/**
 * Assembler:	Uses instance of SymbolTable, Parse,
 * 				and Code to translate form low-level
 * 				language to binary codes.
 * 
 * @author Angel Tapia
 * @version 1.0
 */
import java.io.FileOutputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.io.PrintWriter;

public class Assembler {
	
	// ALGORITHM:
	// get input file name
	// create output file name and stream
	
	// create symbol table
	// do first pass to build symbol table (no output yet!)
	// do second pass to output translated ASM to HACK code
	
	// print out "done" message to user
	// close output file stream
	public static void main(String[] args) {
		
		String inputFileName, outputFileName;
		PrintWriter outputFile = null; //keep compiler happy
		SymbolTable symbolTable;
		int romAddress, ramAddress;
	
		//get input file name from command line or console input
		if(args.length == 1) {
			System.out.println("command line arg = " + args[0]);
			inputFileName = args[0];
		}
		else
		{
			Scanner keyboard = new Scanner(System.in);

			System.out.println("Please enter assembly file name you would like to assemble.");
			System.out.println("Don't forget the .asm extension: ");
			inputFileName = keyboard.nextLine();
					
			keyboard.close();
		}
		
		outputFileName = inputFileName.substring(0,inputFileName.lastIndexOf('.')) + ".hack";
							
		try {
			outputFile = new PrintWriter(new FileOutputStream(outputFileName));
		} catch (FileNotFoundException ex) {
			System.err.println("Could not open output file " + outputFileName);
			System.err.println("Run program again, make sure you have write permissions, etc.");
			System.exit(0);
		}
		
		
		
		// TODO: finish driver as algorithm describes
		
		symbolTable  = new SymbolTable() ;// create symbol table
		
		firstPass(inputFileName, symbolTable) ;// do first pass to build symbol table (no output yet!)
		
		if (labelErrors != null)// There is label naming errors
		{
			System.out.println(outputFileName + " (LABEL ERRORS): " + labelErrors) ;
			System.out.println("Assembler Program closed!") ;
			outputFile.close() ;
			System.exit(0) ;
		}
		
		secondPass(inputFileName, symbolTable, outputFile) ;
		
		if (variableErrors != null)// There is variables naming errors
		{
			System.out.println(outputFileName + " (Variable ERRORS): " + variableErrors) ;
			System.out.println("Assembler Program closed!") ;
			outputFile.close() ;
			System.exit(0) ;
		}
		
		outputFile.close() ;
		System.out.println("Done successfully!") ;// print out "done" message to user
	}
	
	// Stores line error number and and code error number if any
	private static String labelErrors = null ;
	private static String variableErrors = null ;
	
	
	// TODO: march through the source code without generating any code
	// for each label declaration (LABEL) that appears in the source code,
	// add the pair <LABEL, n> to the symbol table
	// n = romAddress which you should keep track of as you go through each line
	// HINT: when should rom address increase? What kind of commands?
	private static void firstPass(String inputFileName, SymbolTable symbolTable)
	{
		int codeNumber = 1  ;
		int romAddress = 0  ;
		Parser parser = new Parser(inputFileName) ;
		String errors = "" ;
		
		while (parser.hasMoreCommands())
		{
			parser.advance() ;
			char command = parser.getCommandTypeChar()  ;
			
			if (command == Parser.L_COMMAND)
			{
				int gotoAddress = codeNumber - romAddress - 1 ;
				if (!symbolTable.addEntry(parser.getSymbol() , gotoAddress))// label exist of invalid
				{
					int lineNumber = parser.getLineNumber() ;
					errors += "[Line:" + lineNumber + " Code:" + codeNumber + "]" ;
				}
				else
				{
					romAddress++ ;
				}				
			}
			codeNumber += command != Parser.NO_COMMAND ? 1 : 0 ;
		}
		
		labelErrors = errors.length() > 0 ? errors : null ;	
		
	}

	// TODO: march again through the source code and process each line:
	// if the line is a c-instruction, simple (translate)
	// if the line is @xxx where xxx is a number, simple (translate)
	// if the line is @xxx and xxx is a symbol, look it up in the symbol
	// table and proceed as follows:
	// If the symbol is found, replace it with its numeric value and
	// and complete the commands translation
	// If the symbol is not found, then it must represent a new variable:
	// add the pair <xxx, n> to the symbol table, where n is the next
	// available RAM address, and complete the commands translation
	// HINT: when should rom address increase? What should ram address start
	// at? When should it increase? What do you do with L commands and No
	// commands?
	private static void secondPass(String inputFileName, SymbolTable symbolTable, PrintWriter outputFile)
	{
				
		Parser parser = new Parser(inputFileName) ;
		Code translator = new Code() ;
		
		int ramAddress = 16  ;
		int codeNumber = 1 ;
		String errors = "" ;
		
		
		while (parser.hasMoreCommands())
		{
			parser.advance() ;
			String code = parser.getCleanLine() ;
			String symbol = parser.getSymbol() ;
			char command = parser.getCommandTypeChar() ;
			int lineNumber = parser.getLineNumber() ;			
			
			String compCode = translator.getComp(parser.getComp()) ;
			String destCode = translator.getDest(parser.getDest()) ;
			String jumpCode = translator.getJump(parser.getJump()) ;
			String binaryCode = "111" + compCode + destCode + jumpCode ;		
			
			
			if (command != Parser.NO_COMMAND)// parsers only non-empty cleanLine
			{
				if (command == Parser.A_COMMAND || command == Parser.L_COMMAND)
				{
					if (command == Parser.A_COMMAND)// if @code is integer
					{
						try
						{
							int decimal = Integer.parseInt(code.substring(1)) ;
							if (decimal < Code.MIM_DECIMAL || decimal > Code.MAX_DECIMAL)
							{
								errors += "[out of range value Line:" 
										+ lineNumber + " Code:" + codeNumber + "]" ;
								binaryCode = null ;
							}
							else
							{
								binaryCode = Code.decimalToBinary(decimal) ;// literal int
							}
						}
						catch (Exception e)
						{
							if (symbolTable.contains(symbol))
							{
								int ram = symbolTable.getAddress(symbol) ;
								binaryCode = Code.decimalToBinary(ram) ;
							}
							else
							{			
								if (!symbolTable.addEntry(symbol, ramAddress))// @code symbol error
								{
									binaryCode = null ;
									errors += "[Line:" + lineNumber + " Code:" + codeNumber + "]" ;
									
								}
								else// set variable to int value
								{
									binaryCode = Code.decimalToBinary(ramAddress) ;
								}								
							}							
						}					
					}
				}
				else if (command == Parser.C_COMMAND && compCode == null)// compCode errors
				{
					binaryCode = null ;
					errors += "[Line:" + lineNumber + " Code:" + codeNumber + "]" ;
				}				
				if (command != Parser.L_COMMAND && binaryCode != null)// output file
				{
					outputFile.println(binaryCode) ;
				}
				codeNumber++ ;
			}
		}		
		variableErrors = errors.length() > 0 ? errors : null ;	
	}
}
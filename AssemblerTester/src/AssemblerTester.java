import java.io.File ;
import java.io.FileDescriptor ;
import java.io.FileNotFoundException ;
import java.io.FileOutputStream ;
import java.io.PrintWriter ;
import java.util.ArrayList ;
import java.util.HashMap ;
import java.util.Scanner ;

public class AssemblerTester
{

	// ALGORITHM:
	// get input file name
	// create output file name and stream

	// create symbol table
	// do first pass to build symbol table (no output yet!)
	// do second pass to output translated ASM to HACK code

	// print out "done" message to user
	// close output file stream
	public static void main(String[] args)
	{
		
		String path = System.getProperty("user.dir") + "\\TestFiles" ;
//		String pathOut = System.getProperty("user.dir") + "\\TestFilesOut" ;	
		
		File folder = new File( path ) ;
		File[] listOfFiles = folder.listFiles() ;
		
		for (int i = 0; i < listOfFiles.length; i++)
		{
			//System.out.printf("%-2s %s%n",i ,listOfFiles[i].getName()) ;
			String nameIn = null , nameOut = null ;
			PrintWriter outputFile = null ; // keep compiler happy
			SymbolTable symbolTable ;
			
			nameIn = listOfFiles[i].getPath() ;
			
			if (nameIn.substring(nameIn.indexOf(".") + 1).equals("asm"))
			{//System.out.printf("%s %n %s%n",listOfFiles[i].getName(), nameOut) ;
				
				nameOut = listOfFiles[i].getName() ;
				nameOut = nameOut.substring(0, nameOut.lastIndexOf('.')) + "0" ;
				nameOut = path + "\\" + nameOut + ".hack" ;	
				
				try
				{
					outputFile = new PrintWriter(new FileOutputStream(nameOut)) ;
				}
				catch (FileNotFoundException ex)
				{
					System.err.println("Could not open output file " + nameOut) ;
					System.err.println("Run program again, make sure you have write permissions, etc.") ;
					System.exit(0) ;
				}
		
				
				
				
				// TODO: finish driver as algorithm describes
		
				symbolTable = new SymbolTable() ;
				firstPass(nameIn, symbolTable) ;		
				secondPass(nameIn, symbolTable, outputFile) ;				
				outputFile.close() ;		
				System.out.println(listOfFiles[i].getName() + " Done successfully!") ;				
				
			}
		}

	}
	private static void firstPass(String inputFileName, SymbolTable symbolTable)
	{
		int codeNumber = 1 ;
		int romAddress = 0 ;
		Parser parser = new Parser(inputFileName) ;
		String errors = "" ;

		while (parser.hasMoreCommands())
		{
			parser.advance() ;
			char command = parser.getCommandTypeChar() ;

			if (command == Parser.L_COMMAND)
			{
				int gotoAddress = codeNumber - romAddress - 1 ;
				
				if (!symbolTable.addEntry(parser.getSymbol(), gotoAddress))
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


	}	
	
	private static void secondPass(String inputFileName, SymbolTable symbolTable, PrintWriter outputFile)
	{

		Parser parser = new Parser(inputFileName) ;
		Code translator = new Code() ;
		int ramAddress = 16 ;
		while (parser.hasMoreCommands())
		{	
			parser.advance() ;
			String symbol = parser.getSymbol() ;
			char command = parser.getCommandTypeChar() ;
			
			String compCode = translator.getComp(parser.getComp()) ;
			String destCode = translator.getDest(parser.getDest()) ;
			String jumpCode = translator.getJump(parser.getJump()) ;
			String binaryCode = "111" + compCode + destCode + jumpCode ;
			
			if (command != Parser.NO_COMMAND)
			{			
				if (command == Parser.A_COMMAND)
				{
					try
					{
						int value = Integer.parseInt(symbol) ;
						outputFile.println(Code.decimalToBinary (value)) ;
					}
					catch (Exception e)
					{
						if (symbolTable.contains(symbol))
						{							
							int value = symbolTable.getAddress(symbol) ;
							outputFile.println(Code.decimalToBinary (value)) ;
						}
						else
						{
							symbolTable.addEntry(symbol, ramAddress++) ;
							outputFile.println(Code.decimalToBinary (ramAddress - 1)) ;
						}
					}					
				}
				else if (command != Parser.L_COMMAND)
				{
					outputFile.println(binaryCode) ;
				}			
			}
		}		
	}
}





























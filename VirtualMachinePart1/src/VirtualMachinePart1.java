/**
 * @author Angel Tapia
 * @version 1.0
 * 
 * Algorithm:
 * 	1) Create a Parser class:
 * 		-Create constants for valid command and command types:
 * 		 C_ARITHMETIC, C_PUSH, push, pop, add, sub, neg, gt, eq,...
 * 		-Create instance variable:
 * 		 inputFile, lineNumber, commandType, command, 
 * 		 segment, index, and a table of valid commands
 * 		-Constructor receive the name of the file
 * 		 as a string, opens the file, and initializes
 * 		 the table of valid command.
 * 		-Create a method hasMoreCommands that return a boolean 
 * 		 if input file has more commands
 * 		 Create a method parse that 
 * 		 from the input file.
 * 		 a) Read the next available command
 * 		 b) Trim any comments
 * 		 c) Use a Scanner object to parse each command part
 * 		 	and parse it into its parts: command, segment, index, 
 * 			and commandType
 * 		 d) closed the Scanner object
 * 		-Create a method advance:
 * 		 Call the parse method only if there is more commands
 * 		-Create accessor for command
 * 		-Create accessor for segment
 * 		-Create acceosor for index
 * 		-Create accessor for commandType
 * 		-Create accessor for lineNumber
 * 2) Create a  CodeWriter class
 * 		-Create constants for valid Memory segments:
 * 		 LCL_BASE = "LCL, ARG_BASE  = "ARG", THIS_BASE = "THIS"
 * 		 THAT_BASE = "THAT", TEMP_BASE = 5,...
 * 		-Create instance variable for outputFile, fileNme, and labelCounter
 * 		-Constructor receives a reference of a PrintWriter object
 * 		 to instantiate outputFile
 * 		-Create a method setFileName that sets fileNem so it
 * 		 can be use to create static variables for the assembly code
 * 		-Write a method writeArithmeticPrefix that writes beginning part of 
 * 		 of arithmetic command in assembly code to the outputFile:
 *		 outputFile.println("@SP") ;
 *		 outputFile.println("AM=M-1") ;
 *		 outputFile.println("D=M") ;
 *		 outputFile.println("A=A-1") ;
 *		-Write a method writePushSuffix that writes the end part of 
 *		 push command in assembly code to the outputFile:
 *		 outputFile.println("@SP") ;
 *		 outputFile.println("AM=M+1") ;
 *		 outputFile.println("A=A-1") ;
 *		 outputFile.println("M=D") ;
 *		-Write a method writePopSuffix that writes the end part of 
 *		 pop command in assembly code to the outputFile:
 * 		 outputFile.println("@R13") ;
 *		 outputFile.println("M=D") ;
 *		 outputFile.println("@SP") ;
 *		 outputFile.println("AM=M-1") ;
 *		 outputFile.println("D=M") ;
 *		 outputFile.println("@13") ;
 *		 outputFile.println("A=M") ;
 *		 outputFile.println("M=D") ;
 *		-Create a method writeArithmetic that receives 
 *		 an arithmetic command:
 *		 if NEG command: write full code for neg
 *		 else if NOT command: write full code for not
 *		 else call writeArithmeticPrefix and write 
 *		 the rest of the arithmetic command.
 *		-Write a method writePush that receives a command, 
 *		 a segment, and index:
 *		 a) Write the beginning of the push assembly codes
 *		 b) Make D store the value to be pushed to the stack
 *		 c) Write the beginning of the pop assembly codes
 *		 d) call writePushSuffix
 *		-Write a method writePop that receives a command, 
 *		 a segment, and index:
 *		 a) Write the beginning of the pop assembly codes
 *		 b) Make D store the index in memory to be store a value
 *		 c) Write the beginning of the pop assembly codes
 *		 d) call writePopSuffix
 *		-Create a method writePushPop that receives a command, 
 *		 a segment, and index:
 *		 create an is statement that selects writePush or
 *		 writePop according to the command argument
 *		-Write a method close that closes the output stream.
 *	3) Create a driver (main):
 *		-get input file name
 *		-create output file name and stream
 *		-Create an instance of the parse class called vmFileParser 
 *		 and pass the name of the input file as its argument
 *		-Create an instance of the CodeWriter class named asmTranslator
 *		 and pass the output file stream as a PrintWriter object
 *		-setFileName for vmFileParser
 *		-Create a while loop to translate the vmFile to asmFile:
 *		 a) Call vmFileParser.advance()
 *		 b) If (command != null && segment != null && index != -1)
 *		    writePushPop
 *		 c) Else if (command != null)
 *		 	writeArithmetic
 *		-Close the asmTranslator output stream
 *		Print to the screen the end of the translation.
 */
import java.io.FileNotFoundException ;
import java.io.FileOutputStream ;
import java.io.PrintWriter ;
import java.util.Scanner ;

public class VirtualMachinePart1
{	
	public static void main(String[] args){

		String inputFileName, outputFileName;
		PrintWriter outputFile = null; //keep compiler happy
		//SymbolTable symbolTable;
		//int romAddress, ramAddress;
	
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
		
		outputFileName = inputFileName.substring(0,inputFileName.lastIndexOf('.')) + ".asm";
							
		try {
			outputFile = new PrintWriter(new FileOutputStream(outputFileName));
		} catch (FileNotFoundException ex) {
			System.err.println("Could not open output file " + outputFileName);
			System.err.println("Run program again, make sure you have write permissions, etc.");
			System.exit(0);
		}
		
		// TODO: finish driver as algorithm describes
		// Test files: BasicTest.vm PointerTest.vm SimpleAdd.vm StackTest.vm StaticTest.vm
		
		Parser vmFileParser = new Parser(inputFileName) ;
		CodeWriter asmTranslator = new CodeWriter(outputFile) ;
		asmTranslator.setFileName(outputFileName) ;

		while (vmFileParser.hasMoreCommands())
		{
			vmFileParser.advance() ;
			String command = vmFileParser.getCommand() ;
			String segment = vmFileParser.getSegment() ;
			int index = vmFileParser.getIndex() ;

			if (command != null && segment != null && index != -1)
			{
				asmTranslator.writePushPop(command, segment, index) ;
			}
			else if (command != null)
			{
				asmTranslator.writeArithmetic(command) ;
			}
		}
		
		asmTranslator.close() ;
		System.out.println(inputFileName + " was successfully translated to " + outputFileName + "!") ;
	}
}
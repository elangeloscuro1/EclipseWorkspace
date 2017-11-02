/**
 * Parser:	Parses each code line 
 * 			into its corresponding parts
 * 
 * @author Angel Tapia
 * @version 1.0
 */
import java.io.File ;
import java.io.FileNotFoundException ;
import java.util.HashMap ;
import java.util.Scanner ;

public class Parser
{
	//	Command types and commands for this Parser version 1.0
	public static final String C_ARITHMETIC = "C_ARITHMETIC" ;	
	public static final String C_PUSH       = "C_PUSH" ;	
	public static final String C_POP        = "C_POP" ;
	public static final String ADD       	= "add" ;	
	public static final String SUB       	= "sub" ;	
	public static final String NEG       	= "neg" ;	
	public static final String EQ      	 	= "eq" ;	
	public static final String GT       	= "gt" ;	
	public static final String LT       	= "lt" ;	
	public static final String AND       	= "and" ;	
	public static final String OR       	= "or" ;	
	public static final String NOT       	= "not" ;	
	public static final String PUSH 		= "push" ;
	public static final String POP 			= "pop" ;	
	
	//	Input file, valid commands table and debugging
	private HashMap<String, String> validCommands;
	private Scanner inputFile ;
	private int lineNumber ;
	
	//	Instance variables
	private String commandType ;// command type
	private String command ;	// command
	private String segment ;	//memory segment
	private int index ;			//current index
	
	/**
	 * DESCRIPTION:		Opens the input file/stream and gets ready to parse it
	 * PRECONDITION:	vmFile must include the extension of the file
	 * POSTCONDITION	Parse is ready to parse the vmFile
	 * @param vmFile: 	the file to be parsed
	 */
	public Parser(String vmFile)
	{		
		try
		{
			inputFile = new Scanner(new File(vmFile)) ;
		}
		catch (FileNotFoundException e)
		{
			System.out.println("ERROR: " + e.getMessage()) ;
			System.out.println("End of the program!") ;
			System.exit(0) ;
		}
		// initializes a the table of valid command codes
		validCommands = new HashMap<String, String>() ;
		validCommands.put(ADD,  C_ARITHMETIC) ;
		validCommands.put(SUB, 	C_ARITHMETIC) ;
		validCommands.put(NEG, 	C_ARITHMETIC) ;
		validCommands.put(EQ,  	C_ARITHMETIC) ;
		validCommands.put(GT,  	C_ARITHMETIC) ;
		validCommands.put(LT,  	C_ARITHMETIC) ;
		validCommands.put(AND, 	C_ARITHMETIC) ;
		validCommands.put(OR,  	C_ARITHMETIC) ;
		validCommands.put(NOT, 	C_ARITHMETIC) ;
		validCommands.put(PUSH, C_PUSH) ;
		validCommands.put(POP,  C_POP) ;
	}
	
	/**
	 * DESCRIPTION:		Checks if the input file has more commands
	 * @return	true if the file contains more commands; false otherwise
	 * @throws:	IllegalStateException if this scanner is closed
	 */
	public boolean hasMoreCommands()
	{		
		if (!inputFile.hasNextLine())
		{
			inputFile.close() ;
			return false ;
		}
		return true ;
	}
	
	//
	//makes it the current command. Should be called
	//only if hasMoreCommands is true.
	//Initially there is no current command.
	/**
	 * DESCRIPTION:		Reads the next command from the input and
	 * 					parse it into its parts
	 * @throws:	IllegalStateException if this scanner is closed
	 */
	public void advance()
	{
		if (hasMoreCommands())
		{
			lineNumber++ ;
			parse() ;
		}
	}
	
	/*
	 * DESCRIPTION: 	parse each VM command into its parts
	 * PRECONDITION:	input file must have more line of code
	 * POSTCONDITION:	VM commands is parsed into its parts
	 */
	private void parse()
	{
		String codeLine = inputFile.nextLine() ;
				
		if (codeLine.contains("/"))// trims comments
		{
			codeLine = codeLine.substring(0, codeLine.indexOf("/")) ;
		}
		
		Scanner scanCode = new Scanner(codeLine) ;
		command = scanCode.hasNext() ? scanCode.next() : null ;
		commandType = validCommands.get(command) ;
		segment = scanCode.hasNext() ? scanCode.next() : null ;
		index =  scanCode.hasNextInt() ? scanCode.nextInt() : -1 ;		
		scanCode.close() ;		
	}
	
	/**
	 * DESCRIPTION: Access the value current line number (can be used for debugging)
	 * @return the value of lineNumber
	 */
	public int getLineNumber()
	{
		return lineNumber ;
	}
	
	/**
	 * DESCRIPTION:		Access the value of the current command
	 * @return	the value of the current command; 
	 * 			null if current line contains no command
	 */
	public String getCommand()
	{
		return command ;
	}
	
	/**
	 * DESCRIPTION:		Access the value of the current command type
	 * @return	C_ARITHMETIC for all the arithmetic command
	 * 			C_PUSH for push command
	 * 			C_POP for pop command
	 * 			null if current line contains no command
	 */
	public String getCommandType()
	{
		return commandType ;
	}
		
	/**
	 * DESCRIPTION:		Access the memory segment of the current command
	 * PRECONDITION:	command type must be PUSH or POP
	 * @return	return the memory segment of the current command;
	 * 			null if current command is not a PUSH or POP command 
	 */
	public String getSegment()
	{
		return segment ;
	}
	
	/**
	 * DESCRIPTION:		Access the index of the current memory segment
	 * PRECONDITION:	command type must be PUSH or POP
	 * @return the index of the current memory segment;
	 * 			-1 if current command is not a PUSH or POP command
	 */
	public int getIndex()
	{		
		return index ;
	}	
}
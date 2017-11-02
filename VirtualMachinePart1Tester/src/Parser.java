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
	
	//	Input file and debugging
	private HashMap<String, String> validCommands;	
	private Scanner inputFile ;
	private int lineNumber ;
	
	//	Instance variables
	private String commandType ;
	private String command ;
	private String segment ;
	private int index ;
	
	//Opens the input file/stream and gets ready to
	//parse it.
	/**
	 * DESCRIPTION
	 * PRECONDITION:
	 * POSTCONDITION
	 * 
	 * @param vmFile
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
	
	//Are there more commands in the input?
	public boolean hasMoreCommands()
	{		
		if (!inputFile.hasNextLine())
		{
			inputFile.close() ;
			return false ;
		}
		return true ;
	}
	
	//Reads the next command from the input and
	//makes it the current command. Should be called
	//only if hasMoreCommands is true.
	//Initially there is no current command.
	public void advance()
	{
		if (hasMoreCommands())
		{
			lineNumber++ ;
			parse() ;
		}
	}
	
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
		
	public int getLineNumber()
	{
		return lineNumber ;
	}
	public String getCommand()
	{
		return command ;
	}
	
	//Returns the type of the current VM command.
	//C_ARITHMETIC is returned for all the arithmetic
	//commands.
	public String getCommandType()
	{
		return commandType ;
	}
	
	
	//Returns the first arg. of the current command.
	//In the case of C_ARITHMETIC, the command itself
	//(add, sub, etc.) is returned. Should not be called
	//if the current command is C_RETURN.
	public String getSegment()
	{
		return segment ;
	}
	
	//Returns the second argument of the current
	//command. Should be called only if the current
	//command is C_PUSH, C_POP, C_FUNCTION, or
	//C_CALL.
	public int getIndex()
	{		
		return index ;
	}
	
	
	
	
	
	
	
	
}

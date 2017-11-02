/**
 * Parser:	Parses each code line 
 * 			into its corresponding parts
 * 
 * @author Angel Tapia
 * @version 1.0
 */
import java.io.File ;
import java.io.FileNotFoundException ;
import java.util.Scanner ;

public class Parser
{
	// Command types
	public static final char NO_COMMAND = 'N' ;
	public static final char A_COMMAND  = 'A' ;
	public static final char C_COMMAND  = 'C' ;
	public static final char L_COMMAND  = 'L' ;
	
	// Instance variables for file and debugging
	private Scanner inputFile ;
	private int lineNumber ;
	private String rawLine ;
	
	// Instance variables for parsed command parts
	private  String cleanLine ;
	private char commandType ;
	private String symbol ;
	private String destMnemonic ;
	private String compMnemonic ;
	private String jumpMnemonic ;	
	
	/**
	 * DESCRIPTION:		Opens input file and prepares it to parse
	 * PRECONDITION:	Provide a ASM file
	 * POSTCONDITION:	Programs ends with a message if file cannot be opened
	 * 
	 * @param fileName: the name of the file to be read.
	 */
	public Parser(String fileName)
	{
		try
		{
			inputFile = new Scanner(new File(fileName)) ;
		}
		catch (FileNotFoundException e)
		{
			System.out.println("ERROR: " + e.getMessage()) ;
			System.out.println("End of the program!") ;
			System.exit(0) ;
		}
	}
	
	/**
	 * Check if the end of input file
	 * 
	 * @return true if file has more lines; false otherwise.
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
	
	/**
	 * DESCRIPTION:		Reads next line from file and parses it into instance variables
	 * PRECONDITION:	file stream is opened, called only if hasMoreCommands()
	 * POSTCONDITION:	if hasMoreCommands(): current instruction is parsed into variables
	 */
	public void advance()//parse into instance variables
	{
		if (hasMoreCommands())// closes file if false
		{
			rawLine = inputFile.nextLine() ;
			cleanLine() ;//sets cleanLine
			lineNumber++ ;
			parse() ;			
		}
	}
	
	/**
	 * DESCRIPTION:		Helper method that cleans raw lines 
	 * 					instruction by removing non-essential parts
	 * POSTCONDITION:	parses into cleanLine variable
	 */
	private void cleanLine()
	{	
		
		String clean = rawLine.replaceAll("\\s", "") ;//Replaces any white space
		if (clean == null || clean.length() == 0)
		{
			cleanLine =  "" ;
		}
		else if(clean.contains("/"))
		{
			cleanLine =  clean.substring(0, clean.indexOf("/")) ;
		}
		else
		{
			cleanLine = clean ;
		}
	}
	
	/**
	 * DESCRIPTION:		Helper method that determines command type from cleanLine
	 * Precondition:	cleanLine() has been called
	 * POSTCONDITION:	Command type is either: N, A, L, or C
	 */
	private void parseCommandType()
	{
		if (cleanLine == null || cleanLine.length() == 0)
		{
			commandType = 'N' ;
		}
		else if (cleanLine.charAt(0) == '@')
		{
			commandType = 'A' ;
		}
		else if (cleanLine.charAt(0) == '(')// later: check if chartAt(length() - 1) == ')'
		{
			commandType = 'L' ;
		}
		else
		{
			commandType = 'C' ;
		}
	}
	
	/**
	 * DESCRIPTION:		Helper method that parses line depending on instruction type
	 * PRECONDITION:	cleanLine() has been called
	 * POSTCONDITION:	instance variables for instruction are parsed
	 */
	private void parse()
	{
		parseCommandType() ;
		parseSymbol() ;
		parseDest() ;			
		parseComp() ;
		parseJump() ;
		
	}
	
	/**
	 * DESCRIPTION:		Helper method that parses symbol for A- or L-Command
	 * Precondition:	cleanLine() and parseCommandType() have been called
	 * POSTCONDITION:	symbol has valid valid value or null
	 */
	private void parseSymbol()
	{
		int size = cleanLine.length() ;
		
		if (commandType == A_COMMAND && size > 1)
		{
			symbol = cleanLine.substring(1) ;
		}
		else if (commandType == L_COMMAND && size > 2)
		{
			boolean isValid = cleanLine.charAt(size - 1) == ')' ;
			symbol = isValid ? cleanLine.substring(1, size - 1) : null ;
		}
		else
		{
			symbol = null ;
		}
	}
	
	/**
	 * DESCRIPTION:		Helper method that parses into destMnemonic
	 * Precondition:	cleanLine() and parseCommandType() have been called
	 * POSTCONDITION:	destMnemonic has valid valid value or null
	 */
	private void parseDest()
	{
		if (commandType == C_COMMAND && cleanLine.contains("="))
		{
			destMnemonic = cleanLine.substring(0, cleanLine.indexOf('=')) ;
		}
		else
		{
			destMnemonic = null ;
		}
	}
	
	/**
	 * DESCRIPTION:		Helper method that parses into compMnemonic
	 * Precondition:	cleanLine() and parseCommandType() have been called
	 * POSTCONDITION:	compMnemonic has valid valid value or null
	 */
	private void parseComp()
	{
		if (commandType == C_COMMAND && cleanLine.contains("="))
		{
			compMnemonic = cleanLine.substring(cleanLine.indexOf('=') + 1) ;
		}
		else if (commandType == C_COMMAND && cleanLine.contains(";"))
		{
			compMnemonic = cleanLine.substring(0, cleanLine.indexOf(';')) ;
		}
		else
		{
			compMnemonic = commandType == C_COMMAND ? cleanLine : null  ;
		}
	}
	
	/**
	 * DESCRIPTION:		Helper method that parses into jumpMnemonic
	 * Precondition:	cleanLine() and parseCommandType() have been called
	 * POSTCONDITION:	jumpMnemonic has valid valid value or null
	 */
	private void parseJump()
	{
		if (commandType == C_COMMAND && cleanLine.contains(";"))
		{
			jumpMnemonic = cleanLine.substring(cleanLine.indexOf(';') + 1) ;
		}
		else
		{
			jumpMnemonic = null ;
		}
	}	
	
	/**
	 * Accessor for commandType
	 * 
	 * @return the value of commandType
	 */
	public char getCommandTypeChar()
	{
		return commandType ;
	}
	
	/**
	 * Accessor for symbol
	 * 
	 * @return the value of symbol
	 */
	public String getSymbol()
	{
		return symbol ;
	}
	
	/**
	 * Accessor for destMnemonic
	 * 
	 * @return the value of destMnemonic
	 */
	public String getDest()
	{
		return destMnemonic ;
	}
	
	/**
	 * Accessor for compMnemonic
	 * 
	 * @return the value of compMnemonic
	 */
	public String getComp()
	{
		return compMnemonic ;
	}	
	
	/**
	 * Accessor for jumpMnemonic
	 * 
	 * @return the value of jumpMnemonic
	 */
	public String getJump()
	{
		return jumpMnemonic ;
	}
	
	/**
	 * Accessor for commandType
	 * 
	 * @return the value of commandType
	 */
	public String getCommandTypeString()
	{
		return Character.toString(commandType) ;
	}	
	
	/**
	 * Accessor for rawLine
	 * 
	 * @return the value of rawLine
	 */
	public String getRawLine()
	{
		return rawLine ;
	}	
	
	/**
	 * Accessor for cleanLine
	 * 
	 * @return the value of cleanLine
	 */
	public String getCleanLine()
	{
		return cleanLine ;
	}	
	
	/**
	 * Accessor for lineNumber
	 * 
	 * @return the value of lineNumber
	 */
	public int getLineNumber()
	{
		return lineNumber ;
	}	
}
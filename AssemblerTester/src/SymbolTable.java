
/**
 * SymbolTable: Manages symbols: -Symbols naming can start with
 * abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ_.$: -Symbol can also
 * contains numbers after the first letter.
 * 
 * @author Angel Tapia
 * @version 1.0
 */
import java.util.HashMap ;

public class SymbolTable
{
	// Initial valid chars
	private static final String INITIAL_VALID_CHARS = "abcdefghijklmnopqrstuvwxyz" + "ABCDEFGHIJKLMNOPQRSTUVWXYZ_.$:" ;
	// Valid chars can include number ONLY after initial valid chars
	private static final String ALL_VALID_CHARS = INITIAL_VALID_CHARS + "0123456789" ;

	private HashMap<String, Integer> symbolTable ;

	/**
	 * DESCRIPTION: Initializes hashmaps with predefined symbols PRECONDITION:
	 * All Assembler must include the same predefined symbols POSTCONDITION: All
	 * hashmaps have lookups for valid codes
	 */
	public SymbolTable()
	{
		symbolTable = new HashMap<String, Integer>() ;

		for (int i = 0; i < 16; i++)
		{
			addEntry("R" + i, i) ;
		}
		addEntry("SP", 0) ;
		addEntry("LCL", 1) ;
		addEntry("ARG", 2) ;
		addEntry("THIS", 3) ;
		addEntry("THAT", 4) ;
		addEntry("SCREEN", 16384) ;
		addEntry("KBD", 24576) ;
	}

	/**
	 * Adds a new symbol/address pair to hashmap
	 * 
	 * @param symbol:
	 *            The symbol to be added.
	 * @param address:
	 *            The address to be added
	 * @return true if symbol/address pair is successfully added; false if
	 *         symbol exist in hashmap or symbol is invalid.
	 */
	public boolean addEntry(String symbol, int address)
	{
		if (contains(symbol) || !isValidName(symbol))
		{
			return false ;
		}
		else
		{
			symbolTable.put(symbol, address) ;
			return true ;
		}
	}

	/**
	 * Checks if the specified symbol is in the hashmap
	 * 
	 * @param symbol:
	 *            a symbol to be checked
	 * @return true if symbol exist in the hashmap; false otherwise
	 */
	public boolean contains(String symbol)
	{

		return symbolTable.containsKey(symbol) ;
	}

	/**
	 * access the address of a specified symbol
	 * 
	 * @param symbol
	 *            for the wanted address
	 * @return if the specified symbol exists: returns the address of the
	 *         symbol; else returns -1 ;
	 */
	public int getAddress(String symbol)
	{
		if (symbolTable.get(symbol) == null)
		{
			return -1 ;
		}
		return symbolTable.get(symbol) ;
	}

	/**
	 * Checks if the specified symbol is valid
	 * 
	 * @param symbol:
	 *            the symbol to be checked.
	 * @return true if symbol is valid; false otherwise.
	 */
	private static boolean isValidName(String symbol)
	{
		if (symbol == null || symbol.length() == 0)
		{
			return false ;
		}
		else
		{
			for (int i = 0; i < symbol.length(); i++)
			{
				if (i == 0 && !INITIAL_VALID_CHARS.contains("" + symbol.charAt(i)))
				{
					return false ;
				}
				else if (!ALL_VALID_CHARS.contains("" + symbol.charAt(i)))
				{
					return false ;
				}
			}
		}
		return true ;
	}
}
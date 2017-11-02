
/**
 * Code: Translate each field into its corresponding binary value, and assembles
 * the resulting value in the form of 111accccccdddjjj Literal value must be
 * between 0 to 32767 (inclusive)
 * 
 * @author Angel Tapia
 * @version 1.0
 */
import java.util.HashMap ;

public class Code
{
	// Minimum and Maximum literal value
	public static final int MIM_DECIMAL = 0 ;
	public static final int MAX_DECIMAL = 32767 ;

	private HashMap<String, String> compCodes ;// 7 bits
	private HashMap<String, String> destCodes ;// 3 bits
	private HashMap<String, String> jumpCodes ;// 3 bits

	/**
	 * DESCRIPTION: Initializes hashmaps with code for easy lookup PRECONDITION:
	 * comp = 7 bit (include a), dest/jump = 3 bits POSTCONDITION: All hashmaps
	 * have lookups for valid codes
	 */
	public Code()
	{
		// Initializes compCode, destCode, and jumpCode
		compCodes = new HashMap<String, String>() ;
		destCodes = new HashMap<String, String>() ;
		jumpCodes = new HashMap<String, String>() ;

		// Fills out compCode where a = 0
		compCodes.put("0", "0101010") ;
		compCodes.put("1", "0111111") ;
		compCodes.put("-1", "0111010") ;
		compCodes.put("D", "0001100") ;
		compCodes.put("A", "0110000") ;
		compCodes.put("!D", "0001101") ;
		compCodes.put("!A", "0110001") ;
		compCodes.put("-D", "0001111") ;
		compCodes.put("-A", "0110011") ;
		compCodes.put("D+1", "0011111") ;
		compCodes.put("A+1", "0110111") ;
		compCodes.put("D-1", "0001110") ;
		compCodes.put("A-1", "0110010") ;
		compCodes.put("D+A", "0000010") ;// Original Mnemonic
		compCodes.put("A+D", "0000010") ;// D+A = A+D
		compCodes.put("D-A", "0010010") ;
		compCodes.put("A-D", "0000111") ;
		compCodes.put("D&A", "0000000") ;// Original Mnemonic
		compCodes.put("A&D", "0000000") ;// D&A = A&D
		compCodes.put("D|A", "0010101") ;// Original Mnemonic
		compCodes.put("A|D", "0010101") ;// D|A = A|D

		// Fills out compCode where a = 1
		compCodes.put("M", "1110000") ;
		compCodes.put("!M", "1110001") ;
		compCodes.put("-M", "1110011") ;
		compCodes.put("M+1", "1110111") ;
		compCodes.put("M-1", "1110010") ;
		compCodes.put("D+M", "1000010") ;// Original Mnemonic
		compCodes.put("M+D", "1000010") ;// D+M = M+D
		compCodes.put("D-M", "1010011") ;
		compCodes.put("M-D", "1000111") ;
		compCodes.put("D&M", "1000000") ;// Original Mnemonic
		compCodes.put("M&D", "1000000") ;// D&M = M&D
		compCodes.put("D|M", "1010101") ;// Original Mnemonic
		compCodes.put("M|D", "1010101") ;// D|M = M|D

		// Fills out destCode
		destCodes.put(null, "000") ;
		destCodes.put("M", "001") ;
		destCodes.put("D", "010") ;
		destCodes.put("MD", "011") ;
		destCodes.put("A", "100") ;
		destCodes.put("AM", "101") ;
		destCodes.put("AD", "110") ;
		destCodes.put("AMD", "111") ;

		// Fills out jumCode
		jumpCodes.put(null, "000") ;
		jumpCodes.put("JGT", "001") ;
		jumpCodes.put("JEQ", "010") ;
		jumpCodes.put("JGE", "011") ;
		jumpCodes.put("JLT", "100") ;
		jumpCodes.put("JNE", "101") ;
		jumpCodes.put("JLE", "110") ;
		jumpCodes.put("JMP", "111") ;
	}

	/**
	 * Converts to string of bits (7) for given comp mnemonic
	 * 
	 * @param mnemonic:
	 *            a valid comp mnemonic
	 * @return true if comp mnemonic is valid; false otherwise.
	 */
	public String getComp(String mnemonic)
	{
		return compCodes.get(mnemonic) ;
	}

	/**
	 * Converts to string of bits (3) for given dest mnemonic
	 * 
	 * @param mnemonic:
	 *            a valid dest mnemonic
	 * @return true if dest mnemonic is valid; false otherwise.
	 */
	public String getDest(String mnemonic)
	{
		return destCodes.get(mnemonic) ;
	}

	/**
	 * Converts to string of bits (3) for given jump mnemonic
	 * 
	 * @param mnemonic:
	 *            a valid jump mnemonic
	 * @return true if jump mnemonic is valid; false otherwise.
	 */
	public String getJump(String mnemonic)
	{
		return jumpCodes.get(mnemonic) ;
	}

	/**
	 * Converts integer form decimal to binary
	 * 
	 * @param decimal:
	 *            a number between 0-32767 (inclusive)
	 * @return 16-bits string of binary digits if decimal is in valid range ;
	 *         null otherwise
	 */
	public static String decimalToBinary(int decimal)
	{
		StringBuilder format = new StringBuilder("0000000000000000") ;
		int index = 0 ;
		if (decimal < MIM_DECIMAL || decimal > MAX_DECIMAL)
		{
			return null ;
		}

		for (int i = decimal; i > 0; i = i / 2)
		{
			format.replace(index, ++index, "" + (i % 2)) ;
		}
		return format.reverse().toString() ;
	}
}
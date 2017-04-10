package edu.miracosta.cs113.hw07 ;

import java.io.File ;
import java.io.FileInputStream ;
import java.io.FileNotFoundException ;
import java.util.Scanner ;


@SuppressWarnings("serial")
public class MorseCodeTree extends BinaryTree<String>
{
	/** morse code file */
	public static final File MORSE_CODE_FILE = new File("MorseCodeTable.txt") ;
	
	/** Stores the morse code symbols*/
	private String[] morseSymbols ;
	
	/**
	 * Default constructor initializes morscodeSymbols and builds the morse code tree data structure.
	 */
	public MorseCodeTree()
	{
		morseSymbols = new String['z' - 'a' + 1] ;
		buildTree() ;
	}

	/**
	 * Builds the morse code tree data structure using the default file that
	 * contains the letters and symbols.
	 */
	private void buildTree()
	{
		Scanner scan = null ;
		try
		{
			scan = new Scanner(new FileInputStream(MORSE_CODE_FILE)) ;
			
			while(scan.hasNextLine())
			{
				String letter = scan.next() ;
				String code   = scan.next() ;
				
				morseSymbols[letter.charAt(0) - 'a'] = code ;
				
				if (root == null)
				{
					root = new Node<String>("") ;
				}
				
				Node<String> temp = root ;
				
				for (int i = 0; i < code.length() ; i++)
				{
					if (code.charAt(i) == '*')
					{
						
						if (temp.left != null)
						{
							temp = temp.left ;
						}
						else
						{
							temp.left = new Node<String>(letter) ;
						}				
					}
					
					if (code.charAt(i) == '-')
					 {
						
						if (temp.right != null)
						{
							temp = temp.right ;
						}
						else
						{
							temp.right = new Node<String>(letter) ;
						}				
					}
				}
				
			}
		}
		catch (FileNotFoundException e)
		{
			System.out.println(e.getMessage()) ;
			System.out.println("Closing Program...") ;
			System.exit(1) ;			
		}
		scan.close() ;
	}
	
	/**
	 * Creates a string that contains morse codes and theirs respectively equivalent characters.
	 * 
	 * @return a string that contains morse codes and theirs respectively equivalent characters.
	 */
	public String morseCodeTable()
	{
		StringBuilder temp = new StringBuilder() ;
		morseCodeTable(root, temp) ;
		return temp.toString() ;
	}
	
	/**
	 * Encode a message from text to morse code.
	 * @param message: the message to be encoded.
	 * @return a already encoded message.
	 */
	public String encodeMessage(String message)
	{
		String encoding = "" ;
		for (int i = 0; i < message.length(); i++)
		{
			if (message.charAt(i) != ' ')
			{
				encoding += morseSymbols[message.charAt(i) - 'a'] + " " ;
			}
		}
		return encoding ;
	}
	
	/**
	 * Decode a message from morse code to regular text
	 * @param message the message to be decoded.
	 * @return
	 */
	public String decodeMessage(String message)
	{
		String encoding = "" ;

		String[] temp = message.trim().split(" ") ;
		
		
		for (int i = 0; i < temp.length; i++)
		{
			
			Node<String> current = root ;
			for (int j = 0; j < temp[i].length() ; j++)
			{				
				current = temp[i].charAt(j) == '*' ? current.left : current.right ;						
			}
			encoding += current.data ; 
		}
		return encoding ;	
	}
	
	/**
	 * Create a morse code table using the morse code tree data structure.
	 * @param node: the current subtree.
	 * @param sb: the string to be built.
	 */
	private void morseCodeTable(Node<String> node, StringBuilder sb)
	{
		
		if (node == null)
		{
			sb.append("") ;
		}
		else
		{
			String letter = node.toString() ;
			if (letter.length() != 0)
			{
				sb.append(node + " " +  morseSymbols[letter.charAt(0) - 'a'] + "\n" ) ;
			}			
			morseCodeTable(node.left, sb) ;
			morseCodeTable(node.right, sb) ;			
		}
	}
	
	/**
	 * display a menu of option to the user.
	 * @return the option entered by the user.
	 */
	@SuppressWarnings("resource")
	public int menu()
	{
		System.out.println("*********************************") ;
		System.out.println("**[1] Output morse code table  **") ;
		System.out.println("**[2] decode a morse code file **") ;
		System.out.println("**[3] morse code to text       **") ;
		System.out.println("**[4] text to morse code       **") ;
		System.out.println("**[0] exit                     **") ;
		System.out.println("*********************************") ;
		
		Scanner scan = new Scanner(System.in) ;		
		int value = scan.nextInt() ;
		return value ;		
	}	
}
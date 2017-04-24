package edu.miracosta.cs113.lab14;

/**
 * Algorithm:
 * 	A) Create  method find that receive an hashTabel 
 * 	   array and a key to be search in the table.
 * 		-Compute the index by taking the hashCode % table.lenght
 * 		-Use a for loop to check at every single key if necessary.
 * 		 if the the computed index is null 
 * 		 (the object is not on the table), return -1.
 * 		 if the computed index is contains the object, return the index.
 * 		 else, recompute the index by increasing the hashCode by one.
 * 	B) Implement the main method to test the program.
 *		-Create an array hashTabel that contains String objects.
 *		-Call the method find and test for some keys.
 *		-Display the end of the test.
 */
/**
 * This class is a tester for a method find
 * that find a specified key from an hashTable array.
 * 
 * @author Angel Tapia angelTapia07084759@gmail.com
 * version 1.0
 */
public class Lab14Programming1
{
	/**
	 * Tests the method find with a hashTable array.
	 * 
	 * @param args Command Lines.
	 */
	public static void main(String[] args)
	{
		// Assume a hashTable array: "Dick", "Sam", "Pete", "Harry", "Tom" 
		String[] hashTable = { "Dick", "Sam", "Pete", "Harry", "Tom" } ;

		// Object to be located in the hashTable array.
		System.out.println(find(hashTable, "Harry")) ;
		System.out.println(find(hashTable, "Tom")) ;
		System.out.println(find(hashTable, "Dick")) ;
		System.out.println(find(hashTable, "Sam")) ;
		System.out.println(find(hashTable, "Pete")) ;
		System.out.println(find(hashTable, "Hola!")) ;
		
		
		
		System.out.println("...End of the test.") ;
	}

	/**
	 * Searches a specified key in a hashTable.
	 * 
	 * @param values: The hashTable that contains a specified key
	 * @param key: The key to be searched in the hasTanble.
	 * @return the the object position in the hashTable or -1 if the object is not found.
	 */
	public static int find(Object[] values, Object key)
	{
		int index  = key.hashCode() % values.length ;

		for (int i = 1 ; i <= values.length ; i++)
		{
			if (values[index] == null )
			{
				return - 1 ;
			}
			else if (values[index].equals(key))
			{
				return index ;
			}
			else
			{
				index = (key.hashCode() + i) % values.length ;
			}
		}		
		return - 1 ;
	}
}
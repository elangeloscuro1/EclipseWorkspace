package edu.miracosta.cs113.hw09;

/**
 * Algorithm:
 * 	A) Complete class HashtableChain so that it 
 * 	   fully implements the Map interface.
 * 		1) Create private constants and instance variables:
 * 			-final int CAPACITY = 101 ;
 * 			-final double LOAD_THRESHOLD = 3.0 ;
 * 			-LinkedList<Entry<K, V>>[] table ;
 * 			-int numKeys ;
 * 		2) Create variables for testing purpose.
 * 			-int numOfCollisions ;
 * 			-double avarageLikedItems ;
 * 		3) Create a method getTestResult for testing purpose.
 * 			build a string that contain the information of Total Keys,
 * 			total Collisions, and Average linked list length.
 * 		4) Implement an inner private class Entry that implements 
 * 		   the Map.Entry interface with generic parameter of Key and Value.
 * 			-Use the generic K for key and generic V for values.
 * 			-Implement the method getKey, getValue, setValue, and toString.
 * 		5) Implement an inner private class SetIterator that implements the interface
 * 		   Iterator with a generic type Map.Entry<K, V>.
 * 			-Use instance variable of int index;  Entry<K, V> lastItemReturned  ;
 * 			 and Iterator<Entry<K, V>> indexIterator ;
 * 			-Implement the method has next by skipping any null spaces until
 * 			 find a non-null index between 0 and table.lenght.
 * 			-Implement the method next by accessing the next available 
 * 			 index of the hash table.
 * 			-Implements the method remove by removing the last-returned item.
 * 		6) Implement an inner private class EntrySet that extends the 
 * 		   abstract class AbstractSet with parameter Map.Entry<K, V>.
 * 			-Implement the method iterator by returning an iterator of
 * 			 the type SetIterator.
 * 			-Implement the method size by returning value of mumKeys.
 * 		7) Implement a private method rehash that helps to reallocate
 * 		   the current hash table to a new-double-size + 1 hashTable.
 * 			-Iterate through the old table rehashing the entries to the
 * 			 new hash table.
 * 		8) Implement the method clear by reseting the hash table to the initial capacity.
 * 			-Reset
 * 		9) Implement the method get by iterating on the resulted hashed key index.
 * 			-return the value of the key if key is in hash table, null otherwise.
 * 	   10) Implement the method containsKey by calling the method get.
 * 			-return true if there is a match with the specified key, false otherwise.
 * 	   11) implement the method containsValue by traversing the hash table and matching each value.
 * 			-return true if the specified value matches a value from this table; false, otherwise.
 * 	   13) Implement the method entrySet by returning an object of the class EntrySet.
 * 	   14) Implement the method isEmplty by checking if numKeys == 0.
 * 	   15) Implement the method keySet by iterating and building a set of keys.
 * 	   16) Implement the method put:
 * 			-Convert the key to a positive hashCode value for an index position.
 * 			-if current index is empty create a new linked list of Entry, 
 * 			-else replace the current value and return the old value.
 * 			-*** Calculate the numOfCollisions and avarageLikedItems for tests results*** 
 * 	   17) For PutAll method, throw a UnsupportedOperationException.
 * 	   18) Implement the method remove:
 * 			-Iterating over the hash table and if there is a match remove it and
 * 			 return it, else return null. 
 * 	   19) Implement the method size by accessing and returning the value of numKeys.
 * 	   20) Implement the method values by iterating and constructing a collection of values.
 * 	   20) Override the method toString:
 * 			-Iterate over the hash table and construct an string that contains the information
 * 			 of the current index, key, and value as follow: [index] ==> [key=value].
 * 	   20) Override the method equals by comparing this and another object's toString methods.
 * 	B) Create a Tester class HashTableChainTester to test the HashTableChain class.
 *		1) Crate a static method keyValueGen that generates integers values up to the given
 *		   maximum value.
 *		2) In main method:
 *			-Create an instance of the HasTableChain class.
 *			-Add 100 keys and values to the has table.
 *			-Display all of the values by the toString method.
 *			-Display the test result by calling the method getTestResult.
 */

/**
 * HashTableChainTester is a tester class for the 
 * interface HashTableChain.
 * 
 * @author  Angel Tapia <angelTapia07084759@gmail.com>
 * version 1.0
 */
public class HashTableChainTester
{
	/**
	 * 
	 * @param maxInt maximum value to be generated.
	 * @return a generated Integer value.
	 */
	public static Integer keyValueGen(int maxInt)
	{
		return (int) (32000 * Math.random()) ;
	}
	
	/**
	 * Runs a HashtableChain interface test.
	 * 
	 * @param args Command Lines
	 */
	public static void main(String[] args)
	{
		//	An instance of the HasTableChain class.
		HashtableChain<Integer, Integer> 
		hashTableChain = new HashtableChain<Integer, Integer>() ;
		
		//	Adding 100 keys and values
		for (int i = 0 ; i < 100 ; i++)
		{
			Integer key = keyValueGen(500000000) ;
			hashTableChain.put(key, key) ;
		}
		
		//	Displaying indices, keys, and values.
		System.out.println(hashTableChain) ;
		
		//	Displaying final result of the test.
		System.out.println(hashTableChain.getTestResult()) ;		
	}
}
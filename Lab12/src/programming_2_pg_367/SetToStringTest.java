package programming_2_pg_367;

/**
 * Algorithm:
 * -Create a class SetToStringTest
 * -Create an object of the type HasSet to represent the implementation
 *  of the interface Set.
 * -Override the toString method by adding brackets and comma in between
 *  the data in set HasSet object.
 * -Create the main method:
 * 	Add some elements to the set and display it. 
 */

import java.util.HashSet ;
import java.util.Set ;
/**
 * SetToStringTest overrides the method toString of the interface
 * Set by adding commas and brackets with a StringBuilder object.
 */
public class SetToStringTest<T>
{
	/** setData replaces the implementation code */
	private Set<T> setData = new HashSet<T>() ;
	
	/**
	 * toString extract the data from setData and adds commas and
	 * brackets using a StringBuilder object.
	 * 
	 * @return a String with data in brackets and separated by commas.
	 */
	@Override
	public String toString()
	{
		StringBuilder sb = new StringBuilder() ;
		sb.append("[") ;
		
		for (T data : setData)
		{
			sb.append(data + ", ") ;
		}
		if (sb.length() > 1)
		{
			sb.replace(sb.lastIndexOf(","), sb.length() - 1 ,  "]") ;
		}
		else
		{
			sb.append("]") ;
		}
		return sb.toString() ;
	}
	
	/**
	 * main test the implemented toString.
	 * 
	 * @param args: command lines...
	 */
	public static void main(String[] args)
	{
		SetToStringTest<String> test = new SetToStringTest<String>() ;
		test.setData.add("Bill") ;
		test.setData.add("Jill") ;
		test.setData.add("Ann") ;
		test.setData.add("Sally") ;
		test.setData.add("Bob") ;
		
		System.out.println(test) ;
	}
}
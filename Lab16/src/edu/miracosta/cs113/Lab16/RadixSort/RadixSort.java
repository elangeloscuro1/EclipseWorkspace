package edu.miracosta.cs113.Lab16.RadixSort;
/**
 * Algorithm:
 * 	A) Create a method radixSort that receives an array of
 * 	   integers to be sorted:
 * 		-Traverse the array to find the value with the maximum digits.
 * 		-Create a for loop that runs for the maximum digits in the array.
 * 		 1) Create a currentDigit variable initialized to 1.
 * 		 2) Create a temporal array for the new values.
 * 		 3) Create an array radix for 0 to 9 indices.
 * 		 4) Traverse the array to counts values by place number.
 * 		 5) Add up the values of radix as follow: 
 * 		 6) radix[j] = (radix[j - 1] + radix[j]).
 * 		 7) Copy the array to their corresponding index based on radix
 * 		 8) and after decrementing the current position.
 * 		 9) set the array to be sorted = temporal.
 * 		10) Upgrade currentDigit to currentDigit * 10 ;
 * 		11) Repeat 1-10 for the maximum digit in the array.
 * 		12) Return the sorted array.
 * 	B) Implement the main method:
 * 		-Create a for loop that starts at 10 and multiply by 10 until 100000.
 * 		 Create an array of 15 elements.
 * 		 Initialize the array with a max value of i ( starts at 10).
 * 		 Display the original values.
 * 		 Use the method radixSirt to sirt it.
 * 		 Display the sorted array.
 * 		  		
 * 		 
 */
/**
 * RadixSort is a simple implementation of a Radix Sort.
 * This is done in a static method called radixSort.
 * 
 * @author Angel Tapia <angelTapia07084759@gmail.com>
 * version 1.0
 */
public class RadixSort
{

	/**
	 * Tests radixSort  method.
	 * 
	 * @param args Command Lines
	 */
	public static void main(String[] args)
	{
		// Testing four array with max values of 10, 100, 1000, and 10000.
		for (int test = 10 ; test < 100000; test *= 10)
		{
			System.out.println("Test with a max value of " + test) ;
			int[] ints = new int[15] ;
			
			// Adding random values.
			for (int i = 0; i < ints.length; i++)
			{
				ints[i] = (int) (Math.random() * test) ;
			}
			
			// Displaying original values.
			for (int i = 0; i < ints.length; i++)
			{
				System.out.printf("%-5s", ints[i]) ;
			}			
			System.out.println() ;
			
			// Using the radixSort method.
			ints = radixSort(ints) ;			
			
			// Displaying sorted array.
			for (int i = 0; i < ints.length; i++)
			{
				System.out.printf("%-5s", ints[i]) ;
			}	
			System.out.println("\n") ;
		}
	}
	
	/**
	 * radixSort sorts an array of integers by placing each value
	 * in the array according to their place values.
	 * This is repeated the number of the maximum-digits in the array.
	 * 
	 * @param intArray: the array to be sorted.
	 * 
	 * @return the sorted array.
	 */
	public static int[] radixSort(int[] intArray)
	{
		int maxDigit = Integer.toString(intArray[0]).length() ;		
		
		for (int i = 0; i < intArray.length; i++)
		{			
			int currentDigit = Integer.toString(intArray[i]).length() ;
			if (maxDigit < currentDigit)
			{
				maxDigit = currentDigit ;
			}
		}
		
		int currentDigit = 1 ;
		
		for (int i = 0 ; i < maxDigit ; i++)
		{			
			int[] newInts = new int[intArray.length] ;	
			int[] radix = new int[10] ;			
			
			for (int j = 0 ; j < intArray.length; j++)
			{	
				radix[(intArray[j] / currentDigit) % 10]++ ;
				
			}			
			for (int j = 1; j < radix.length; j++)
			{				
				radix[j] = (radix[j - 1] + radix[j]) ;
			}		
			for (int j = intArray.length - 1 ; j >= 0 ; j--)
			{	
				newInts[--radix[(intArray[j] / currentDigit) % 10]] = intArray[j] ;		
			}			
			intArray = newInts ;			
			currentDigit *= 10 ;			
		}
		return intArray ;
	}
}
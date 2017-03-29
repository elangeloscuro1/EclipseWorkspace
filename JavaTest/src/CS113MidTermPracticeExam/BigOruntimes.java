package CS113MidTermPracticeExam;


import java.util.ArrayDeque ;

public class BigOruntimes
{
	
	
	
	
	public static void main2(String[] args)
	{
		//O(n^2)
		System.out.println("2.1 ==>     O(n^2)     <==") ;//
		
		int[] array1 = {1,0,1,0,1,1,0,0,1,0,0,1} ;
		print(array1) ;System.out.println(bubbleSort(array1)) ; print(array1) ;
		System.out.println() ;
		
		//O(n)
		System.out.println("2.3 ==>     O(n)     <==") ;
		int[] array2 = {1,0,1,0,1,1,0,0,1,0,0,1} ;
		print(array2) ; array2 = oNSortEC(array2) ; print(array2) ;
		System.out.println() ;
		
		
		//O(n)
		System.out.println("2.e ==>     O(n)     <==") ;
		int[] array3 = {1,0,1,0,1,1,0,0,1,0,0,1} ;
		print(array3) ; System.out.println(oNsort3(array3)) ; print(array3) ;
		System.out.println() ;	
	
	}
	/*************************************************************************************
	 * Print array values
	 *************************************************************************************/
	
	public static void print(int[] array)
	{
		for (int i = 0; i < array.length; i++)
		{
			System.out.printf("%-2s", array[i]) ;
			
		}
		System.out.println() ;
	}
	/*************************************************************************************
	 * bubbleSort
	 *************************************************************************************/
	public static int bubbleSort(int[] array)
	{
		int counter = 0 ;
		boolean swapped = true ;
		while (swapped)
		{
			swapped = false ;
			
			for (int i = 1; i < array.length; i++)
			{							
				if (array[i - 1] > array[i])
				{
					int temp = array[i - 1] ;
					array[i - 1] = array[i] ;
					array[i] = temp ;
					swapped = true ;
				}
				counter++ ;				
			}
		}	
		return counter ;
		
	}	
	/*************************************************************************************
	 * oNSort3
	 *************************************************************************************/
	public static int oNsort3(int[] a)
	{
		ArrayDeque<Integer> temp = new ArrayDeque<>(a.length) ;
		int count = 0 ;
		for (int i = 0; i < a.length; i++)
		{
			if (a[i] > 0)
			{
				temp.addLast(a[i]) ;
			}
			else
			{
				temp.addFirst(a[i]) ;
			}
			count++ ;
			
		}
		
		for (int i = 0; i < a.length; i++)
		{
			a[i] = temp.poll() ;
			count++ ;
		}
		return count ;
	}
	/*************************************************************************************
	 * oNSortEC
	 *************************************************************************************/	
	public static int[] oNSortEC(int[] array)
	{
		int[] temp = new int[array.length] ;
		int start = 0 ;
		int end = array.length - 1 ;
		int count = 0 ;//
		
		for (int i = 0; i < array.length; i++)
		{
			if (array[i] > 0)
			{
				temp[end--] = array[i] ;
			}
			else
			{
				temp[start++] = array[i] ;
			}
			
			count++ ;//
		}
		System.out.println(count) ;//		
		return temp ;
	}






}//



















































package Heap;



public class A_Heap
{
	//private int[] array = {9, 2, 5, 6, 7, 3, 4} ;//2 6 3 9 7 5 4 
	private int[] array = {10, 14, 3, 6, 12, 13, 7, 4, 1, 2} ;//1, 2, 7, 4, 3, 13, 10, 14, 6, 12
	
	public void display()
	{
		for (int i = 0; i < array.length; i++)
		{
			
			System.out.print(array[i] + " ") ;
		}
		System.out.println() ;
	}
	
	public void sort()
	{
		int counter = 0 ;
		for (int p = 0 ; p <= (array.length - 2) / 2 ; p++)
		{
			int left = (2 * p) + 1 ;
			int right = (2 * p) + 2 ;			
			int tempP = p ;

			while (tempP >= 0 && left < array.length && array[tempP] > array[left])
			{				
				int temp = array[tempP] ; 
				array[tempP] = array[left] ;
				array[left] = temp ;
				
				left = tempP ;
				tempP = (left - 1) / 2 ;counter++ ;
			}
			
			tempP = p ;
			
			while (tempP >= 0 && right < array.length && array[tempP] > array[right])
			{				
				int temp = array[tempP] ; 
				array[tempP] = array[right] ;
				array[right] = temp ;
				
				right = tempP ;
				tempP = (right - 1) / 2 ;counter++ ;
			}
			counter++ ;
		}
		System.out.println("\n\nCounts: " + counter) ;
	}
	public static void main(String[] args)
	{
		A_Heap test = new A_Heap() ;
		test.display();
		test.sort();
		test.display();
	}
	
	
	

}

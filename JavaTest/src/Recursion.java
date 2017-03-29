
public class Recursion
{
	private int[] temp ;
	private int index ;
	
	public int fibo(int current, int prev, int fib)
	{
		System.out.println(current) ;
		temp[fib - 1] = current  ;
		
		if (fib == 1)
		{
			index++ ;
			return current ;
		}
		return fibo(current + prev, current, fib - 1) ;
		
	}
	
	public int[] fibonacci(int n)
	{
		temp = new int[n] ;
		fibo(1,0,n) ;
		return temp ;
		
	}
	
	// main
	public static void main(String[] args)
	{
		Recursion test = new Recursion() ;
		
		test.fibonacci(10) ;
		
		
		
		
	}

}
















































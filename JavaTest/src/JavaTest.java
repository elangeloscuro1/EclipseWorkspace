

public class JavaTest
{
//	public static int[] sort1(int[] array)
//	{
//		
//		return
//	}
	
	public static void main(String[] args)
	{
		
		
		int[] array = {1,0,1,0,1,1,0,0,1,0,0,1} ;
		
		for (int i = 1; i < array.length; i++)
		{
			for (int j = 0; j < array.length; j++)
			{
				if (array[i] < array[j])
				{
					int temp = array[i] ;
					array[i] = array[j] ;
					array[j] = temp ;
				}
			}
		}
		
		for (int i = 0; i < array.length; i++)
		{
			System.out.println(array[i]) ;
		}
		
/*		for (int i = -5 ; i < 5 ; i++)
		{
			
			
			Algebra test = new Algebra() ;test.setCoefficient(i);
			System.out.printf("[%4s <==> %4s  ]%n", test.getTerm(false), test.getTerm(true)) ;
			
			
			

			
			
			
			
			
			
		Algebra test = new Algebra(i) ;
		Algebra test2 = new Variable(i, 'A') ;
		
		System.out.printf("[%4s <==> %4s  ]", test.getTerm(false), test.getTerm(true)) ;
		System.out.printf("[%4s <==> %4s  ]%n", test2.getTerm(false), test2.getTerm(true)) ;		
		
		System.out.println("--------------------------------------") ;
		}*/
		
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
/*	*//** Instance variables *//*
	private char variable ;
	private int variableCounter ;
	private String expression ;
	
	
	public JavaTest(char variable)
	{
		this.variable = variable ;
	}
	
	public void addVariable()
	{
		if (expression == null)
		{
			this.expression = "" + variable ;
			variableCounter++ ;
		}
		else
		{
			this.expression += " + " + variable ;
		}
	}
	
	public void subtractVariable()
	{
		if (expression == null)
		{
			this.expression = "" + variable ;
		}
		else
		{
			this.expression += " - " + variable ;
		}
	}
	
	@Override
	public String toString()
	{
		return expression ;
	}*/
	
	
	
	
	
	
	
	
	
	
	
	
	
}


















































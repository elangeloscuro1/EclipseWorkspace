package changeDispenser ;

import java.util.ArrayList ;
import java.util.Scanner ;

public class ChangeDispenser
{

	
	
	private static ArrayList<String> test = new  ArrayList<String>() ;
	private static int couter = 0 ;
	private static Scanner scanner  = new Scanner(System.in) ;
	
	
	public static void makeChange(int total, int quaters, int dimes, int nickels, int pennies)
	{

		if (quaters * 25 + dimes * 10 + nickels * 5 + pennies > total)
		{
			return ;
		}
		String temp = String.format("T: %2s     Q: %2s D:%2s N:%2s P:%2s%n",total,quaters,dimes,nickels,pennies) ;
		if (!test.contains(temp))
		{
			test.add(temp) ;
			System.out.printf(++couter + "T: %2s     Q: %2s D:%2s N:%2s P:%2s%n",total,quaters,dimes,nickels,pennies) ;
		}
		
		if (pennies >= 5)
		{
			makeChange(total, quaters, dimes, nickels + 1, pennies - 5) ;
		}
		if (pennies >= 10)
		{
			makeChange(total, quaters, dimes + 1, nickels, pennies - 10) ;
		}
		if (pennies >= 25)
		{
			makeChange(total, quaters + 1, dimes, nickels, pennies - 25) ;
		}
	}	
	


	public static void main(String[] args)
	{
		int option = 0 ;
		
		while (option != -1)
		{
			System.out.println("Enter Amount") ;
			break ;
			
		}
		
		
		
		

	}

}

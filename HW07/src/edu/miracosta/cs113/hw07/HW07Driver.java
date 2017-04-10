package edu.miracosta.cs113.hw07;


import java.io.FileInputStream ;
import java.io.FileNotFoundException ;
import java.util.Scanner ;

public class HW07Driver
{

	public static void main(String[] args)//MorseCodeTable.txt
	{
		MorseCodeTree test = new MorseCodeTree() ;
		Scanner userIn = new Scanner(System.in) ;
		int option = test.menu() ;
		
		while (option != 0)
		{
			switch (option)
			{
			case 1:

				System.out.println(test.morseCodeTable()) ;
				break ;
			case 2:

				System.out.print("Enter file name(w/out the extention): ") ;
				try
				{
					Scanner scan = new Scanner(new FileInputStream(userIn.nextLine().trim() + ".txt")) ;

					String message = "" ;
					while (scan.hasNext())
					{
						message += scan.next() + " " ;
					}
					System.out.println(test.decodeMessage(message)) ;
				}
				catch (FileNotFoundException e)
				{
					System.out.println(e.getMessage()) ;
				}
				break ;
			case 3:
				System.out.print("Enter morse code: ") ;
				System.out.println(test.decodeMessage(userIn.nextLine())) ;
				break ;
			case 4:
				System.out.print("Enter word: ") ;
				System.out.println(test.encodeMessage(userIn.nextLine())) ;
				break ;
			default:
				option = 0 ;
				break ;
			}
			option = test.menu() ;
		}
		userIn.close() ;
	

	}

}

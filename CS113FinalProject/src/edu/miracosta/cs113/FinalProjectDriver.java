package edu.miracosta.cs113;

public class FinalProjectDriver
{	
	
	public static void main(String[] args)
	{      
		
		TravelPlanner test = new TravelPlanner(7) ;
		test.addAirport("A") ;//0
		test.addAirport("B") ;//1
		test.addAirport("C") ;//2
		test.addAirport("D") ;//3
		test.addAirport("E") ;//4
		test.addAirport("F") ;//5
		test.addAirport("G") ;//6
		
		test.addFlight("A", "D",  5) ;		
		test.addFlight("A", "E", 15) ;		
		test.addFlight("A", "F", 10) ;	
		
		test.addFlight("B", "C", 20) ;		
		test.addFlight("B", "F",  5) ;
		
//		test.addFlight("C", "B", 20) ;		
		test.addFlight("C", "D", 10) ;		
		test.addFlight("C", "E",  5) ;	
		
//		test.addFlight("D", "A",  5) ;		
//		test.addFlight("D", "C", 10) ;		
		test.addFlight("D", "E", 20) ;		
		
//		test.addFlight("E", "A", 15) ;		
//		test.addFlight("E", "C",  5) ;
//		test.addFlight("E", "D", 20) ;
		
//		test.addFlight("F", "A", 10) ;		
//		test.addFlight("F", "B",  5) ;	
		
		System.out.println(test) ;
		
		String[] x = test.findCheapestPath(0, 2) ;
		for (String string : x)
		{
			System.out.println(string) ;
		}
		
		System.out.println("---------------------") ;
		
		
		String[] xx = test.findCheapestPath(1, 4) ;
		
		for (String string : xx)
		{
			System.out.println(string) ;
		}

	}

}

import java.util.ArrayList ;
import java.util.Scanner ;

public class TravelPlanner2
{
	protected ArrayList<Flight> flights ;
	
	public TravelPlanner2(Scanner flightsFile)
	{
		flights = new ArrayList<Flight>() ;		
		readFile(flightsFile) ;
	}
	
	private void readFile(Scanner flightsFile)
	{
		Flight tempFlight = new Flight() ;
		int currentLine = 1 ;
		while(flightsFile.hasNextLine() && flightsFile.hasNext())
		{
			switch (currentLine)
			{
			case 1:
				currentLine++ ;
				tempFlight.setSource(flightsFile.nextLine()) ;
				break ;
			case 2:
				currentLine++ ;
				tempFlight.setDestination(flightsFile.nextLine()) ;
				break ;
			case 3:
				currentLine++ ;
				tempFlight.setCost(Double.parseDouble(flightsFile.nextLine())) ;
				flights.add(tempFlight) ;
				tempFlight = new Flight() ;
				break ;
			default:
				currentLine = 1 ;				
				flightsFile.nextLine() ;
				break ;
			}			
		}
		if (currentLine == 3)
		{
			flights.add(tempFlight) ;
		}		
	}
	
	public Flight[] getSortedByFromAToZsource()
	{
		Heap<Flight> temp = new Heap<Flight>()
		{
			@Override
			public int compare(Flight left, Flight right)
			{
				return left.getSource().compareTo(right.getSource()) ;
			}
		};		
		
		for (Flight flight : flights)
		{
			temp.offer(flight) ;
		}
		
		Flight[] sortedFlights = new Flight[temp.size()] ;
		for (int i = 0; i < sortedFlights.length; i++)
		{
			sortedFlights[i] = temp.poll() ;
		}
		return sortedFlights ;		
	}
	
	
	
	public Flight[] getSortedByFromAToZdestination()
	{
		Heap<Flight> temp = new Heap<Flight>()
		{
			@Override
			public int compare(Flight left, Flight right)
			{
				return left.getDestination().compareTo(right.getDestination()) ;
			}
		};		
		
		for (Flight flight : flights)
		{
			temp.offer(flight) ;
		}
		
		Flight[] sortedFlights = new Flight[temp.size()] ;
		for (int i = 0; i < sortedFlights.length; i++)
		{
			sortedFlights[i] = temp.poll() ;
		}
		return sortedFlights ;		
	}
	
	
	
	
	
	
	

}

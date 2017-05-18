

import java.text.ParseException ;
import java.util.ArrayList ;
import java.util.HashSet ;
import java.util.InputMismatchException ;
import java.util.Scanner ;
import java.util.Stack ;

public class TravelPlanner
{
	private String[] airports ;
	private double[][] costMatrix ;
	
	public TravelPlanner(Scanner vertices, Scanner edges) throws ParseException, InputMismatchException
	{
		loadAirportsFromFile(vertices) ;
		loadCostsFromFile(edges) ;
	}

	public String addFlight(int from, int to)
	// Consider: costMatrix[from][to] == Infinity
	// 			 path.length < 2 ;
	{
		String nonstopovers = "From " + airports[from] + " to " + airports[to] + " costs = $" + costMatrix[from][to] ;		
		String cheapestPath = "\nA lower price would be " ;
		
		int[] path = findCheapestPath(from, to) ;
		double cost = 0 ;
		
		for (int i = 0; i < path.length - 1; i++)
		{
			cost += costMatrix[path[i]][path[i + 1]] ;
			cheapestPath += "\nForm " + airports[path[i]] 
									  + " to " + airports[path[i + 1]] + " = $" + costMatrix[path[i]][path[i + 1]] ;
		}
		cheapestPath += "\nTotal: $" + cost ;
		return  nonstopovers + ". " + cheapestPath ;
	}
	
	public void removeFlight()
	{
		
	}
	public void addLocation()
	{
		
	}
	
	/**
	 * Precondition: One airport per line.
	 * 
	 * @param scan: The file input to be read.
	 * @return An array of the airport read from the file.
	 */
	public String[] loadAirportsFromFile(Scanner scan)
	{
		ArrayList<String> airportList = new ArrayList<String>() ;
		while(scan.hasNextLine())
		{
			airportList.add(scan.nextLine()) ;
		}
		this.airports = new String[airportList.size()] ;
		String[] temp = new String[airportList.size()] ;
		
		for (int i = 0; i < airports.length; i++)
		{
			temp[i] = airportList.get(i) ;
			this.airports[i] = airportList.get(i) ;
		}
		return temp ;
	}
	
	/**
	 * Precondition: Read the cost from one airport to another as follow:
	 * 				 A=>A A=>B A=>C
	 * 				 B=>A B=>B B=>C
	 * 				 C=>A C=>B C=>C
	 * 
	 * @param scan: The file input to be read.
	 * @return An double array of the costs read from the file.
	 * @throws ParseException if file is not formatted correctly.
	 * @throws InputMismatchException if file is not formatted correctly.
	 */
	public double[][] loadCostsFromFile(Scanner scan) throws ParseException, InputMismatchException
	{
		this.costMatrix = new double[airports.length][airports.length] ;
		double[][] temp = new double[airports.length][airports.length] ;
		
		int source = 0 ;	
		
		while(scan.hasNextLine())
		{
			Scanner weights =  new Scanner (scan.nextLine()) ;
			int destination = 0 ;
			
			while(weights.hasNext())
			{
				double currentCost = Double.parseDouble(weights.next()) ;
				temp[source][destination] = currentCost != 0 ? currentCost : Double.POSITIVE_INFINITY ;
				costMatrix[source][destination++] = currentCost != 0 ? currentCost : Double.POSITIVE_INFINITY ;
				
			}			
			weights.close() ;
			source++ ;	
		}
		return temp ;
	}
	
	/**
	 * 
	 * @param from: starting location.
	 * @param to: ending location.
	 * @return an array of stopovers in between.
	 */
	public int[] findCheapestPath(int from, int to)
	{
		int[] pred = new int[airports.length] ;
		double[] cost = new double[airports.length] ;
		Stack<Integer> aPath = new Stack<>() ;	
		int[] stopover = null ;
		
		if (dijkstrasAlgorithm(costMatrix , from,  pred, cost))
		{
			aPath.add(to) ;
			
			for (int i = to; i > from; i = pred[i])
			{
				double d = costMatrix[pred[i]][i] ;
				aPath.add(pred[i]) ;
			}

			stopover = new int[aPath.size()] ;

			for (int i = 0; !aPath.isEmpty(); i++)
			{
				stopover[i] = aPath.pop() ;
			}
		}
		return stopover ;
	}
	
	/**
	 * 
	 * @param costs: a matrix cost.
	 * @param start: staring location
	 * @param pred:  predecessor.
	 * @param cost:  costs to each location.
	 */
	public boolean dijkstrasAlgorithm(double[][] costs, int start, int[] pred, double[] cost)
	{
		boolean isFound = false ;
		int numV = costs.length ;
		HashSet<Integer> vMinusS = new HashSet<>(numV) ;
		
		for (int i = 0; i < numV; i++)
		{
			if (i != start)
			{
				vMinusS.add(i) ;
			}
		}
		
		for (int v : vMinusS)
		{
			pred[v] = start ;
			cost[v] = costs[start][v] ;
		}
		
		while (vMinusS.size() != 0)
		{
			double minDist = Double.POSITIVE_INFINITY ;
			int u = -1 ;
			for (int v : vMinusS)
			{
				if (cost[v] < minDist)
				{
					isFound = true ;
					minDist = cost[v] ;
					u = v ;					
				}
			}
			
			if (u == -1 )
			{
				break ;
			}
			
			vMinusS.remove(u) ;
			
			for (int v : vMinusS)
			{
				if (costs[u][v] != Double.POSITIVE_INFINITY)
				{
					double weight = costs[u][v] ;
					if (cost[u] + weight < cost[v])
					{
						cost[v] = cost[u] + weight ;
						pred[v] = u ;
					}
				}				
			}
		}
		return isFound ;
	}
}
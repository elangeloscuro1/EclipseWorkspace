package edu.miracosta.cs113 ;



import java.util.ArrayList ;
import java.util.HashSet ;

public class TravelPlanner
{
	public static final boolean IS_DIRECTED = true ;
	private MatrixGraph graph ;
	private ArrayList<String> locations ;
	
	
	public TravelPlanner(int numAirpots)
	{
		this.locations = new ArrayList<>(numAirpots) ;
		this.graph = new MatrixGraph(numAirpots, IS_DIRECTED) ;
	}
	
	/**
	 * 
	 * @param src
	 * @param dest
	 * @param cost
	 * @return
	 */
	public boolean addFlight(String src, String dest, double cost)
	{
		int srcIndex = this.locations.indexOf(src) ;
		int destIndex = this.locations.indexOf(dest) ;	
				
		if (srcIndex == -1)
		{
			srcIndex = this.locations.lastIndexOf(src) ;
		}
		if (destIndex == -1)
		{
			destIndex = this.locations.lastIndexOf(dest) ;
		}
		this.graph.insert(srcIndex, destIndex, cost) ;
		return true ;		
	}
	
	/**
	 * 
	 * @param src
	 * @param dest
	 * @return
	 */
	public boolean removeFlight(String src, String dest)
	{
		int srcIndex = this.locations.indexOf(src) ;
		int destIndex = this.locations.indexOf(dest) ;
		
		if (srcIndex == -1 || destIndex == -1)
		{
			return false ;
		}
		this.graph.insert(srcIndex, destIndex, 0) ;
		return true ;
	}
	
	/**
	 * 
	 * @param location
	 * @return
	 */
	public boolean addAirport(String location)
	{
		if (!this.locations.contains(location))
		{
			this.locations.add(location) ;
			return true ;
		}
		return false ;		
	}
	
	/**
	 * 
	 * @param src
	 * @param dest
	 * @return 0 if cost is undefined.
	 */
	public double getFlightCost(String src, String dest)
	{
		int srcIndex = this.locations.indexOf(src) ;
		int destIndex = this.locations.indexOf(dest) ;	
		
		if (srcIndex == -1 || destIndex == -1)
		{
			return 0 ;
		}
		if (graph.getEdge(srcIndex, destIndex) == null)
		{
			return 0 ;
		}	
		return this.graph.getEdge(srcIndex, destIndex).getWeight() ;
	}
	
	/**
	 * 
	 * @param from: starting location.
	 * @param to: ending location.
	 * @return an array of stopovers in between. lenght.
	 */
	public String[] findCheapestPath(int from, int to)
	{
		int[] pred = new int[this.graph.getNumV()] ;
		double[] cost = new double[this.graph.getNumV()] ;
		ArrayList<Integer> path = new ArrayList<Integer>() ;
		String[] stopover = null ;
		
		if (dijkstrasAlgorithm(this.graph , from,  pred, cost))
		{			
			for (int i = to; i > from; i = pred[i])
			{
				if (graph.isEdge(pred[i], i))
				{
					path.add(i) ;
				}
			}				
		}
		path.add(from) ;
		stopover = new String[path.size()] ;		
		for (int i = 0 , j = path.size() - 1 ; i < path.size()  ; i++, j--)
		{
			stopover[i] = this.locations.get(path.get(j)) ;
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
	public boolean dijkstrasAlgorithm(MatrixGraph graph, int start, int[] pred, double[] cost)
	{
		boolean isFound = false ;
		int numV = graph.getNumV() ;
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
			
			if (graph.isEdge(start, v))
			{
				cost[v] = graph.getEdge(start, v).getWeight() ;
			}
			else
			{
				cost[v] = Double.POSITIVE_INFINITY ;
			}			
		}
		
		while (vMinusS.size() != 0)
		{
			double minDist = Double.POSITIVE_INFINITY ;
			int u = -1 ;
			for (int v : vMinusS)
			{
				if (cost[v] < minDist)
				{
					System.out.println(cost[v] + "   <  " + minDist) ;
					isFound = true ;
					minDist = cost[v] ;
					u = v ;					
				}				
			}
			if (vMinusS.remove(u))
			{
				for (int v : vMinusS)
				{
					if (graph.isEdge(u, v))
					{
						double weight = graph.getEdge(u, v).getWeight() ;
						if (cost[u] + weight < cost[v])
						{
							cost[v] = cost[u] + weight ;
							pred[v] = u ;
						}
					}
				}
			}
			else
			{
				vMinusS.clear() ;
			}
		}
		return isFound ;
	}

	@Override
	public String toString()
	{
		return graph.toString() ;
	}
	
	
}
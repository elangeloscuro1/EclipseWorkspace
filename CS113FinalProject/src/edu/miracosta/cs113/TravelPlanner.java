
package edu.miracosta.cs113;

import java.util.HashSet ;
import java.util.Scanner ;
import java.util.Stack ;

public class TravelPlanner extends MatrixGraph
{
	private boolean undirectedGraph ;
	
	public TravelPlanner(Scanner vertices, Scanner edges)
	{
		super(vertices, edges) ;
	}

	public boolean isUndirectedGraph()
	{
		return this.undirectedGraph ;
	}
	
	public int findCheapestPath(int from, int to)
	{
		int[] pred = new int[numV] ;
		double[] dist = new double[numV] ;
		int count = 0 ;
		
		dijkstrasAlgorithm(weights , from,  pred, dist) ;
		Stack<Integer> path = new Stack<>() ;
		path.add(to) ;
		for (int i = to ; i > from ; i = pred[i], count++ )
		{
			double d =  weights[pred[i]][i] ;
			path.add(pred[i]) ;			
		}
		
		while (!path.isEmpty())
		{
			System.out.println(path.pop()) ;
		}		
		return count ;
		
	}

	public void addFlight(int from, int to)
	{
		
	}
	
	public void removeFlight()
	{
		
	}
	public void addLocation()
	{
		
	}
	
	public void dijkstrasAlgorithm(double[][] graph, int start, int[] pred, double[] dist)
	{
		int numV = graph.length ;
		HashSet<Integer> vMinusS = new HashSet<>(numV) ;
		
		// Initialize V–S.
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
			dist[v] = graph[start][v] ;
		}
		while (vMinusS.size() != 0)
		{
			double minDist = Double.POSITIVE_INFINITY ;
			int u = -1 ;
			for (int v : vMinusS)
			{
				if (dist[v] < minDist)
				{
					minDist = dist[v] ;
					u = v ;
				}
			}	
			if (u == -1 )
			{
				System.out.println("Failed: " ) ; 
				break;
			}
			
			vMinusS.remove(u) ;
			for (int v : vMinusS)
			{
				if (graph[u][v] != 0 )// Double.POSITIVE_INFINITY)
				{
					double weight = graph[u][v] ;
					if (dist[u] + weight < dist[v])
					{
						dist[v] = dist[u] + weight ;
						pred[v] = u ;
					}
				}				
			}
		}
	}
}
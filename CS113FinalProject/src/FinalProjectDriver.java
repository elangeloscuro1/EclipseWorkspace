
import java.util.HashSet ;
import java.util.Scanner ;

import edu.miracosta.cs113.MatrixGraph ;

public class FinalProjectDriver
{
	public static void main(String[] args)
	{
		Scanner ver = new Scanner("A\nB\nC\nD\nE\nF") ;
		Scanner wei = new Scanner("0  25 10 40  0   50 \n"
				                + "0  0  0  10  20  0 \n"
				                + "0  0  0  20  0   0 \n"
				                + "0  0  0  0   30  0 \n"
				                + "0  0  0  0   0   0 \n"
				                + "0  0  0  15  0   0 \n") ;

		
		MatrixGraph ports = new MatrixGraph(ver, wei) ;
		
//		
//		for (int i = 0; i < ports.vertices.length; i++)
//		{
//			for (int j = 0; j < ports.vertices.length; j++)
//			{
//				if (ports[i][j] == Double.POSITIVE_INFINITY)
//					System.out.printf(" %-5s |", '\u221e') ;
//				else
//				System.out.printf(" %-5s |", ports[i][j]) ;
//			}
//			System.out.println("\n") ;
//		}
//		
//		System.out.println() ;
		int[] pred = new int[ports.vertices.length] ;
		double[] dist = new double[ports.vertices.length] ;
		
		dijkstrasAlgorithm(ports.weights, 0 , pred, dist); 
		for (int i = 0; i < ports.vertices.length; i++)
		{
			System.out.printf(i + "  %-7s%-5s%n%n", dist[i], pred[i]) ;
		}
	}

	public static void main2(String[] args)
	{
		
//		MatrixGraph test = new MatrixGraph() ;
		double[][] ports = new double[5][5] ;
		
		ports[0][0] =  Double.POSITIVE_INFINITY ;
		ports[0][1] = 10 ;
		ports[0][2] =  Double.POSITIVE_INFINITY ;
		ports[0][3] = 30 ;
		ports[0][4] = 100 ;
		
		ports[1][0] =  Double.POSITIVE_INFINITY ;
		ports[1][1] =  Double.POSITIVE_INFINITY ;
		ports[1][2] = 50 ;
		ports[1][3] =  Double.POSITIVE_INFINITY ;
		ports[1][4] =  Double.POSITIVE_INFINITY ;
		
		ports[2][0] =  Double.POSITIVE_INFINITY ;
		ports[2][1] =  Double.POSITIVE_INFINITY ;
		ports[2][2] =  Double.POSITIVE_INFINITY ;
		ports[2][3] =  Double.POSITIVE_INFINITY ;
		ports[2][4] = 10 ;
		
		ports[3][0] =  Double.POSITIVE_INFINITY ;
		ports[3][1] =  Double.POSITIVE_INFINITY ;
		ports[3][2] = 20 ;
		ports[3][3] =  Double.POSITIVE_INFINITY ;
		ports[3][4] = 60 ;
		
		ports[4][0] =  Double.POSITIVE_INFINITY ;
		ports[4][1] =  Double.POSITIVE_INFINITY ;
		ports[4][2] =  Double.POSITIVE_INFINITY ;
		ports[4][3] =  Double.POSITIVE_INFINITY ;
		ports[4][4] =  Double.POSITIVE_INFINITY ;
		
		
		for (int i = 0; i < ports.length; i++)
		{
			for (int j = 0; j < ports.length; j++)
			{
				if (ports[i][j] == Double.POSITIVE_INFINITY)
					System.out.printf(" %-5s |", '\u221e') ;
				else
				System.out.printf(" %-5s |", ports[i][j]) ;
			}
			System.out.println("\n") ;
		}
		
		System.out.println() ;
		int[] pred = new int[5] ;
		double[] dist = new double[5] ;
		
		dijkstrasAlgorithm(ports, 0 , pred, dist); 
		for (int i = 0; i < ports.length; i++)
		{
			System.out.printf(i + "  %-7s%-5s%n%n", dist[i], pred[i]) ;
		}
		
		
	}
	
	public static void dijkstrasAlgorithm(double[][] graph, int start, int[] pred, double[] dist)
	{
		int numV = graph.length ;
		HashSet<Integer> vMinusS = new HashSet<>(numV) ;
		
		// Initialize V–S.
		for (int i = 0; i < numV; i++)
		{
			if (i != start)
			{
				vMinusS.add(i) ;System.out.println("Adding: " + i) ;
			}
		}
		// Initialize pred and dist.
		for (int v : vMinusS)
		{
			pred[v] = start ;System.out.print("Start: " + start + "  v: " + v + "\n") ;
			dist[v] = graph[start][v] ;
		}
		// Main loop
		while (vMinusS.size() != 0)
		{
			// Find the value u in V–S with the smallest dist[u].
			double minDist = Double.POSITIVE_INFINITY ;
			int u = -1 ;
			for (int v : vMinusS)
			{				
				System.out.println("current v: " + v + "  dist[v] < minDist: " + dist[v] + "  <  " + minDist) ;
				if (dist[v] < minDist)
				{
					minDist = dist[v] ;
					u = v ;
				}
			}
			
			if (u == -1 ){System.out.println("Failed: " ) ; break;}/////////////////
			System.out.println("smallest value: " + minDist + "   form u =  " + u + "\n") ;///////////////////////////
			System.out.println(vMinusS) ;/////////////////////////////
			
			// Remove u from vMinusS.
			vMinusS.remove(u) ;			
			// Update the distances.
			for (int v : vMinusS)
			{
				System.out.println("u: " + u + "  v: " + v + "  (u + v = " + (dist[u] + graph[u][v]) + ") < " + dist[v]) ;
				if (graph[u][v] != 0)//Double.POSITIVE_INFINITY)
				{
					double weight = graph[u][v] ;
					if (dist[u] + weight < dist[v])
					{
						dist[v] = dist[u] + weight ;
						pred[v] = u ;
					}
				}				
			}System.out.println() ;////////////////////////////
		}
	}
	
	
//	public static ArrayList<Edge> primsAlgorithm(double[][] graph, int start)
//	{
//		ArrayList<Edge> result = new ArrayList<>() ;
//		int numV = graph.length ;
//		// Use a HashSet to represent V–S.
//		Set<Integer> vMinusS = new HashSet<>(numV) ;
//		// Declare the priority queue.
//		Queue<Edge> pQ = new PriorityQueue<>(numV) ;
//		// Initialize V–S.
//		for (int i = 0; i < numV; i++)
//		{
//			if (i != start)
//			{
//				vMinusS.add(i) ;
//			}
//		}
//		int current = start ;
//		// Main loop
//		while (vMinusS.size() != 0)
//		{
//			// Update priority queue.
//			Iterator<Edge> iter = graph.edgeIterator(current) ;
//			while (iter.hasNext())
//			{
//				Edge edge = iter.next() ;
//				int dest = edge.getDest() ;
//				if (vMinusS.contains(dest))
//				{
//					pQ.add(edge) ;
//				}
//			}
//			// Find the shortest edge whose source is in S and
//			// destination is in V–S.
//			int dest = -1 ;
//			Edge edge = null ;
//			do
//			{
//				edge = pQ.remove() ;
//				dest = edge.getDest() ;
//			}
//			while (!vMinusS.contains(dest)) ;
//			// Take dest out of vMinusS.
//			vMinusS.remove(dest) ;
//			// Add edge to result.
//			result.add(edge) ;
//			// Make this the current vertex.
//			current = dest ;
//		}
//		return result ;
//	}

	
	
	/** Dijkstra's Shortest‐Path algorithm. (Reference page:583)
	@param graph The weighted graph to be searched
	@param start The start vertex
	@param pred Output array to contain the predecessors in the shortest path
	@param dist Output array to contain the distance in the shortest path
	*/
/*	public static void dijkstrasAlgorithm(Graph graph, int start, int[] pred, double[] dist)
	{
		int numV = graph.getNumV() ;
		HashSet<Integer> vMinusS = new HashSet<>(numV) ;
		// Initialize V–S.
		for (int i = 0; i < numV; i++)
		{
			if (i != start)
			{
				vMinusS.add(i) ;
			}
		}
		// Initialize pred and dist.
		for (int v : vMinusS)
		{
			pred[v] = start ;
			dist[v] = graph.getEdge(start, v).getWeight() ;
		}
		// Main loop
		while (vMinusS.size() != 0)
		{
			// Find the value u in V–S with the smallest dist[u].
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
			// Remove u from vMinusS.
			vMinusS.remove(u) ;
			// Update the distances.
			for (int v : vMinusS)
			{
				if (graph.isEdge(u, v))
				{
					double weight = graph.getEdge(u, v).getWeight() ;
					if (dist[u] + weight < dist[v])
					{
						dist[v] = dist[u] + weight ;
						pred[v] = u ;
					}
				}
			}
		}
	}*/
	
	
	

}

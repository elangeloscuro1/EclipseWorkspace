
import java.awt.FlowLayout ;
import java.util.HashSet ;
import java.util.Scanner ;

import javax.swing.JButton ;
import javax.swing.JFrame ;


import edu.miracosta.cs113.TravelPlanner ;

public class FinalProjectDriver extends JFrame
{
	static Scanner ver = new Scanner("0\n1\n2\n3\n4") ;
	static Scanner wei = new Scanner("0  10 0  30  100  \n"
			                + "0  0  50 0   0    \n"
			                + "0  0  0  0   10   \n"
			                + "0  0  20 0   60   \n"
			                + "0  0  0  0   0    \n") ;
	Scanner ver2 = new Scanner("A\nB\nC\nD\nE\nF") ;
	Scanner wei2 = new Scanner("0  25 10 40  0   50 \n"
			                + "0  0  0  10  20  0 \n"
			                + "0  0  0  20  0   0 \n"
			                + "0  0  0  0   30  0 \n"
			                + "0  0  0  0   0   0 \n"
			                + "0  0  0  15  0   0 \n") ;
	
	public FinalProjectDriver()
	{
		TravelPlanner ports = new TravelPlanner(ver2, wei2) ;
		setTitle("ADMIN") ;
		//setSize(800, 800) ;
		setLayout(new FlowLayout());
		for (int i = 0; i < ports.vertices.length; i++)
		{
			add(new JButton(ports.vertices[i])) ;
		}
		pack() ;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE) ;
		setLocationRelativeTo(null);
		setVisible(true);	
	}
	
	public static void main(String[] args)
	{	
		
		//new FinalProjectDriver() ;
		
		TravelPlanner ports = new TravelPlanner(ver, wei) ;
		
		int[] pred = new int[ports.vertices.length] ;
		double[] dist = new double[ports.vertices.length] ;
		
		ports.dijkstrasAlgorithm(ports.weights, 0 , pred, dist); 
		for (int i = 0; i < ports.vertices.length; i++)
		{
			System.out.printf(i + " %-7s%-5s%n%n", dist[i], pred[i]) ;
		}
		
		ports.findCheapestPath(0, 4) ;
		
		
		
		
		
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

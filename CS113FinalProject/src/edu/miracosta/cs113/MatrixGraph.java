package edu.miracosta.cs113 ;
import java.util.ArrayList ;
import java.util.Iterator ;
import java.util.Scanner ;

public class MatrixGraph extends AbstractGraph
{
	public String[] vertices ;
	public double[][] weights ;
	
	public MatrixGraph()
	{
		this.weights = null ;
		this.vertices = null ;
	}
	public MatrixGraph(Scanner vertices, Scanner edges)
	{
		
		loadVerticesFromFile(vertices) ;
		initializeWeights() ;
		loadEdgesFromFile(edges) ;
		
		
//		for (int i = 0; i < this.vertices.length; i++)
//		{
//			for (int j = 0; j < this.vertices.length; j++)
//			{				
//				System.out.print(this.vertices[i] + " ==> " + this.vertices[j] +"  "+ weights[i][j]) ;
//				System.out.println("   <>  " + i + " ==> " + j + " " + weights[i][j]  ) ;
//			}
//		}
//	
	}
	
	private void initializeWeights()
	{
		this.weights = new double[numV][numV] ;
		for (int i = 0; i < this.vertices.length; i++)
		{
			for (int j = 0; j < this.vertices.length; j++)
			{				
				weights[i][j] = Double.POSITIVE_INFINITY ;
			}
		}
	}
	
	private class EdgeIterator implements Iterator<Edge>
	{
		private int indexRow = 0 ;
		private int indexColumn = 0 ; 
		private boolean isNext = false;

		@Override
		public boolean hasNext()
		{
			if (weights == null)
			{
				return false ;
			}
			else
			{
				for (int i = indexRow; i < vertices.length; i++)
				{
					for (int j = indexColumn; j < vertices.length; j++)
					{
						if (weights[i][j] != Double.POSITIVE_INFINITY)
						{
							isNext = true ;
							return true ;
						}
					}
				}
				return false ;
			}
		}

		@Override
		public Edge next()
		{
			if (!isNext)
			{
				throw new IllegalStateException() ;
			}
			isNext = false ;
			return new Edge(indexRow, indexColumn, weights[indexRow][indexColumn]) ;
		}
		
	}
	public MatrixGraph(int numV, boolean directed)
	{
		super(numV, directed) ;
	}

	@Override
	public boolean isEdge(int source, int dest)
	{
		return false ;
	}

	@Override
	public Edge getEdge(int source, int dest)
	{
		return null ;
	}

	@Override
	public Iterator<Edge> edgeIterator(int source)
	{
		return new EdgeIterator() ;
	}

	@Override
	public void loadVerticesFromFile(Scanner scan)
	{
		ArrayList<String> vertixList = new ArrayList<String>() ;
		while(scan.hasNextLine())
		{
			vertixList.add(scan.nextLine()) ;
			this.numV++ ;
		}
		this.vertices = new String[numV] ;
		for (int i = 0; i < vertixList.size(); i++)
		{
			this.vertices[i] = vertixList.get(i) ;
		}
	}

	@Override
	public Edge[] loadEdgesFromFile(Scanner scan)
	{
		ArrayList<Edge> edges = new ArrayList<Edge>() ;
		int source = 0 ;		
		int edgeCounter = 0 ;
		
		while(scan.hasNextLine())
		{
			int destination = 0 ;
			Scanner weights =  new Scanner (scan.nextLine()) ;
			
			while(weights.hasNext())
			{
				double currentWeight = Double.parseDouble(weights.next()) ;
				edges.add(new Edge(destination++, source , currentWeight )) ;
				edgeCounter++ ;
			}
			weights.close() ;
			source++ ;	
		}
		
		Edge[] edgeArray = new Edge[edgeCounter] ;		
		for (int i = 0; i < edges.size(); i++)
		{
			edgeArray[i] = edges.get(i) ;
			int src = edges.get(i).getSource() ;
			int dest = edges.get(i).getDest() ;
			double wieght = edges.get(i).getWeight() ;
			this.weights[src][dest] = wieght == 0 ? Double.POSITIVE_INFINITY : wieght ;
						
		}
		return edgeArray ;
	}


	
//	
//	// String State, String Name, String state and weight
//	private Airport[] readFile(Scanner scan)
//	{
//		ArrayList<Airport> airport = new ArrayList<Airport>() ;
//		while (scan.hasNext())
//		{
//			String state = scan.next() ;
//			double cost = scan.nextDouble() ;
//			airport.add(new Airport(state, cost)) ;
//		}
//		return (Airport[]) airport.toArray() ;
//	}
	
	


	

}

package edu.miracosta.cs113;
/**
 * MatrixGraph.java : Graph Class for Travel Planner Program.
 * 						Implemented using adjacency matrix.
 * 
 * @author Steven Siddall, Angel Tapia, Nathan Tran
 * @version 1.0
 * 
 */
import java.util.Iterator;
import java.util.NoSuchElementException;

public class MatrixGraph extends AbstractGraph
{
	protected double[][] matrix;
	private int numEdges ;
	
	public MatrixGraph(int numVertices, boolean directed)
	{
		super(numVertices, directed);
		this.matrix = new double[numVertices][numVertices];
		this.numEdges = 0;	
	}

	@Override
	public int getNumV()
	{
		return this.getNumVertices();
	}
	public int getNumEdges()
	{
		return this.numEdges;
	}
	//NEED TO CHECK
	@Override
	public void insert(Edge edge)
	{
		double newWeight = edge.getWeight();
		if(matrix[edge.getSource()][edge.getDest()] == 0)
		{
			numEdges++;
		}
		matrix[edge.getSource()][edge.getDest()] = newWeight;
		matrix[edge.getDest()][edge.getSource()] = newWeight;
	}
	public void insert(int source, int dest, double weight)
	{
		Edge newEdge = new Edge(source, dest, weight);
		insert(newEdge);
	}

	@Override
	public boolean isEdge(int source, int dest)
	{
		//if weight does not exist
		if(matrix[source][dest] == 0)
		{
			return false;
		}
		else	//Edge exists
		{
			return true;
		}
	}

	@Override
	public Edge getEdge(int source, int dest)
	{
		if(matrix[source][dest] == 0)
		{
			return null;
		}
		else
		{
			Edge edge;
			double edgeWeight;
			edgeWeight = matrix[source][dest];
			edge = new Edge(source, dest, edgeWeight);
			return edge;
		}
	}
	
	@Override
	public Iterable<Double> edgeIterator(int source)
	{
		return new MatrixIterator(source);
	}
	protected class MatrixIterator implements Iterator<Double>, Iterable<Double>
	{
		private int x;
		private int y = 0;
		
		MatrixIterator(int x)
		{
			this.x = x;
		}
		@Override
		public Iterator<Double> iterator()
		{
			return this;
		}

		@Override
		public boolean hasNext()
		{
			while(y < getNumVertices())
			{
				if(matrix[x][y] == 0)
				{
					return true;
				}
				y++;
			}
			return false;
		}

		@Override
		public Double next()
		{
			if(!hasNext())
			{
				throw new NoSuchElementException();
			}
			return matrix[x][y++];
		}
        public void remove() {
            throw new UnsupportedOperationException();
        }
	}
	
	public String toString()
	{
		/*
		//Iterable<Edge> iter = new EdgeIterator(getNumOf)
		
		System.out.println("Vertices : " + getNumVertices() + " Edges: " + getNumEdges());
		String aString = "";
		for(int row = 0; row < getNumVertices(); row++)
		{
		   for(int col = 0; col < getNumVertices(); col++)
		   {
			   if(matrix[row][col] == 0)
			   {
				   System.out.print("0    ");
			   }
			   else
			   {
				   System.out.print(matrix[row][col] + "    ");
			   }
		    }
		   System.out.println();
		 }
		return aString;
		*/
        for (int i = 0; i < getNumVertices(); i++) 
        {
            for (int j = 0; j < getNumVertices(); j++)
            {
                System.out.print(matrix[i][j] + "   ");
            }
            System.out.println();
        }
        return "";
	}
	
}
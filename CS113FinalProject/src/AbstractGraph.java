import java.util.Scanner ;

/**Reference page: 554
 * Abstract base class for graphs. A graph is a set of vertices and
 * a set of edges. Vertices are represented by integers
 * from 0 to n ‐ 1. Edges are ordered pairs of vertices.
 */
/**
 * 
 * @author Group D
 *
 */
public abstract class AbstractGraph implements Graph
{
	// Data Fields
	/** The number of vertices */
	private int numV ;
	/** Indicates whether this is a directed graph */
	private boolean directed ;

	// Constructor
	/**
	 * Construct a graph with the specified number of vertices and the directed
	 * flag. If the directed flag is true, this is a directed graph.
	 * 
	 * @param numV The number of vertices
	 * @param directed The directed flag
	 */
	public AbstractGraph(int numV, boolean directed)
	{
		this.numV = numV ;
		this.directed = directed ;
	}

	// Accessor Methods
	/**
	 * Return the number of vertices.
	 * 
	 * @return The number of vertices
	 */
	public int getNumV()
	{
		return numV ;
	}

	/**
	 * Return whether this is a directed graph.
	 * 
	 * @return true if this is a directed graph
	 */
	public boolean isDirected()
	{
		return directed ;
	}

	// Other Methods
	/**
	 * Load the edges of a graph from the data in an input file. 
	 * The file should contain the source, the destination and weight (optional).
	 * 
	 * @param scan The Scanner connected to the data file
	 */
	public abstract void loadEdgesFromFile(Scanner scan) ;
	
	/** Factory method to create a graph and load the data from an input
	 * file. The first line of the input file should contain the number
	 * of vertices. The remaining lines should contain the edge data as
	 * described under loadEdgesFromFile.
	 * @param scan The Scanner connected to the data file.
	 * @param isDirected true if this is a directed graph, false otherwise.
	 */
	public abstract Graph createGraph(Scanner scan, boolean isDirected) ;
}
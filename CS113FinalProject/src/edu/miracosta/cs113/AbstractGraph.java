package edu.miracosta.cs113 ;
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
	/** The number of vertices */
	protected int numV ;
	/** Indicates whether this is a directed graph */
	private boolean directed ;

	/**
	 * Default constructor initializes a directed graph that
	 * contains 0 vertex.
	 */
	public AbstractGraph()
	{
		this.numV = 0 ;
		this.directed = true ;
	}
	
	/**
	 * Constructs a graph with the specified number of vertices
	 * and specifies the graph as a directed true of false.
	 * 
	 * @param numV The number of vertices
	 * @param directed declares a directed graph.
	 */
	public AbstractGraph(int numV, boolean directed)
	{
		this.numV = numV ;
		this.directed = directed ;
	}

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
	
	/**
	 * Load the vertices for a graph from the data in an input file.
	 * (one vertex per line)
	 * PRECONDITION: count the number of vertices by incrementing numV.
	 * 
	 * @param scan The Scanner connected to the data file
	 * @return an array of object extracted from the data file.
	 */
	public abstract void loadVerticesFromFile(Scanner scan) ;
	
	/**
	 * Load the edges of a graph from the data in an input file. 
	 * The file should contain the source, the destination and weight (optional).
	 * 
	 * @param scan The Scanner connected to the data file
	 */
	public abstract Edge[] loadEdgesFromFile(Scanner scan) ;
	
}
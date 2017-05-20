package edu.miracosta.cs113;
/**
 * WeightedGraph.java : Graph Class that acts as the superclass for
 * 						MatrixGraph. Allows for implementation of some
 * 						methods for the graph interface and leaves other
 * 						methods for the more specific subclass (MatrixGraph).
 * 
 * @author Steven Siddall, Angel Tapia, Nathan Tran
 * @version 1.0
 * 
 */
import java.util.*;
import java.io.*;

public abstract class AbstractGraph implements Graph
{
	private boolean directed;
	private int numVertices;
	
    /**
     * Constructor, constructs a graph with the specified number of
     * 				vertices and the directed flag. If the directed flag
     * 				is true, this is a directed graph.
     *
     * @param numVertices - number of vertices in graph.
     * @param directed - true for directed graph, false for undirected
     */
	public AbstractGraph(int numVertices, boolean directed)
	{
		this.numVertices = numVertices;
		this.directed	= directed;
	}
    /**
     * Accessor, returns number of vertices
     *	
     * @return numVertices - number of vertices in graph.
     */
    public int getNumVertices()
    {
        return this.numVertices;
    }
    /**
     * Accessor, returns whether this graph is directed or not
     *	
     * @return directed - true if graph is directed, false otherwise.
     */
    public boolean isDirected()
    {
        return this.directed;
    }
    /*
    public abstract void loadEdgesFromFile()
    {
    	//Yet To implement.
    	
    }
    */
}
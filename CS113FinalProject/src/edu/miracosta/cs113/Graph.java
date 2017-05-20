package edu.miracosta.cs113;
/**
 * Graph.java : Interface for AbstractGraph class and by extension,
 * 				MatrixGraph
 * 
 * @author Steven Siddall, Angel Tapia, Nathan Tran
 * @version 1.0
 * 
 */
import java.util.*;

public interface Graph
{
	/**
	 * Accessor, gets number of vertices in graph
	 * @return	returns number of vertices
	 */
	int getNumV();
	
	/**
	 * Method determines whether graph is directed or not.
	 * @return	returns true if directed, false otherwise.
	 */
	boolean isDirected();
	
	/**
	 * Inserts new edge into graph.
	 * @param	edge to be inserted
	 */
	void insert(Edge edge);
	
	/**
	 * Determines whether edge exists.
	 * @param	source - source vertex
	 * @param   dest   - destination vertex
	 * @return	returns true if edge exists, false otherwise.
	 */
	boolean isEdge(int source, int dest);
	
	/**
	 * Method which returns the edge between two vertices.
	 * @param	source - source vertex
	 * @param   dest   - destination vertex
	 * @return	returns Edge or Edge with weight of Double.POSITIVE_INFINITY
	 * 			if no edge exists.
	 */
	Edge getEdge(int source, int dest);
	
	/**
	 * Return an iterable object to the edges connected to a given vertex
	 * @param	source - source vertex
	 * @return	returns an iterable<Integer> to the vertices connected to source
	 */
	Iterable<Double> edgeIterator(int source);
}
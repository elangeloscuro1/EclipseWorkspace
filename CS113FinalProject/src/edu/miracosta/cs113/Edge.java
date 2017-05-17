package edu.miracosta.cs113 ;
/**Reference page: 549
 * 
 * Edge represents the weight of a graph implementation.
 * Edge representation can be denoted as follow:
 * source ==> destination = weight
 */
/**
 * 
 * @author Group D
 *
 */
public class Edge
{
	/** Default weight = 1.0 */
	public static final double DEFAULT_WEIGHT = 1.0 ;
	/** The destination vertex for an edge */
	private int dest ;
	/** The source vertex for an edge */
	private int source ;
	/** The weight of an edge */
	private double weight ;
	
	/**
	 * Constructs an Edge from source to destination. Sets the weight to 1.0
	 * @param dest: The destination vertex for the edge
	 * @param source: The source vertex for the edge
	 */
	public Edge(int dest, int source)
	{
		this.dest = dest ;
		this.source = source ;
		this.weight = DEFAULT_WEIGHT ;//
	}
	
	/**
	 * Constructs an Edge from source to destination. Sets weight to the specified value.
	 * 
	 * @param dest: The destination vertex for the edge
	 * @param source: The source vertex for the edge
	 * @param weight: The weight for the edge
	 */
	public Edge(int dest, int source, double weight)
	{
		this.dest = dest ;
		this.source = source ;
		this.weight = weight ;
	}	
	
	/**
	 * Accessor for the destination vertex.
	 * 
	 * @return the destination vertex.
	 */
	public int getDest()
	{
		return this.dest ;
	}
	
	/**
	 * Accessor for the source vertex.
	 * 
	 * @return the source vertex.
	 */
	public int getSource()
	{
		return this.source ;
	}
	
	/**
	 * Accessor for the weight.
	 * 
	 * @return the weight of the edge.
	 */
	public double getWeight()
	{
		return this.weight ;
	}
	
	/**IMPLEMENTATION IS NEEDED.
	 * (Page 549: Returns the hash code for an edge. The hash code depends only
	 * on the source and destination)
	 * @return
	 */
	@Override
	public int hashCode()
	{
		return super.hashCode() ;
	}
	
	/**
	 * builds a string representation of this edge.
	 * 
	 * @return a string representation of the edge.
	 */
	@Override
	public String toString()
	{
		return String.format("DESTINATION: %-5s SOURCE: %-5s WEIGHT: %-5s", dest, source, weight) ;
	}
	/**
	 * Compares the destination, source, and weight of this and another edge.
	 * 
	 * @return true if all of the values are equivalent; false otherwise.
	 */
	@Override
	public boolean equals(Object obj)
	{
		if (obj == null || obj.getClass() != this.getClass())
		{
			return false ;
		}
		Edge other = (Edge) obj ;
		return this.dest == other.dest
			&& this.source == other.source
			&& this.weight == other.weight ;
		
	}
}

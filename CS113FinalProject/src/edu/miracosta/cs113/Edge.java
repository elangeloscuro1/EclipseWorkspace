package edu.miracosta.cs113;
/**
 * Edge.java : Edge Class for use in Graphs.
 * 
 * @author Steven Siddall, Angel Tapia, Nathan Tran
 * @version 1.0
 * 
 */
public class Edge implements Comparable<Edge>
{
	private static final double DEFAULT_WEIGHT = 1.0;
	//Data Fields
	private int source; //The source vertex for an edge
	private int dest;	//Destination
	protected double weight; //The weight
	private Edge reverse;
	
	/**
	 * Constructor, constructs an edge from source to dest with default
	 * 				weight of DEFAULT_WEIGHT;
	 * @param source - source vertex
	 * @param dest   - destination vertex
	 */
	public Edge(int source, int dest)
	{
        if (source < 0)
        {
        	throw new IllegalArgumentException("Source vertex index must be non-negative.");
        }
        else if (dest < 0)
    	{
        	throw new IllegalArgumentException("Destination vertex index must be nonnegative.");
    	}
        else
        {
	        this.source = source;
	        this.dest = dest;
	        this.weight = DEFAULT_WEIGHT;
        }
	}
	/**
	 * Constructor, constructs an edge from source to dest with give weight.
	 * @param source - source vertex
	 * @param dest   - destination vertex
	 * @param weight - weight of edge
	 */
	public Edge(int source, int dest, double weight)
	{
        if (source < 0)
        {
        	throw new IllegalArgumentException("Source vertex index must be non-negative.");
        }
        else if (dest < 0)
    	{
        	throw new IllegalArgumentException("Destination vertex index must be nonnegative.");
    	}
        else if(Double.isNaN(weight))
        {
        	throw new IllegalArgumentException("Weight is not a number.");
        }
        else
        {
        	this.source = source;
	        this.dest = dest;
	        this.weight = weight;
        }
	}
	public void setWeight(double weight)
	{
		this.weight = weight;
	}
	/**
	 * Accessor, returns the edge with source and dest locations switched.
	 * @return reverse - returns edge with source and dest switched.
	 */
	public Edge getReverse()
	{
		return this.reverse;
	}
	/**
	 * Accessor, returns destination vertex.
	 * @return dest - destination vertex.
	 */
	public int getDest()
	{
		return this.dest;
	}
	/**
	 * Accessor, returns source vertex.
	 * @return source - source vertex.
	 */
	public int getSource()
	{
		return this.source;
	}
	/**
	 * Accessor, returns weight of edge.
	 * @return weight - weight of edge.
	 */
	public double getWeight()
	{
		return this.weight;
	}
    /**
     * toString method, returns a string representation of this edge.
     *
     * @return a string representation of this edge
     */
    public String toString()
    {
        return String.format("%d-%d %.5f", source, dest, weight);
    }

	/**
	 * compareTo method for Edge objects. Compares the weights of both edges.
	 * @param otherEdge - edge to compare to.
	 * @return
	 */
	@Override
	public int compareTo(Edge otherEdge)
	{
		return Double.compare(this.weight, otherEdge.weight);
	}
}
package edu.miracosta.cs113Reference.book ;

/**Reference page: 549
 * 
 * @author ADTV
 *
 */
public class Edge
{
//	public static final double DEFAULT_WEIGHT = 1.0 ;
	private int dest ;
	private int source ;
	private double weight ;
	
	public Edge(int dest, int source)
	{
		this.dest = dest ;
		this.source = source ;
//		this.weight = DEFAULT_WEIGHT ;
	}
	public Edge(int dest, int source, double weight)
	{
		this.dest = dest ;
		this.source = source ;
		this.weight = weight ;
	}	
	
	public int getDest()
	{
		return dest ;
	}
	public int getSource()
	{
		return source ;
	}
	public double getWeight()
	{
		return weight ;
	}
	
	@Override
	public int hashCode()
	{
		// TODO Auto-generated method stub
		return super.hashCode() ;
	}
	@Override
	public String toString()
	{
		// TODO Auto-generated method stub
		return super.toString() ;
	}
	@Override
	public boolean equals(Object obj)
	{
		// TODO Auto-generated method stub
		return super.equals(obj) ;
	}
	
	
	
}











/**
 * Flight represents a flight that contains a source, a destination, and cost.
 */
/**
 * @author Group D
 * @version 1.0
 */

public class Flight implements Comparable<Flight>
{
	/** The starting location of the flight. */
	private String source ;
	/** The ending location of the flight. */
	private String destination ;
	/** The cost of the flight. */
	private double cost ;
	
	/**
	 * Default constructor initializes fields to Unknown and infinity.
	 */
	public Flight()
	{
		this.source = "UNKNOWN" ;
		this.destination = "UNKNOWN" ;
		this.cost = Double.POSITIVE_INFINITY ;
	}
	
	/**
	 * Copy constructor.
	 * 
	 * @param otherFlight: the flight to be copied.
	 */
	public Flight(Flight otherFlight)
	{
		this.source = otherFlight.source ;
		this.destination = otherFlight.destination ;
		this.cost = otherFlight.cost ;
	}
	
	/**
	 * Full Constructor
	 * 
	 * @param source: the starting location of the flight.
	 * @param destination: the ending location of the flight.
	 * @param cost: the cost of the flight.
	 */
	public Flight(String source, String destination, double cost)
	{
		this.source = source ;
		this.destination = destination ;
		this.cost = cost ;
	}	

	/**
	 * Accessor for source.
	 * 
	 * @return The source of the flight.
	 */
	public String getSource()
	{
		return source ;
	}

	/**
	 * Mutator for source.
	 * 
	 * @param source: The source of the flight.
	 */
	public void setSource(String source)
	{
		this.source = source ;
	}

	/**
	 * Accessor for destination.
	 * 
	 * @return the destination of the flight.
	 */
	public String getDestination()
	{
		return destination ;
	}

	/**
	 * Mutator for destination.
	 * 
	 * @param destination: the destination of the flight.
	 */
	public void setDestination(String destination)
	{
		this.destination = destination ;
	}

	/**
	 * Accessor for cost.
	 * 
	 * @return the cost of the flight.
	 */
	public double getCost()
	{
		return cost ;
	}

	/**
	 * Mutator for cost.
	 * 
	 * @param cost: The cost for the flight.
	 */
	public void setCost(double cost)
	{
		this.cost = cost ;
	}	

	/**
	 * Compares two flights by natural order lexicographically.
	 * 
	 * @param o: the flight to be compared.
	 * @return 1 if this flight is before the other (lexicographically)
	 *        -1 if this flight is after the other (lexicographically)
	 *         0 if this flights are equivalent.
	 */
	@Override
	public int compareTo(Flight o)
	{
		if (source.compareTo(o.source) == 0)
		{
			if (destination.compareTo(o.destination) == 0)
			{
				if (cost > o.cost)
				{
					return 1 ;
				}
				if (cost < o.cost)
				{
					return -1 ;
				}
				return 0 ;
			}
			return destination.compareTo(o.destination) ;
		}
		return source.compareTo(o.source) ;
	}
	
	/**IMPLEMENTATION IS NEEDED.
	 * 
	 * @return
	 */
	@Override
	public int hashCode()
	{
		return super.hashCode() ;
	}
	
	/**
	 * builds a string representation of this Flight.
	 * 
	 * @return a string representation of this Flight.
	 */
	@Override
	public String toString()
	{
		return String.format("From: %s %nTo: %s %n$%-5s", source, destination, cost) ;
	}
	
	/**
	 * Compares the source, destination, and cost of this and another edge.
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
		Flight other = (Flight) obj ;
		return this.destination.equals(other.destination)
			&& this.source.equals(other.source)
			&& this.cost == other.cost ;
	}
}
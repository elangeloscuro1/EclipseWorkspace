
public class Airport implements Comparable<Airport>
{
	private String state ;
	private double cost ;
	
	public Airport(String state, double cost)
	{
		this.state = state ;
		this.cost = cost ;
	}
	
	public String getState()
	{
		return state ;
	}

	public void setState(String state)
	{
		this.state = state ;
	}

	public double getCost()
	{
		return cost ;
	}

	public void setCost(double cost)
	{
		this.cost = cost ;
	}

	@Override
	public int compareTo(Airport o)
	{
		return state.compareTo(o.state) ;
	}
	
	
	
	
	
}

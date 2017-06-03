package C19_2_RaceConditionsAndThreadSynchronization ;

public class Counter
{
	private int counter ;

	public Counter()
	{
		counter = 0 ;
	}

	public int value()
	{
		return counter ;
	}

	public /*synchronized*/ void increment()
	{
		int local ;
		local = counter ;
		local++ ;
		counter = local ;
	}
}

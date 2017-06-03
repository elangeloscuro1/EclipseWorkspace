package C19_2_RaceConditionsAndThreadSynchronization ;

public class RaceConditionTest extends Thread
{
	//Stores a reference to a single Counter object.
	private Counter countObject ;

	public RaceConditionTest(Counter ctr)
	{
		countObject = ctr ;
	}

	public void run()
	{
		countObject.increment() ;// Invokes the code in Display 19.4 where the race condition occurs.
	}

	public static void main(String[] args)
	{
		int i ;
		Counter masterCounter = new Counter() ;// The single instance of the Counter object.
		RaceConditionTest[] threads = new RaceConditionTest[30000] ;// Array of 30,000 threads.
		
		System.out.println("The counter is " + masterCounter.value()) ;
		
		for (i = 0; i < threads.length; i++)
		{
			//Give each thread a reference to the single Counter object and start each thread.
			threads[i] = new RaceConditionTest(masterCounter) ;
			threads[i].start() ;
		}
		
		// Wait for the threads to finish
		for (i = 0; i < threads.length; i++)
		{
			try
			{
				threads[i].join() ;// Waits for the thread to complete.
			}
			catch (InterruptedException e)
			{
				System.out.println(e.getMessage()) ;
			}
		}
		
		System.out.println("The counter is " + masterCounter.value()) ;
	}
}

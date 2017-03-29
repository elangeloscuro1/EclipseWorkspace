package printerQueue ;

import java.util.Scanner ;

/**
 * PrinterTestSetup contains method to set up a number of printers and
 * a number of print job test.
 */
/**
 * @author Angel Tapia
 */
public class PrinterTestSetup
{
	/** Maximum of pages that a printer can take. */
	public static final int MAXIMUM_PAGES = 50 ;
	/** Keyboard input to take values from the user. */
	public static final Scanner scan = new Scanner(System.in) ;

	/** printers represents an array of printers to test. */
	private PrinterQueue[] printers ;
	/** totalPrintJobs represents the total of print jobs to test. */
	private int totalPrintJobs ;
	/** show displays details of the test. */
	private boolean show ;
	/** delayTime creates delay time in milliseconds. */
	private int delayTime ;

	/**
	 * Default constructor  initializes instance variable to default values.
	 */
	public PrinterTestSetup()
	{
		this.printers = null ;
		this.totalPrintJobs = 0 ;
		this.show = false ;
		this.delayTime = 0 ;
	}

	/**
	 * Full constructor takes in values for instance variable.
	 * 
	 * @param printers: number of printers to test.
	 * @param totalPrintJobs: total of print jobs to test.
	 * @param show: show the details of the test.
	 * @param delayTime: delay time in milliseconds.
	 */
	public PrinterTestSetup(int printers, int totalPrintJobs, boolean show, int delayTime)
	{
		this.setPrinters(printers) ;
		this.totalPrintJobs = totalPrintJobs ;
		this.show = show ;
		this.delayTime = delayTime ;
	}
	
	/**
	 * Setter for printers.
	 * 
	 * @param printers: number of printers to set up.
	 * @return the number of printers that was set up.
	 * @throws IllegalArgumentException if printers < 1.
	 */
	public int setPrinters(int printers)
	{
		if (printers < 1)
		{
			throw new IllegalArgumentException(">" + printers + "<") ;
		}

		this.printers = new PrinterQueue[printers] ;
		int min = 0 ;

		for (int i = 1; i < printers; i++)
		{
			this.printers[i - 1] = new PrinterQueue("PRINTER<" + i + ">", min + 1, min + 10) ;
			min = i * 10 ;
		}
		this.printers[printers - 1] = new PrinterQueue("PRINTER<" + printers + ">", min + 1, MAXIMUM_PAGES) ;
		return printers ;
	}
	
	/**
	 * Setter for totalPrintJobs.
	 * 
	 * @param totalPrintJobs: the number of print jobs to test.
	 * @return the number of print jobs to test.
	 * @throws IllegalArgumentException if totalPrintJobs < 1.
	 */
	public int setTotalPrintJobs(int totalPrintJobs)
	{
		if (totalPrintJobs < 1)
		{
			throw new IllegalArgumentException(">" + totalPrintJobs + "<") ;
		}
		return this.totalPrintJobs = totalPrintJobs ;
	}
	
	/**
	 * Setter for show.
	 * 
	 * @param show: if true, displays details of the test.
	 * @return the value of show.
	 */
	public boolean setShow(boolean show)
	{
		return this.show = show ;
	}

	/**
	 * Setter for delayTime.
	 * 
	 * @param delayTime: the time in  millisecond to delay each step.
	 * @return the value of delayTime.
	 * @throws IllegalArgumentException if delayTime < 1.
	 */
	public int setDelayTime(int delayTime)
	{
		if (delayTime < 1)
		{
			throw new IllegalArgumentException(">" + delayTime + "<") ;
		}
		return this.delayTime = delayTime ;
	}

	/**
	 * Displays a menu option to run a test or create a new test.
	 * 
	 * @return the number of the chosen option.
	 */
	public int menu()
	{
		System.out.println("-------------  MENU -------------") ;
		System.out.println("|   [1] start default test       |") ;
		System.out.println("|   [2] start new test           |") ;
		System.out.println("|   [0] exit                     |") ;
		System.out.println("----------------------------------") ;
		return getValidInt(0, 2) ;
	}

	/**
	 * Validates an integer value between the specified values.
	 * 
	 * @param min: minimum valid integer to choose.
	 * @param max: maximum valid integer to choose.
	 * @return a valid integer between min and max.
	 */
	public int getValidInt(int min, int max)
	{
		int input = 0 ;
		boolean isValid = false ;
		while (!isValid)
		{
			try
			{
				input = Integer.parseInt(scan.next()) ;
				if (input < min || input > max)
				{
					throw new Exception() ;
				}
				isValid = true ;
			}
			catch (Exception e)
			{
				System.out.println("Please enter number from " + min + " to " + max + ".") ;
			}
		}
		return input ;
	}
	
	/**
	 * Delayer specified by delayTime and show.
	 */
	private void delay()
	{
		if (this.show)
		{
			try
			{
				Thread.sleep(this.delayTime) ;
			}
			catch (Exception e)
			{
				System.out.println(e.getMessage()) ;
			}
		}
	}
	
	/**
	 * Sets up and run a test with three printers.
	 */
	public void setDefaultTest()
	{
		this.printers = new PrinterQueue[3] ;
		this.printers[0] = new PrinterQueue("SMALL", 1, 10) ;
		this.printers[1] = new PrinterQueue("MIDIUM", 11, 20) ;
		this.printers[2] = new PrinterQueue("LARGE", 21, Integer.MAX_VALUE) ;
		this.totalPrintJobs = 100 ;
		this.show = true ;
		System.out.println(toString()) ;
		addPrintJobs() ;
		printAll() ;
		displayResults() ;
	}
	
	/**
	 * Adds and displays,if show is true, print jobs to the printers.
	 */
	public void addPrintJobs()
	{
		if (this.printers == null)
		{
			System.out.println("Could not add print jobs!") ;
			return ;
		}
		System.out.print(show ? "\n============ ADDING PRINT JOBS   ===========\n" : "") ;
		for (int i = 0; i < this.totalPrintJobs; i++)
		{
			int pages = (int) (Math.random() * MAXIMUM_PAGES) + 1 ;
			for (PrinterQueue printerQueue : printers)
			{
				if (printerQueue.addPrintJob(new PrintJob(pages)) && show)
				{
					System.out.printf("%-3s was added to %s%n", pages, printerQueue.getPrinterID()) ;
					delay() ;
				}
			}
		}
	}
	
	/**
	 * Prints all of the print jobs, and displays details if show is true.
	 */
	public void printAll()
	{
		if (this.totalPrintJobs == 0)
		{
			System.out.println("There is nothig to print!") ;
			return ;
		}

		System.out.print(show ? "\n============ PRINTING PRINT JOBS ===========\n" : "") ;
		int allDone = 0 ;

		while (allDone < this.printers.length)
		{
			allDone = 0 ;
			for (PrinterQueue printerQueue : this.printers)
			{
				PrintJob temp = printerQueue.printNext() ;
				if (show && temp != null && temp.isJobDone())
				{
					System.out.printf("Time: %-3s  Printer ID: %-12s >>   %S%n", 
							PrintJob.getTime(), printerQueue.getPrinterID(), temp) ;
					delay() ;
				}
				allDone = printerQueue.isEmpty() ? (allDone + 1) : allDone ;
			}
			PrintJob.incrementTime() ;
		}
	}

	/**
	 * Displays the ending result of the test.
	 */
	public void displayResults()
	{
		if (this.printers == null)
		{
			System.out.println("Printers no found!") ;
			return ;
		}

		System.out.println("============ RESULTS:            ===========") ;
		for (PrinterQueue printerQueue : this.printers)
		{
			System.out.println(printerQueue.getPrinterID() 
					+ " TEST TIME :" + printerQueue.getPrintingTime()) ;
			delay() ;
		}
	}	
	
	/**
	 * Builds a string containing all of the printers information.
	 * 
	 * @return a string containing all of the printers information.
	 */
	@Override
	public String toString()
	{
		if (this.printers == null)
		{
			return "Printers no found!" ;
		}
		String temp = "============ PRINTERS INFO       ===========\n" ;
		for (PrinterQueue printerQueue : this.printers)
		{
			temp += printerQueue + "\n" ;
		}
		return show ? temp : "" ;
	}
	
	/**
	 * Compares the values of printers
	 * 
	 * @return true if all of the printers are equivalent.
	 */
	@Override
	public boolean equals(Object obj)
	{
		if (obj == null || getClass() != obj.getClass())
		{
			return false ;
		}
		PrinterTestSetup other = (PrinterTestSetup) obj ;

		if (this.printers.length != other.printers.length)
		{
			return false ;
		}

		for (int i = 0; i < this.printers.length; i++)
		{
			if (this.printers[i] != other.printers[i])
			{
				return false ;
			}
		}
		return true ;
	}
}
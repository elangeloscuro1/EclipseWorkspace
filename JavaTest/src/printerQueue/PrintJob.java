package printerQueue;

/**
 * PrintJob represent a print job that can be of any number
 * of pages and can store the time that the print job is
 * started and calculate the time that the print job is ended.
 */
/**
 * @author Angel Tapia
 */
public class PrintJob
{
	/** PAGES_PER_TIME represents the maximum possible pages per minus to be printed */
	public static final int PAGES_PER_TIME = 10 ;
	
	/** time represents the time that all of the print jobs are related to. */
	private static int time = 0 ;	
	/** numberOfPages represents the number of pages per print job . */
	private int numberOfPages ;
	/** printStartTime represents the time that a print job starts printing */
	private int printStartTime ;// -1 indicates have not started yet.
	
	/**
	 * Default constructor initializes instance variables to 
	 * zero pages and -1 print start time.
	 * (-1 indicates that the PrintJob has not been printed yet.)
	 */
	public PrintJob()
	{
		this.numberOfPages = 0 ;
		this.printStartTime = -1 ;
	}
	
	/**
	 * Constructor that receives the number of pages to be printed.
	 * printStartTime is initialized to -1 to indicate that 
	 * the print job has not been done yet.)
	 * 
	 * @param numberOfPages represents the number of pages to be printed.
	 */
	public PrintJob(int numberOfPages)
	{
		this.numberOfPages = numberOfPages ;
		this.printStartTime = -1 ;
	}
	
	/**
	 * Copy Constructor copies the fields of a specified print job.
	 * 
	 * @param otherPrintJob represents the print job to be copied ;
	 */
	public PrintJob(PrintJob otherPrintJob)
	{
		this.numberOfPages = otherPrintJob.numberOfPages ;
		this.printStartTime = otherPrintJob.printStartTime ;
	}	
	
	/**
	 * Accessor for static variable time.
	 * 	
	 * @return the current value of the static variable time.
	 */
	public static int getTime()
	{
		return time ;
	}
	
	/**
	 * Increments the value of the static variable, time, by one.
	 * 
	 * @return the value of the static variable time after being incremented.
	 */
	public static int incrementTime()
	{
		return time++ ;
	}
	
	/**
	 * Mutator of numberOfPages.
	 * 
	 * @param numberOfPages represents the number of pages to be printed.
	 */
	public void setNumberOfPages(int numberOfPages)
	{
		this.numberOfPages = numberOfPages ;
	}
	
	/**
	 * Accessor of numberOfPages.
	 * 
	 * @return the number of pages to be printed.
	 */
	public int getNumberOfPages()
	{
		return numberOfPages ;
	}

	/**
	 * Mutator for printStartTime.
	 * 
	 * @param startTime represents the time in which the print job starts printing.
	 */
	public void setPrintStartTime(int startTime)
	{
		this.printStartTime = startTime ;
	}
	
	/**
	 * Accessor for printStartTime.
	 * 
	 * @return the time in which the print job started printing.
	 */
	public int getPrintStartTime()
	{
		return printStartTime ;
	}
	
	/**
	 * Calculates the ending time of the print job.
	 * 
	 * @return the ending time of the print job;
	 * 		-1 if the print job has not been done yet
	 */
	public int getPrintEndTime()
	{			
		if (!isJobDone())
		{
			return -1 ;
		}
		return this.printStartTime + getPrintingTime() ;
	}
	
	/**
	 * Calculate the time that the print job takes to print all of the pages.
	 * 
	 * @return the time that the print job takes to print all of the pages.
	 */
	public int getPrintingTime()
	{
		return (int) Math.ceil((double) numberOfPages / PAGES_PER_TIME) ;
	}
	
	/**
	 * Tests if the print job has been started; but has not finished yet.
	 * 
	 * @return true if the print job has been started and has not finished yet
	 */
	public boolean isPrinting()
	{
		return this.printStartTime != -1 
			&& this.printStartTime + getPrintingTime() > PrintJob.time ;
	}
	
	/**
	 * Tests if the print job has stared and has done printing. 
	 * @return
	 */
	public boolean isJobDone()
	{
		return !isPrinting() && this.printStartTime != -1 ;
	}
	
	/**
	 * Builds a string that contains the number of pages; the print start time;
	 * and if the job has or has not been printed..
	 * 
	 * @return a string that contains the values of all of the instance variable.
	 */
	@Override
	public String toString()
	{
		String start = printStartTime == -1 ? "---" : printStartTime + "" ;
		String end = getPrintEndTime() == -1 ? "---" : getPrintEndTime() + "" ;
		
		return String.format("Pages: %-3s   Start: %-3s   End: %-3s   Done: %s", 
				numberOfPages, start , end, isJobDone()) ;
	}
	
	/**
	 * Compares the instance variable of this and another print job.
	 * 
	 * @return true if all of the compared instance variables are equivalent.
	 */
	@Override
	public boolean equals(Object obj)
	{
		if (obj == null || getClass() != obj.getClass())
		{
			return false ;
		}
		
		PrintJob other = (PrintJob) obj ;
		return numberOfPages == other.numberOfPages
				&& printStartTime == other.printStartTime ;
	}	
}
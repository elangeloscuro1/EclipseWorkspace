package printerQueue ;

/**
 * PrinterQueue represents the queue of a printer and can
 * be set up to store print job with in a specified range.
 */
/**
 * @author Angel Tapia
 */
public class PrinterQueue
{
	/** printerID represents the printer ID  */
	private String printerID ;
	/** minNumberOfPages represents the minimum number of pages the printer can take.  */
	private int minNumberOfPages ;
	/** maxNumberOfPages represents the maximum number of pages the printer can take.  */
	private int maxNumberOfPages ;
	/** printingTime represents the time that takes a set of print job set to be printed. */
	private int printingTime ;
	
	/** printJobs stores the list of print jobs to be printed. */
	private ArrayQueue<PrintJob> printJobs ;
	
	/**
	 * Default constructor initializes all of the variables to their default values.
	 */
	public PrinterQueue()
	{
		this.printerID = "UNKNOWN" ;
		this.minNumberOfPages = 0 ;
		this.maxNumberOfPages = 0 ;
		this.printingTime = 0 ;
		this.printJobs = new ArrayQueue<PrintJob>() ;
	}
	
	/**
	 * Full constructor.
	 * 
	 * @param printerID represents the ID of the printer.
	 * @param minNumberOfPages represents the minimum of pages the printer can take in.
	 * @param maxNumberOfPages represents the maximum number of pages the printer can take in.
	 * @throws IllegalArgumentException if minNumberOfPages > maxNumberOfPages.
	 */
	public PrinterQueue(String printerID, int minNumberOfPages, int maxNumberOfPages )
	{
		if (minNumberOfPages > maxNumberOfPages)
		{
			throw new IllegalArgumentException("MIN > MAX") ;
		}
		this.printerID = printerID ;
		this.minNumberOfPages = minNumberOfPages ;
		this.maxNumberOfPages = maxNumberOfPages ;
		this.printingTime = 0 ;
		this.printJobs = new ArrayQueue<PrintJob>() ;
	}
	
	/**
	 * Mutator for printerID.
	 * 
	 * @param printerID represents the ID for the printer.
	 */
	public void setPrinterID(String printerID)
	{
		this.printerID = printerID ;
	}	
	
	/**
	 * Accessor for printerID.
	 * 
	 * @return the printer ID
	 */
	public String getPrinterID()
	{
		return this.printerID ;
	}

	/**
	 * Mutator for minNumberOfPages.
	 * 
	 * @param minNumberOfPages the minimum number of pages the printer can take.
	 */
	public void setMinNumberOfPages(int minNumberOfPages)
	{
		this.minNumberOfPages = minNumberOfPages ;
	}
	
	/**
	 * Accessor for minNumberOfPages.
	 * 
	 * @return the maximum number of pages the printer can take in.
	 */
	public int getMinNumberOfPages()
	{
		return minNumberOfPages ;
	}

	/**
	 * Mutator for maxNumberOfPages.
	 * 
	 * @param maxNumberOfPages represents the maximum number of pages the printer can take.
	 */
	public void setMaxNumberOfPages(int maxNumberOfPages)
	{
		this.maxNumberOfPages = maxNumberOfPages ;
	}
	
	/**
	 * Accessor for maxNumberOfPages.
	 * 
	 * @return the maximum number of pages the printer can take.
	 */
	public int getMaxNumberOfPages()
	{
		return maxNumberOfPages ;
	}
	
	/**
	 * Mutator for printingTime.
	 * 
	 * @param printingTime represents the time that takes a set of print job set to be printed.
	 */
	public void setPrintingTime(int printingTime)
	{
		this.printingTime = printingTime ;
	}

	/**
	 * Accessor for printingTime.
	 * 
	 * @return the time that takes a set of print job set to be printed.
	 */
	public int getPrintingTime()
	{
		return printingTime ;
	}
	
	/**
	 * Tests if printJobs is empty.
	 * 
	 * @return true if this printer queue is empty.
	 */
	public boolean isEmpty()
	{
		return this.printJobs.isEmpty() ;
	}
	
	/**
	 * Add a print job to this printer queue.
	 * 
	 * @param aPrintJob represents the print job to be added.
	 * @return true if the print job is successfully added; false otherwise.
	 */
	public boolean addPrintJob(PrintJob aPrintJob)
	{
		if (aPrintJob.getNumberOfPages() >= this.minNumberOfPages
				&& aPrintJob.getNumberOfPages() <= this.maxNumberOfPages )
		{
			return this.printJobs.offer(aPrintJob) ;
		}
		return false ;
	}
	
	/**
	 * Prints next print job or removes if print job is done printing.
	 * 
	 * @return the current print job or null if queue is empty.
	 */
	public PrintJob printNext()
	{
		PrintJob curent = this.printJobs.peek() ;		
		
		if (curent != null 
			&& !curent.isPrinting() && !curent.isJobDone())
		{
			curent.setPrintStartTime(PrintJob.getTime()) ;
		}
		if (curent != null && curent.isJobDone())
		{
			this.printingTime += curent.getPrintingTime() ;
			curent = this.printJobs.poll() ;
			
			if (this.printJobs.peek() != null)
			{
				this.printJobs.peek().setPrintStartTime(PrintJob.getTime()) ;
			}			
			return curent ;
		}
		return curent ;
	}
	
	/**
	 * Builds a string that contains the information about this printer queue.
	 * 
	 * @return the information about this printer queue.
	 */
	@Override
	public String toString()
	{	
		return String.format("Name: %-12s Min: %-4s Max: %s", 
				printerID, minNumberOfPages, maxNumberOfPages) ;
	}
	
	/**
	 * Compares the list of print jobs from this and another print job list.
	 * 
	 * @return true if all of the print jobs are equivalent; false otherwise.
	 */
	@Override
	public boolean equals(Object obj)
	{
		if (obj == null || getClass() != obj.getClass())
		{
			return false ;
		}		
		PrinterQueue other = (PrinterQueue) obj ;
		return printJobs.containsAll(other.printJobs) ;		
	}
}
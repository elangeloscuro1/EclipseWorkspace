package printerQueue2;

/**
 * PrintingTime represents the printing time in minutes that
 * takes a specific number of pages to be printed.
 */
/**
 * @author Angel Tapia
 */
public class PrintingTime
{
	/** PAGES_PER_MINUTE represents the number of pages per minutes */
	public static final int PAGES_PER_MINUTE = 10 ;
	
	/** */
	public static int timer ; 
	
	/** numberOfPages represents the total of pages to print */
	private int numberOfPages ;
	//private int printStartTime;
	/** timeInMinutes represent the total of minutes to print the total of pages */
	private int timeInMinutes ;
	/** remainderTime represents the decimal remainder to to complete another minute */
	private int remainderTime ;
	
	/**
	 * Default constructor initializes all instance variable to default values.
	 */
	public PrintingTime()
	{
		this.numberOfPages = 0 ;
		this.timeInMinutes = 0 ;
		this.remainderTime = 0 ;
	}
	
	/**
	 * Receives a number of pages and calculate 
	 * timeMinutes and remaindeTime according to the number of pages.
	 * 
	 * @param numberOfPages represents the number of pages to be printed.
	 */
	public PrintingTime(int numberOfPages)
	{
		this.numberOfPages = numberOfPages ;
		this.timeInMinutes = numberOfPages / PAGES_PER_MINUTE ;
		this.remainderTime = numberOfPages % PAGES_PER_MINUTE ;
	}
	
	/**
	 * Copy constructor: copy all of the field values of other PrintingTime object.
	 * 
	 * @param other represents the other object of the type PrintingTime.
	 */
	public PrintingTime(PrintingTime other)
	{
		this.numberOfPages = other.numberOfPages ;	
		this.timeInMinutes = other.timeInMinutes ;	
		this.remainderTime = other.remainderTime ;
		
	}
	
	/**
	 * Mutator for numberOfPages
	 * 
	 * @param numberOfPages represents the number of pages to be printed.
	 */
	public void setNumberOfPages(int numberOfPages)
	{
		this.numberOfPages = numberOfPages ;
	}
	
	/**
	 * Accessor for numberOfPages.
	 * 
	 * @return the number of pages to be printed.
	 */
	public int getNumberOfPages()
	{
		return this.numberOfPages ;
	}
	
	/**
	 * Mutator for timeInMinutes.
	 * 
	 * @param minutes represents the total of minutes 
	 * 		that takes to print the total number of pages.
	 */
	public void setTimeInMinutes(int minutes)
	{
		this.timeInMinutes = minutes ;
	}
	
	/**
	 * Accessor fot timeInMinutes.
	 * 
	 * @return the total of minutes 
	 * 		that takes to print the total number of pages.
	 */
	public int getTimeInMinutes()
	{
		return this.timeInMinutes ;
	}
	
	/**
	 * Mutator for remainderTime.
	 * 
	 * @param remainderTime represents the time remaining to complete a minute.
	 */
	public void setRemainderTime(int remainderTime)
	{
		this.remainderTime = remainderTime ;
	}
	
	/**
	 * Accessor for remainderTime.
	 * 
	 * @return time remaining to complete a minute.
	 */
	public int getRemainderTime()
	{
		return this.remainderTime ;
	}
	
	/**
	 * Builds a string that contains the information of all the instance variable.
	 * 
	 * @return a string that contains the information of all the instance variable.
	 */
	@Override
	public String toString()
	{
		return String.format("Total Pages: %-5s Total Minutes: %-5s",
				numberOfPages , timeInMinutes + "." + remainderTime) ;
		
	}
	
	/**
	 * Compares all the instance variable of this and other PrintingTime object.
	 * 
	 * @return true if all the instance variable are equivalent.
	 */
	@Override
	public boolean equals(Object obj)
	{
		if (obj == null || getClass() != obj.getClass())
		{
			return false ;
		}
		
		PrintingTime other = (PrintingTime) obj ;
		return this.numberOfPages == other.numberOfPages 
			&& this.timeInMinutes == other.timeInMinutes
			&& this.remainderTime == other.remainderTime ;
	}
}
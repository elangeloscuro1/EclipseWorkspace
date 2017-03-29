package printerQueue2;



public class PrintJob
{
	
	public static void main(String[] args)
	{
		System.out.println(PrintingTime.timer) ;
		PrintingTime.timer++ ;
		System.out.println(PrintingTime.timer) ;
	}
	
	private int numberOfPages ;
	private int currentPage ;
	
	
	
	
	
	
	
	
	
	public PrintJob()
	{
		this.numberOfPages = 0 ;
		this.currentPage = 0 ;
	}
	public PrintJob(int numberOfPages)
	{
		this.numberOfPages = numberOfPages ;
		this.currentPage = 0 ;
	}
	
	public PrintJob(int numberOfPages, int currentPage)
	{
		if (currentPage > numberOfPages || currentPage < 0)
		{
			throw new IllegalArgumentException("Current Page: " + currentPage) ;
		}
		this.numberOfPages = numberOfPages ;
		this.currentPage = currentPage ;
	}
	
	public void setNumberOfPages(int numberOfPages)
	{
		this.numberOfPages = numberOfPages ;
	}
	public int getNumberOfPages()
	{
		return this.numberOfPages ;
	}	
	public void setCurrentPage(int currentPage)
	{
		this.currentPage = currentPage ;
	}	
	public int getCurrentPage()
	{
		return this.currentPage ;
	}
	public boolean isPrinting()
	{
		return this.currentPage != this.numberOfPages ;
	}	
	public boolean printNextPage()
	{
		if (!isPrinting())
		{
			return false ;			
		}
		this.currentPage++ ;
		return true ;
	}

}

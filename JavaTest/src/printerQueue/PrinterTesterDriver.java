package printerQueue;

/**
 * PrinterTesterDriver runs a program that tests the printing time
 * to print from 1  to 1000 print jobs, with 1 to 10 printers.
 * 
 * Algorithm:
 * 	A) Implement an generic ArrayQueue class based on a queue structure.
 * 		-Create instance variable: 
 * 		 generic type dataArray and capacity, front, rear, and size of the type int.
 * 		-Create an inner class iterator that implements the hasNext and next method
 * 		 from the iterator interface.
 * 		-Create a default ArrayQueue constructor that initializes instance variable
 * 		 to default values.
 * 		-Create a constructor that initializes capacity to a specific value.
 * 		-Create a method reallocate that doubles the dataArray when size reaches capacity.
 * 		-Implement the method offer to add data to the dataArray.
 * 		-Implement the method peek to access the first data added to dataArray.
 * 		-Implement the method pool that access and remove the first data added to dataArray.
 * 		-Implement the method iterator that return an iterator for dadaArray.
 * 		-Implement the method size that return the size of data in dataArray.
 * 		-Override the toString method by building and returning a string
 * 		 that contains all of the data in dataArray.
 * 		-Override the equals method by comparing every data in dataArray.
 * 	B) Create a class PrintJobs that represents a print job.
 * 		-Create a constant that represent the pages per minutes for each print job can process.
 * 		-Create a static variable time that represent the time in which every print job do their work.
 * 		-Create instance variable numberOfPages and printStartTime to track the number of pages and the
 * 		 printing start time of each print job.
 * 		-Create a default constructor that initializes number of pages to 0 and printStartTime to -1
 * 		 to indicate that the current print job has no data yet.
 * 		-Create a constructor that takes in the number of pages for a print job.
 * 		-Create a copy constructor that copy the fields of another print job.
 * 		-Create a static method to access time.
 * 		-Create a static method that increments time by 1.
 * 		-Create Getters and Setter for numberOfPages and printStartTime.
 * 		-Create a method getPrintingTime that calculate the time a print job takes.
 * 		-Create a method getPrintEndTime that calculate the time a print job should end.
 * 		-Create a method isPrinting that checks if a print job has started.
 * 		-Create a method isJobDone that checks if a print job has been printed.
 * 		-Override the toString method by building and returning the current status of the print job.
 * 		-Override the equals method by comparing the value of two print jobs.
 * 	C) Create a class PrinterQueue that represents the queue of a printer.
 * 		-Create instance variable for printerID, minNumberOfPages, maxNumberOfPages and printingTime.
 * 		-Create a ArrayQueue of printJobs to store data of print jobs.
 * 		-Create a default constructor that initializes all of the instance variable to their default values.
 * 		-Create an full constructor that takes in the value for printerID, minNumberOfPages, and maxNumberOfPage.
 * 		-Create Setters and Getters for printerID, minNumberOfPages, maxNumberOfPages and printingTime.
 * 		-Create a method isEmpty that checks id a printer queue is empty.
 * 		-Create a method addPrintJob that add a print job to the queue.
 * 		-Create a method printNext that start a print job or removes a print job
 * 		 (if print job has done printing). This method also return the current print job.
 * 		-Override the to string method by building and returning a string that contains 
 * 		 the values of the instance variable.
 * 		-Override the equals method by checking if two printers queue contains the same values.
 * 	D) Create a class PrinterTestSetup that can set up one or more printer to test.
 * 		-Create a constant for a maximum possible of pages for print jobs.
 * 		-Create a Scanner final object to get input from the user.
 * 		-Create an array of PrinterQueue to store printers print jobs.
 * 		-Create an instance variable totalPrintJobs that stores the number of print jobs to test.
 * 		-Create an instance variable show that allows the user to see details of the test.
 * 		-Create an instance variable delayTime that allows the user to delay the test time in milliseconds.
 * 		-Create a default constructor that initializes all of the instance variable to their default values.
 * 		-Create a full constructor that takes in values for instance variable.
 * 		-Create a setPrinters method that initializes a specified number of printers to test.
 * 		-Create s method setTotalPrintJobs that initializes the total of print jobs to test.
 * 		-Create a method setShow that set the show variable to display or not the details of the test.
 * 		-Create a setDelayTime method that a delay time in the test in milliseconds.
 * 		-Create a menu method that display option for the test and return the number chose.
 * 		-Create a method getValidInt that receive a minimum and a maximum value to validate
 * 		 (returns a valid integer).
 * 		-Create a method delay that create a delay of a specified time.
 * 		-Create a method setDefaultTest that creates and runs a specific number of computers
 * 		 with a specific set of print job.
 * 		-Create a addPrinJobs method that adds print jobs to a printer queue.
 * 		-Create a method printAll that makes all the printers print all their print jobs.
 * 		-Create a method displayResult that show the final result of all of the printer.
 * 		-Override the toString method by displaying the information of all of the printers.
 * 		-Override the equals method by comparing the values in each printer.
 * 	E) Create a PrinterTesterDriver class that is the driver of the program.
 * 		-Create constant for maximum printer and maximum print jobs to test.
 * 		-Implement the main method:
 * 			-Create an object of PrinterTestSetup.
 * 			-Create a while loop that allows the user to do more that one test.
 * 			 If specified, run the default test by calling the method setDefaultTest.
 * 			 Else Make the user to set a number of printer to use and a number 
 * 			 of print jobs to test.
 * 			 Print result for each test.
 */
/**
 * @author Angel Tapia
 */
public class PrinterTesterDriver
{
	/** Maximum printers to test. */
	public static final int MAXIMUM_PRINTERS = 10 ;
	/** Maximum of print jobs to test  */
	public static final int MAXIMUM_PRINT_JOBS = 1000 ;

	public static void main(String[] args)
	{		
		PrinterTestSetup test = new PrinterTestSetup() ;
		
		int option = 0 ;
		while ((option =  test.menu()) !=  0)
		{
			if (option == 1)
			{
				test.setDefaultTest() ;
			}
			else
			{
				System.out.print("Number of Printers (No more than 10): ") ;
				test.setPrinters(test.getValidInt(1, MAXIMUM_PRINTERS)) ;

				System.out.print("Number of Print Jobs: ") ;
				test.setTotalPrintJobs(test.getValidInt(1, MAXIMUM_PRINT_JOBS)) ;

				System.out.print("Display Details(y/n)? ") ;
				if (test.setShow(PrinterTestSetup.scan.next().toLowerCase().charAt(0) == 'y'))
				{
					System.out.print("Set Time in Milliseconds:") ;
					test.setDelayTime(test.getValidInt(10, 60000)) ;
				}

				System.out.println(test) ;
				test.addPrintJobs() ;
				test.printAll() ;
				test.displayResults() ;
			}
			System.out.println("\n==== End of Test =====\n") ;
		}
	}
}
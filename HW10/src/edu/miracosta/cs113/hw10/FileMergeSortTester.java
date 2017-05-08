package edu.miracosta.cs113.hw10;

import java.io.File ;
import java.io.FileNotFoundException ;
import java.io.FileOutputStream ;
import java.io.PrintWriter ;
import java.util.Scanner ;

/**
 * Algorithm:
 * 		-Initializes file to be used.
 * 		-Create a while loop that stops when the array is sorted.
 * 		-Delete any existing files(except the original).
 * 		-Create an object of the Type Scanner that read the original file.
 * 		-Create an object of the Type Scanner that read the leftFile.
 * 		-Create an object of the Type Scanner that read the right File.
 * 		-Create an object of type printWriter that writes to the leftFile.
 * 		-Create an object of type printWriter that writes to the rightFile.
 * 		-Create an object of type printWriter that writes to a tempFile.
 * 		-Scan the original file and separate each pair of value.
 * 		 (Small to the leftFile and big to the rightFile.)
 * 		-If There is no more swaps done, break the loop.
 * 		-Else, merge the leftFile and the rightFile into a tempFile.
 * 		-Update the original file to be the tempFile.
 * 
 */
/**
 * FileMergeSorttester tests FileMergeSort.
 * 
 * @author Angel Tapia <angelTapia07084759@gmail.com>
 * version 1.0
 */
public class FileMergeSortTester
{
	/**
	 * Sorts a sequential data file that contains integers.
	 * 
	 * @param args Command Lines.
	 */
	public static void main(String[] args)
	{
		String fileName = "OriginalFileForMergeSort.txt" ;
		new FileMergeSortTester(fileName).sort() ;
	}

	/** File to be used to separate smallest values. */
	private static final File LEFT_FILE  = new File("leftFile.txt")  ;
	/** File to be used to separate largest values. */
	private static final File RIGHT_FILE = new File("rightFile.txt") ;
	//
	private static final File TEMP_FILE  = new File("tempFile.txt")  ;

	private static File originalFile = null ;
	
	public FileMergeSortTester(String fileName)
	{
		originalFile = new File(fileName) ; 
	}
	
	
	public boolean sort()
	{		
		
	
		return true ;
	}
	
	public Scanner scanFile(File file) throws FileNotFoundException
	{
		return new Scanner(originalFile) ;
	}	
	
	public PrintWriter writeFile(File file, boolean append) throws FileNotFoundException
	{
		return new PrintWriter(new FileOutputStream(file, append)) ;
	}
	
	public boolean splitFile(Scanner original, PrintWriter left, PrintWriter right) throws FileNotFoundException
	{
		int countSwaps = 0 ;
		
		Integer prev = null ;
		while (original.hasNextInt())
		{
			int current = original.nextInt() ;

			if (original.hasNextInt())
			{
				int next = original.nextInt() ;

				left.println((next < current) ? next : current) ;
				right.println((next > current) ? next : current) ;						
				left.flush() ;
				right.flush() ;
				prev = next ;
				countSwaps = current > next ? (countSwaps + 1) : countSwaps ;						
			}
			else
			{
				countSwaps = prev != null && prev > current  ? (countSwaps + 1) : countSwaps ;	
				left.println(current) ;
				left.flush() ;
			}
		}		
		return countSwaps == 0 ;		
	}
	
	public void mergeFiles(Scanner left, Scanner right, PrintWriter original) throws FileNotFoundException
	{
		String OriginalFileName = originalFile.getName() ;
		originalFile.renameTo(TEMP_FILE) ;
		originalFile.delete() ;
	}
	
	public void mainTest(String[] args)
	{		
		boolean isSorted = false ;
		while (!isSorted)
		{
			try
			{
				LEFT_FILE.delete() ;
				RIGHT_FILE.delete() ;
				TEMP_FILE.delete() ;				
				
				Scanner scanOriginal = scanFile(originalFile) ;				
				PrintWriter leftWriter =  writeFile(LEFT_FILE, true)  ;
				PrintWriter rightWriter =  writeFile(RIGHT_FILE, true) ;
				
				isSorted = splitFile(scanOriginal, leftWriter, rightWriter) ;		
				boolean isMerging = !isSorted ;

				// Scanners and writer to update the original file.
				Scanner scanLeft = scanFile(LEFT_FILE) ;
				Scanner scanRight = scanFile(RIGHT_FILE) ;
				PrintWriter newWriter =  writeFile(TEMP_FILE, true) ;				
								
				

				Integer nextLeft = scanLeft.hasNextInt() ? scanLeft.nextInt() : null ;
				Integer nextRight = scanRight.hasNextInt() ? scanRight.nextInt() : null ;

				// Merging to a new file
				while (isMerging)
				{
					if (nextLeft != null && nextRight != null)
					{
						if (nextLeft < nextRight)
						{
							newWriter.println(nextLeft) ;
							newWriter.flush() ;
							nextLeft = scanLeft.hasNextInt() ? scanLeft.nextInt() : null ;
						}
						else
						{
							newWriter.println(nextRight) ;
							newWriter.flush() ;
							nextRight = scanRight.hasNextInt() ? scanRight.nextInt() : null ;
						}
					}
					else if (nextLeft != null)
					{
						newWriter.println(nextLeft) ;
						newWriter.flush() ;
						nextLeft = scanLeft.hasNextInt() ? scanLeft.nextInt() : null ;
					}
					else if (nextRight != null)
					{
						newWriter.println(nextRight) ;
						newWriter.flush() ;
						nextRight = scanRight.hasNextInt() ? scanRight.nextInt() : null ;
					}
					else
					{
						isMerging = scanLeft.hasNext() || scanRight.hasNext() ;
					}

				}
				
				// Closing resources
				leftWriter.close() ;
				rightWriter.close() ;
				newWriter.close() ;

				scanOriginal.close() ;
				scanLeft.close() ;
				scanRight.close() ;				
				// Updating original File
				if (!isSorted)
				{
					originalFile.delete() ;
					TEMP_FILE.renameTo(originalFile) ;
				}
			}
			catch (FileNotFoundException e)
			{
				System.out.println(e.getMessage()) ;
			}
		}
		System.out.println("DONE!") ;
	}// End of main
}

package edu.miracosta.cs113.hw10 ;
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
 * FileMergeSort is sorts file using sequential data files.
 * 
 * @author Angel Tapia <angelTapia07084759@gmail.com>
 * version 1.0
 */
public class FileMergeSort
{

	/**
	 * Sorts a sequential data file that contains integers.
	 * 
	 * @param args Command Lines.
	 */
	public static void main(String[] args)
	{		
		// Files to be used
		File originalFile = new File("OriginalFileForMergeSort.txt") ;	
		File leftFile = new File("leftFile.txt") ;
		File rightFile = new File("rightFile.txt") ;
		File tempFile = new File("tempFile.txt") ;
		
		// flag
		boolean isSorted = false ;
		while (!isSorted)
		{
			try
			{
				// Delete any existent file
				leftFile.delete() ;
				rightFile.delete() ;
				tempFile.delete() ;				
				
				// scanner an writer for the left and right files
				Scanner scanOriginal = new Scanner(originalFile) ;
				PrintWriter leftWriter = new PrintWriter(new FileOutputStream(leftFile, true)) ;
				PrintWriter rightWriter = new PrintWriter(new FileOutputStream(rightFile, true)) ;

				// Scanners and writer to update the original file.
				Scanner scanLeft = new Scanner(leftFile) ;
				Scanner scanRight = new Scanner(rightFile) ;
				PrintWriter newWriter = new PrintWriter(new FileOutputStream(tempFile, true)) ;

				int countSwaps = 0 ;// counts swaps if current > next
				
				// Separating values smaller to leftFile and larger rifgtFile
				Integer prev = null ;
				while (scanOriginal.hasNextInt())
				{
					int current = scanOriginal.nextInt() ;

					if (scanOriginal.hasNextInt())
					{
						int next = scanOriginal.nextInt() ;

						leftWriter.println((next < current) ? next : current) ;
						rightWriter.println((next > current) ? next : current) ;						
						leftWriter.flush() ;
						rightWriter.flush() ;
						prev = next ;
						countSwaps = current > next ? (countSwaps + 1) : countSwaps ;						
					}
					else
					{
						countSwaps = prev != null && prev > current  ? (countSwaps + 1) : countSwaps ;	
						leftWriter.println(current) ;
						leftWriter.flush() ;
					}
				}
				
								
				isSorted = countSwaps == 0 ;// 0 swaps means is sorted				
				boolean isMerging = !isSorted ;

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
					tempFile.renameTo(originalFile) ;
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
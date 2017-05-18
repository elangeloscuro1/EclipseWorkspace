import java.util.ArrayList ;
import java.util.NoSuchElementException ;

/**
 * Heap is an abstract class that provided the basic implementation
 * of a heap and uses an ArrayList to store its data.
 * 
 * @author Angel Tapia <angelTapia07084759@gmail.com>
 * @version 1.0
 * @param <E>  Generic type parameter
 */
public abstract class Heap<E>
{
	/** Stores the data of the heap */
	private ArrayList<E> theData ;
		
	/**
	 * Default constructor that constructs a heap with priority 
	 * using natural ordering.
	 */
	public Heap()
	{
		this.theData = new ArrayList<E>() ;
	}	
	
	/**
     * Abstract method compare must be defined in subclasses.
     * 
     * @param left element to the left to be compared.
     * @param right element to the right to be compared.
     * @return -1 if left has less priority than right; 0 if left has equals 
     * 			priority than right, and 1 if left has greater priority than right.
     */
	public abstract int compare(E left, E right) ;
	
	/**
	 * Exchanges the objects references in theData indexes i and j.
	 * 
	 * @param i: first index of the element to be swapped.
	 * @param j: second index of the element to be swapped.
	 */
	private void swap(int i, int j)
	{
		E tempData = this.theData.get(i) ;
		this.theData.set(i, this.theData.get(j)) ;
		this.theData.set(j, tempData) ;
	}

	/**
	 * Checks if theData is empty.
	 * 
	 * @return true if theData is empty.
	 */
	public boolean isEmpty()
	{
		return this.theData.size() == 0 ;
	}
	
	/**
	 * Access the size of theData.
	 * 
	 * @return the number of element in theData.
	 */
	public int size()
	{
		return this.theData.size() ;
	}
	
	/**
	 * Inserts an item into the heap.
	 * 
	 * @param item: the item to be inserted into the heap.
	 * @return if the item is successfully inserted.
	 */
	public boolean offer(E item)
	{
		this.theData.add(item) ;
		
		int child = this.theData.size() - 1 ;
		int parent = (child - 1) / 2 ;

		while (parent >= 0 && compare(this.theData.get(parent), this.theData.get(child)) > 0)
		{
			swap(parent, child) ;
			child = parent ;
			parent = (child - 1) / 2 ;
		}
		return true ;
	}
	
	/**
	 * Access the first element in theData without removing it.
	 * 
	 * @return the first element in theData without removing it or null if empty.
	 */
	public E peek()
	{
		if (isEmpty())
		{
			return null ;
		}
		return this.theData.get(0) ;
	}
	
	/**
	 * Access the first element in theData without removing it.
	 * 
	 * @return the first element in theData without removing it
	 *  @throws NoSuchElementException if theData is empty.
	 */
	public E element()
	{
		if (isEmpty())
		{
			throw new NoSuchElementException() ;
		}
		return peek() ;
	}
	
	/**
	 * Remove an item from the priority at level 1 of the heap.
	 * 
	 * @return The item at level 1 of the heap or null if empty.
	 */
	public E poll()
	{
		if (isEmpty())
		{
			return null ;
		}
		
		E result = this.theData.get(0) ;
		if (this.theData.size() == 1)
		{
			this.theData.remove(0) ;
			return result ;
		}
				
		this.theData.set(0, this.theData.remove(this.theData.size() - 1)) ;
		
		for (int p = 0 ; p <= (this.theData.size() - 2) / 2 ; p++)
		{
			int childL = (2 * p) + 1 ;
			int childR = (2 * p) + 2 ;			
			int parent = p ;

			while (parent >= 0 && childL < this.theData.size() 
					&& compare(this.theData.get(parent), this.theData.get(childL)) > 0)
			{
				swap(parent, childL) ;				
				childL = parent ;
				parent = (childL - 1) / 2 ;
			}
			
			parent = p ;
			
			while (parent >= 0 && childR < this.theData.size() 
					&& compare(this.theData.get(parent), this.theData.get(childR)) > 0)
			{
				swap(parent, childR) ;				
				childR = parent ;
				parent = (childR - 1) / 2 ;
			}
		}	
		return result ;
	}
	
	/**
	 * Remove an item from the priority at level 1 of the heap.
	 * 
	 *  @throws NoSuchElementException if theData is empty.
	 */
	public E remove()
	{
		if (isEmpty())
		{
			throw new NoSuchElementException() ;
		}
		return poll() ;
	}
	
	/**
	 * Builds a string that contains data of theData.
	 * 
	 * @return a string that contains data of theData.
	 */
	@Override
	public String toString()
	{
		StringBuilder sb = new StringBuilder() ;
		for (E e : theData)
		{
			sb.append(e + "\n") ;
		}
		return sb.toString() ;
	}
	
	/**
	 * Checks if this this and another's theData contains equivalent elements
	 * 
	 * @return true if both theData are equivalent.
	 */
	@SuppressWarnings("unchecked")
	@Override
	public boolean equals(Object obj)
	{
		if (obj == null || obj.getClass() != getClass())
		{
			return false ;
		}		
		Heap<E> other = (Heap<E>) obj ;
		return this.theData.containsAll(other.theData) &&  other.theData.containsAll(theData) ;		
	}
}
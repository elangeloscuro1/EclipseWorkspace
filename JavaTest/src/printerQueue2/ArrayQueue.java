package printerQueue2;

import java.util.AbstractQueue ;
import java.util.Iterator ;
import java.util.NoSuchElementException ;
import java.util.Queue ;

/**
 * ArrayQueue is an implementation of a Queue interface 
 * that uses a circular array to store its data.
 */

/**
 * @author Angel Tapia
 *
 * @param <T> represents the generic type.
 */
public class ArrayQueue<T> extends AbstractQueue<T> implements Queue<T>
{
	/** INITIAL_CAPACITY stores the default initial capacity of the arrayQueue */
	public static final int INITIAL_CAPACITY = 10 ;
	
	/** dataArray stores the data in this queue */
	private T[] dataArray ;
	/** capacity represents the current capacity of this queue */
	private int capacity ;
	/** front represents the index of the first item added to this queue */
	private int front ;
	/** rear represents the last item added to this queue */
	private int rear ;
	/** size counts the total of items in this queue */
	private int size ;
	
	
	/*******************************************************
	 * Inner Generic Class ArrayQueueIterator
	 * (extends Iterator to make ArrayQueue iterable)
	 *******************************************************/
	private class ArrayQueueIterator implements Iterator<T>
	{
		/** index represent the current index of the iterator*/
		private int index ;
		/** count counts the iterated indices */
		private int count ;
		
		/**
		 * Default constructor sets the index at the front of the queue.
		 */
		public ArrayQueueIterator()
		{
			this.index = front ;
			this.count = 0 ;
		}
		
		/**
		 * Test if number of iterated indices is equal 
		 * to the total of elements in the queue.
		 * 
		 * @return true if count < size; false otherwise.
		 */
		@Override
		public boolean hasNext()
		{
			return this.count < size ;  
		}
		
		/**
		 * Access the next available element in the queue.
		 * 
		 * @return the next available element in the queue.
		 * @throws NoSuchElementException if no more elements is found.
		 */
		@Override
		public T next()
		{
			if (!hasNext())
			{
				throw new NoSuchElementException() ;
			}
			T temp = (T) dataArray[this.index] ;
			this.index = (this.index + 1) % capacity ;
			this.count++ ;
			return temp ;
		}		
	}// End of inner class ArrayQueueIterator
	
	
	/**
	 * Default constructor that initializes instance variable to default values ;
	 */
	@SuppressWarnings("unchecked")
	public ArrayQueue()
	{
		this.dataArray = (T[]) new Object[INITIAL_CAPACITY] ;
		this.capacity = INITIAL_CAPACITY ;
		this.front = 0 ;
		this.rear = this.capacity - 1 ;
		this.size = 0 ;		
	}
	
	/**
	 * Initializes dataArray with a specific initial capacity.
	 * 
	 * @param initialCapacity represents the specified capacity.
	 */
	@SuppressWarnings("unchecked")
	public ArrayQueue(int initialCapacity)
	{
		this.dataArray = (T[]) new Object[initialCapacity] ;
		this.capacity = initialCapacity ;
		this.front = 0 ;
		this.rear = this.capacity - 1 ;
		this.size = 0 ;
	}
	
	/**
	 * Reallocates the current full array to a new double-sized array.
	 */
	@SuppressWarnings("unchecked")
	private void reallocate()
	{		
		int newCapacity = this.capacity * 2 ;
		T[] newArray = (T[]) new Object[newCapacity] ;
		int oldIndex = this.front ;
		
		for (int newIndex = 0; newIndex < this.size ; newIndex++)
		{
			newArray[newIndex] = this.dataArray[oldIndex] ;
			oldIndex = (oldIndex + 1) % this.capacity ;
		}
		
		this.front = 0 ;
		this.rear = this.size - 1 ;
		this.capacity = newCapacity ;
		this.dataArray = newArray ;		
	}
	
	/**
	 * Adds an item at the rear of the queue.
	 * 
	 * @return true if the item is successfully added.
	 */
	@Override
	public boolean offer(T item)
	{
		if (this.size == this.capacity)
		{
			reallocate() ;
		}
		
		this.size++ ;
		this.rear = (this.rear + 1) % this.capacity ;
		this.dataArray[this.rear] = item ;
		return true ;
	}
	
	/**
	 * Access the item at the front of the queue.
	 * 
	 * @return the item at the front of the queue if exist; null otherwise.
	 */
	@Override
	public T peek()
	{
		if (this.size == 0)
		{
			return null ;
		}
		return this.dataArray[this.front] ;
	}
	
	/**
	 * Access and removes the item at the front of the queue.
	 * 
	 * @return the element that is removed ;
	 */
	@Override
	public T poll()
	{
		if (size == 0)
		{
			return null ;
		}
		T result = dataArray[front] ;
		front = (front + 1) % capacity ;
		size-- ;
		return result ;
	}
	
	/**
	 * Creates an Iterator positioned at front of the queue.
	 * 
	 * @return an Iterator positioned at front of the queue.
	 */
	@Override
	public Iterator<T> iterator()
	{
		return new ArrayQueueIterator() ;
	}
	
	/**
	 * Access the size of the queue.
	 * 
	 * @return the number of element in the queue.
	 */
	@Override
	public int size()
	{
		return this.size ;
	}
	
	/**
	 * Builds a string containing the values of the elements in the queue.
	 * 
	 * @return a string containing the values of the elements in the queue.
	 */
	@Override
	public String toString()
	{
		String temp = "" ;
		ArrayQueueIterator  iter = new ArrayQueueIterator() ;
		while (iter.hasNext())
		{
			temp += iter.next().toString() + "\n" ;
		}		
		return temp.trim() ;		
	}
	
	/**
	 * Compares the toString of this queue and another queue.
	 * 
	 * @return true if both toStrings are equivalent.
	 */
	@Override
	public boolean equals(Object obj)
	{
		if (obj == null || getClass() != obj.getClass())
		{
			return false ;
		}
		@SuppressWarnings("unchecked")
		ArrayQueue<T> other = (ArrayQueue<T>) obj ;
		return toString().equals(other.toString()) ;
	}
}
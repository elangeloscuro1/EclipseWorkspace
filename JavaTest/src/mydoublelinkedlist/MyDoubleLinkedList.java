/**
 * MyDoubleLinkedList in an implementation of the generic interface List.
 * This required an inner class Node 
 * and a inner class DoubleLinkedlist that implements ListIterator.
 */
package mydoublelinkedlist;

import java.util.Collection ;
import java.util.Iterator ;
import java.util.List ;
import java.util.ListIterator ;
import java.util.NoSuchElementException ;

/**
 * @author Angel Tapia
 *
 * @param <T> Generic type MyDoubleLinkedList
 */
public class MyDoubleLinkedList<T> implements List<T>
{

    /** A reference to the head of the list. */
    private Node<T> head = null ;
    /** A reference to the end of the list. */
    private Node<T> tail = null ;
    /** The size of the list. */
    private int size = 0 ;  
    
	/************************************************
	 * Inner Static Class Node
	 * 
	 * @param <T> Generic Type 
	 ************************************************/
	private static class Node<T>
	{
		/**Links to the next Node */
		private Node<T> next = null ;
		/**Links to the previews Node */
		private Node<T> prev = null ;
		/** Stores a data value */
		private T data ;
		
		/**
		 * Constructor receives the data to be stored.
		 * 
		 * @param data represents the data to be stored
		 */
		private Node(T data)
		{
			this.data = data ;			
		}
	}// End of Inner Static Class Node
	
		
	/************************************************
	 * Inner Class DoubleListIterator
	 * 
	 * @param <T> Generic Type 
	 ************************************************/
	private class DoubleListIterator implements ListIterator<T>
	{
		/** A reference to the next item */
		private Node<T> nextItem ;
		/** A reference to the last item returned */
		private Node<T> lastItemReturned ;
		/** The index of the current item */
		private int index ;
		
		/**
		 * Default constructor starts iterator at index 0.
		 */
		public DoubleListIterator()
		{
			this.nextItem = (Node<T>) head ;
			this.lastItemReturned = null ;
			this.index = 0 ;
		}
		
		/**
		 * Constructor takes in the index for the iterator.
		 * 
		 * @param index represents the index to place the Iterator.
		 */
		public DoubleListIterator(int index)
		{
			if (index < 0 || index > size)
			{
				throw new IndexOutOfBoundsException("Invalid index " + index) ;
			}
			
			lastItemReturned = null ;
			if (index == size)
			{
				this.index = size ;
				this.nextItem = null ;
			}
			else
			{
				this.nextItem = (Node<T>) head ;
				for (this.index = 0; this.index < index; this.index++)
				{
					this.nextItem = this.nextItem.next ;
				}
			}
		}
		
		/**
		 * Adds a new element between the next and previews elements.
		 * 
		 * @param e represents the element to be added.
		 */
		@Override
		public void add(T e)
		{
			Node<T> newNode = new Node<T>(e) ;
			if (head == null)
			{
				head = newNode ;
				tail = head ;
			}
			else if (this.nextItem == head)
			{
				newNode.next = this.nextItem ;
				this.nextItem.prev = newNode ;
				head = newNode ;
			}
			else if (this.nextItem == null)
			{
				tail.next = newNode ;
				newNode.prev = tail ;
				tail = newNode ;
			}
			else
			{
				newNode.prev = this.nextItem.prev ;
				this.nextItem.prev.next = newNode ;
				newNode.next = this.nextItem ;
				this.nextItem.prev = newNode ;
			}
			size++ ;
			this.index++ ;
			this.lastItemReturned = null ;			
		}
		
		/**
		 * Checks if there exist a next element in the list.
		 * 
		 * @return true if there exist an element next to the current element. 
		 */
		@Override
		public boolean hasNext()
		{	
			return this.nextItem != null ;
		}
		
		/**
		 * Accesses the next element in the list.
		 * 
		 * @return the next element in the list.
		 */
		@Override
		public T next()
		{
			if (!hasNext())
			{
				throw new NoSuchElementException() ;
			}
			this.lastItemReturned = this.nextItem ;
			this.nextItem = this.nextItem.next ;
			this.index++ ;
			return this.lastItemReturned.data ;
		}
		
		/**
		 * Checks if there exist a previous element in the list.
		 * 
		 * @return true if there exist an element previous to the current element. 
		 */
		@Override
		public boolean hasPrevious()
		{
			return (this.nextItem == null && size != 0) || this.nextItem.prev != null;
		}
		
		/**
		 * Accesses the previous element in the list.
		 * 
		 * @return the previous element in the list.
		 */		
		@Override
		public T previous()
		{
			if (!hasPrevious())
			{
				throw new NoSuchElementException() ;
			}
			if (this.nextItem == null)
			{
				this.nextItem = tail ;
			}
			else
			{
				this.nextItem = this.nextItem.prev ;
			}
			this.lastItemReturned = this.nextItem ;
			this.index-- ;
			return this.lastItemReturned.data ;
		}
		
		/**
		 * Accesses the next index in the list.
		 * 
		 * @return the next index in the list.
		 */	
		@Override
		public int nextIndex()
		{
			return this.index ;
		}
		
		/**
		 * Accesses the previous index in the list.
		 * 
		 * @return the previous index in the list.
		 */	
		@Override
		public int previousIndex()
		{
			return this.index - 1;
		}
		
		/**
		 * Removes the last element returned by next() or previous()
		 * @throws IllegalStateException if neither next() nor previous() have been called
		 */
		@Override
		public void remove()
		{
			if (this.lastItemReturned == null)
			{
				throw new IllegalStateException() ;
			}			
			if (size == 1)
			{
				head = null ;
				tail = null ;
				this.nextItem = null ;
			}
			else if (this.lastItemReturned == head)
			{
				head = head.next ;
				head.prev = null ;
			}
			else if (this.lastItemReturned == tail)
			{
				tail = tail.prev ;
				tail.next = null ;
			}
			else
			{
				this.lastItemReturned.next.prev = this.lastItemReturned.prev ;
				this.lastItemReturned.prev.next = this.lastItemReturned.next ;
			}
			this.lastItemReturned = null ;			
			size-- ;
			this.index-- ;
		}
		
		/**
		 * Replace the last element return by next() or previous() with the specified element.
		 * 
		 * @param e represents the element to replace the the last element returned.
		 * @throws IllegalStateException if neither next() nor previous() have been called.
		 */
		@Override
		public void set(T e)
		{
			if (this.lastItemReturned == null)
			{
				throw new IllegalStateException() ;
			}
			this.lastItemReturned.data = e ;
			this.lastItemReturned = null ;
		}
	}// End of Inner Class DoubleListIterator
	
	
	//************************************************
	// Implementing List interface.
	//************************************************
	    
    /**
     * Adds a specified element at the end of this list.
     * 
     * @param e represents the element to be added.
     * @return true if the element is successfully added.
     */
	@Override
	public boolean add(T e)
	{
		listIterator(this.size).add(e);
		return true ;
	}	
	
	/**
	 * Adds a specified element at a specified index to this list.
	 * 
	 * @param index represents the index to add the specified element.
	 * @param element represents the element to be added.
	 */
	@Override
	public void add(int index, T element)
	{
		listIterator(index).add(element) ;		
	}

	/**
	 * Adds a list of a specified element at the end of this list.
	 * 
	 * @param c represents the list of element to be added.
	 * @return true if at least one element is added to this list; false otherwise.
	 */
	@Override
	public boolean addAll(Collection<? extends T> c)
	{
		ListIterator<T> tempIterator = (ListIterator<T>) c.iterator() ;
		int currentSize = this.size ;
		
		while (tempIterator.hasNext())
		{
			add(tempIterator.next()) ;
		}		
		return this.size > currentSize ;
	}
	
	/**
	 * Adds a specified list of element at a specified index in this list.
	 * 
	 * @param index represents the index of this list to add the new elements.
	 * @param c represents the list of elements to be added.
	 * @return true if at least one element is added to this list; false otherwise.
	 */
	@Override
	public boolean addAll(int index, Collection<? extends T> c)
	{
		ListIterator<T> otherListIterator = (ListIterator<T>) c.iterator() ;
		ListIterator<T> thisListIterator = listIterator(index) ;		
		int currentSize = this.size ;
		
		while (otherListIterator.hasNext())
		{
			thisListIterator.add(otherListIterator.next()) ;
		}		
		return this.size > currentSize ;
	}
	
	/**
	 * Sets all instance variables of this list to default values (null and 0).
	 */
	@Override
	public void clear()
	{
		this.head = null ;
		this.tail = null ;
		this.size =  0 ;
	}
	
	/**
	 * Compares a specified element with each element in this list until a match is found.
	 * 
	 * @param object represent the target to be searched.
	 * @return true if the specified element is found; false otherwise.
	 * 
	 */
	@Override
	public boolean contains(Object object)
	{
		ListIterator<T> thisListIterator = listIterator() ;
		
		while(thisListIterator.hasNext())
		{
			if (thisListIterator.next().equals( (T) object ))
			{
				return true ;
			}		
		}
		return false ;
	}
	
	/**
	 * Compares each element in the specified list to each element in this list.
	 * 
	 * @param c represents the list of elements to be compared with this list.
	 * @return true if every single element in the specified list matches
	 * 		one element in this list; false otherwise.
	 */
	@Override
	public boolean containsAll(Collection<?> c)
	{		
		ListIterator<T> otherListIterator = (ListIterator<T>) c.iterator() ;
		
		while (otherListIterator.hasNext())
		{
			if (!contains(otherListIterator.next()))
			{
				return false ;
			}
		}				
		return true ;
	}
	
	/**
	 * Creates an iterator at a specified index to retrieve the element.
	 * 
	 * @param index represents the index of the element to return.
	 * @return the element at the specified index.
	 * @throws IndexOutOfBoundsException - if the index is out of range 
	 * 		   (index < 0 || index >= size())
	 */
	@Override
	public T get(int index)
	{		
		if (index < 0 || index >= this.size)
		{
			throw new IndexOutOfBoundsException("Invalid index " + index) ;
		}
		return listIterator(index).next() ;
	}
	
	/**
	 * Searches a specified element in the list.
	 * 
	 * @param object represent the target to be searched.
	 * @return the index of the first occurrence of the specified element; -1 if element is not found.
	 */
	@Override
	public int indexOf(Object object)
	{
		ListIterator<T> thisListIterator = listIterator() ;

		while (thisListIterator.hasNext())
		{
			if (thisListIterator.next().equals((T) object))
			{
				return thisListIterator.previousIndex() ;
			}
		}		
		return -1 ;
	}
	
	/**
	 * Checks if this list contains any element.
	 * 
	 * @return true if this list has 0 elements; false otherwise.
	 */
	@Override
	public boolean isEmpty()
	{
		return this.size == 0 ;
	}
	
	/**
	 * Creates a ListIterator at position 0 of this list.
	 * 
	 * @return a ListIterator at position 0 of this list.
	 */
	@Override
	public Iterator<T> iterator()
	{
		return new DoubleListIterator() ;
	}
		
	/**
	 * Searches a specified element in the list.
	 * 
	 * @param object represent the target to be searched.
	 * @return the index of the last occurrence of the specified element; -1 if element is not found.
	 */
	@Override
	public int lastIndexOf(Object object)
	{		
		ListIterator<T> thisListIterator = listIterator(this.size) ;

		while (thisListIterator.hasPrevious())
		{
			if (thisListIterator.previous().equals((T) object))
			{
				return thisListIterator.nextIndex() ;
			}
		}		
		return -1 ;
	}
	
	/**
	 * @return a ListIterator placed at the beginning of the list.
	 */
	@Override
	public ListIterator<T> listIterator()
	{		
		return new DoubleListIterator() ;
	}
	
	/**
	 * Creates an ListIterator at a specified index.
	 * 
	 * @param index represents the position to be used for the listIterator.
	 * @return a ListIterator placed at the specified index ;
	 */
	@Override
	public ListIterator<T> listIterator(int index)
	{
		return new DoubleListIterator(index) ;
	}

	/**
	 * Removes the first instance of the specified element.
	 * 
	 * @param object represents the element to be removed.
	 * @return true if the specified element is successfully removed.
	 */
	@Override
	public boolean remove(Object object)
	{
		ListIterator<T> thisListIterator = listIterator() ;
		
		while (thisListIterator.hasNext())
		{
			if (thisListIterator.next().equals((T) object))
			{
				thisListIterator.remove() ;
				return true ;
			}
		}		
		return false ;
	}
	
	/**
	 * Removes an element from a specified index of the list.
	 * 
	 * @param index represents the index of the element to be removed.
	 * @return the elements that is removed.
	 * @throws IndexOutOfBoundsException for an illegal  index value 
	 * 		   (index < 0 || index > size -1)
	 */
	@Override
	public T remove(int index)
	{
		if (index < 0 || index > size -1)
		{
			throw new IndexOutOfBoundsException("Invalid index " + index) ;
		}
		
		ListIterator<T> thisListIterator = listIterator(index) ;
		T temp = null ;
		
		if (thisListIterator.hasNext())
		{
			temp = thisListIterator.next() ;
			thisListIterator.remove() ;
		}		
		return temp ;
	}
	
	/** 
	 * Removes from this list all of its elements that are contained in the specified collection.
	 * 
	 * @param c represents the collection containing elements to be removed from this list.
	 * @return true if the list is changed after this method is called.
	 */
	@Override
	public boolean removeAll(Collection<?> c)
	{
		ListIterator<T> otherListIterator = (ListIterator<T>) c.iterator() ;
		int currentSize = this.size ;
		
		while (otherListIterator.hasNext())
		{
			remove(otherListIterator.next()) ;
		}		
		return this.size < currentSize ;
	}
	
	/**
	 * Retains only the elements in this list that are contained in the specified collection.
	 * 
	 * @param c represents the collection containing elements to be retained in this list.
	 * @return true if the list is changed after this method is called.
	 */
	@Override
	public boolean retainAll(Collection<?> c)
	{
		ListIterator<T> otherListIterator = (ListIterator<T>) c.iterator() ;
		MyDoubleLinkedList<T> tempList = new MyDoubleLinkedList<T>() ;		
		boolean hasItem = false ;
		
		while (otherListIterator.hasNext())
		{
			T temp = null ;
			if (contains(temp = otherListIterator.next()))
			{
				tempList.add(temp) ;
				hasItem = true ;
			}
		}
		
		if (hasItem)
		{
			this.head = tempList.head ;
			this.tail = tempList.tail ;
			this.size = tempList.size ;
		}		
		return hasItem ;
	}
	
	/**
	 * Swaps the element at a specific index of this list.
	 * 
	 * @param index represents the index in the list to be swapped.
	 * @param element represents the new element for the list.
	 * @return the element that is remove from the list.
	 */
	@Override
	public T set(int index, T element)
	{
		ListIterator<T> thisListIterator = listIterator(index) ;
		T temp = thisListIterator.next() ;
		thisListIterator.set(element) ;		
		return temp ;
	}
	
	/**
	 * Accessor for size.
	 * 
	 * @return the size of the list.
	 */
	@Override
	public int size()
	{
		return this.size ;
	}
	
	/**
	 * Creates a sublist with specific elements in this list.
	 * 
	 * @param fromIndex represents the low end-point of the subList (included).
	 * @param toIndex represent the high end-point of the subList (excluded).
	 * @return a subList that contains items of the specific indices.
	 * @throws IndexOutOfBoundsException for an illegal endpoint index value 
	 * 		   (fromIndex < 0 || toIndex > size || fromIndex > toIndex)
	 */
	@Override
	public List<T> subList(int fromIndex, int toIndex)
	{
		if (fromIndex < 0 || toIndex > this.size || fromIndex > toIndex)
		{
			throw new IndexOutOfBoundsException(
					"Invalid index " + fromIndex + " OR " + toIndex) ;
		}		
		ListIterator<T> thisListIterator = listIterator(fromIndex) ;
		MyDoubleLinkedList<T> tempList = new MyDoubleLinkedList<T>() ;
		
		for (int i = fromIndex; i < toIndex ; i++)
		{
			tempList.add(thisListIterator.next()) ;
		}	
		return tempList ;
	}
	
	/**
	 * Creates an array of objects containing all of the elements in this list.
	 * 
	 * @return an reallocated array of elements from this list.
	 */
	@Override
	public Object[] toArray()
	{
		ListIterator<T> thisListIterator = listIterator() ;
		Object[] tempList = new Object[this.size] ;
		
		for (int i = 0; thisListIterator.hasNext() ; i++)
		{
			tempList[i] = (Object) thisListIterator.next() ;
		}		
		return tempList ;
	}
		
	/**
	 * Reallocates the element of this list.
	 * 
	 * @param a represent the array into which the elements of this list are to be stored.
	 * @return an reallocated array of elements from this list.
	 */
	@Override
	public <T> T[] toArray(T[] a)
	{
		a = (T[]) new Object[a.length] ;		
		ListIterator<T> thisListIterator = (ListIterator<T>) listIterator() ;
		
		for (int i = 0; thisListIterator.hasNext() ; i++)
		{
			a[i] = (T) thisListIterator.next() ;
		}		
		return a ;
	}
	
	/**
	 * Creates a String containing all of the data in this list.
	 * 
	 * @return a string that contains all of the data in this list.
	 */
	@Override
	public String toString()
	{
		ListIterator<T> thisListIterator = listIterator() ;
		String temp = "" ;
		
		while (thisListIterator.hasNext())
		{
			temp += thisListIterator.next() + "\n" ;
		}
		return temp ;
	}
	
	/***
	 * Compares one-to-one element of two lists.
	 * 
	 * @param object represent the list to be compared with this list.
	 * @return true if every single element in both lists are equivalent; false otherwise.
	 */
	@Override
	public boolean equals(Object object)
	{
		if (object == null || getClass() != object.getClass())
		{
			return false ;
		}
		
		ListIterator<T> otherListIterator = ((List<T>) object).listIterator() ;
		ListIterator<T> thisListIterator = listIterator() ;		
		
		while (thisListIterator.hasNext() && otherListIterator.hasNext())
		{
			if (!thisListIterator.next().equals(otherListIterator.next()))
			{
				return false ;
			}					
		}
		return true ;
	}
}
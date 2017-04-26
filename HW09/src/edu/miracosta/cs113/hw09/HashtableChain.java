package edu.miracosta.cs113.hw09;

import java.util.AbstractSet ;
import java.util.Collection ;
import java.util.HashSet ;
import java.util.Iterator ;
import java.util.LinkedList ;
import java.util.Map ;
import java.util.NoSuchElementException ;
import java.util.Set ;

/**
 *	HashtableChain is a Hash table using chaining 
 *	that fully implements the Map interface.
 *  
 * @author  Angel Tapia <angelTapia07084759@gmail.com>
 * version 1.0
 *
 * @param <K> Generic type for key.
 * @param <V> Generic Type for value.
 */
public class HashtableChain<K, V> implements Map<K, V>
{
	/** The size of the table. */
	private static final int CAPACITY = 101 ;
	/** The maximum load factor */
	private static final double LOAD_THRESHOLD = 3.0 ;
	
	/** The table of references to each entry. */
	private LinkedList<Entry<K, V>>[] table ;
	/** The number of keys of entries in the table. */
	private int numKeys ;
	
	/**********************************************************
	 * Testing purpose instance variables
	 **********************************************************/
	private int numOfCollisions ;
	private double avarageLikedItems ;
	public String getTestResult()
	{
		return "TOTAL KEYS: " + numKeys 
				+ "\nTOTAL COLLISIONS: " + numOfCollisions 
				+ "\nAVERAGE LINKED LISTS: " + avarageLikedItems ;
	}
	
	
	/**********************************************************
	 * Inner class Entry that Contains 
	 * key-value pairs this hash table.
	 * Entry implements Map.Entry as a 
	 * requirement for implementing the Map interface.
	 * 
	 * @author  Angel Tapia <angelTapia07084759@gmail.com>
	 * @param <K> Generic type for key.
	 * @param <V> Generic Type for value.
	 ***********************************************************/
	private static class Entry<K, V> implements Map.Entry<K, V>
	{
		/** The key corresponding to this entry */
		private K key ;
		/** The value corresponding to this entry */
		private V value ;

		/**
		 * Constructs an entry with the given key-value pair.
		 * 
		 * @param key: The key for this entry.
		 * @param value: The value for this entry.
		 */
		public Entry(K key, V value)
		{
			this.key = key ;
			this.value = value ;
		}

		/**
		 * Accessor for key.
		 * 
		 * @return The key
		 */
		@Override
		public K getKey()
		{
			return this.key ;
		}

		/**
		 * Accessor for value.
		 * 
		 * @return The value
		 */
		@Override
		public V getValue()
		{
			return this.value ;
		}

		/**
		 * Mutator for value.
		 * 
		 * @param val: A new value for the current key.
		 * @return The old value.
		 */
		@Override
		public V setValue(V val)
		{
			V oldVal = this.value ;
			this.value = val ;
			return oldVal ;
		}

		/**
		 * Builds a string that contains the key-value of this entry.
		 * 
		 * @return The string that contains the key-value of this entry.
		 */
		public String toString()
		{
			return this.key + "=" + this.value ;
		}
	}// End of Inner class Entry
	
	/**********************************************************
	 * Inner class SetIterator is an implementation of 
	 * the Iterator interface and can be used to traverse 
	 * this hash table.
	 * 
	 * @author  Angel Tapia <angelTapia07084759@gmail.com>
	 ***********************************************************/
	private class SetIterator implements Iterator<Map.Entry<K, V>>
	{
		/** Keeps tack of the next value (starting at 0). */
		private int index ;
		/** Last item returned by next() */
		private Entry<K, V> lastItemReturned ;
		/** Traverse each LinkedList in this hash table. */
		private Iterator<Entry<K, V>> indexIterator ;

		/**
		 * Default constructor sets iterator to index 0 of this table.
		 */
		public SetIterator()
		{
			this.index = 0 ;
			this.lastItemReturned = null ;
			this.indexIterator =  null ;			
		}
		
		/**
		 * Checks if there exist more elements in this hash table.
		 * 
		 * @return true if there is more element available in the table; 
		 * 			false otherwise.
		 */
		@Override
		public boolean hasNext()
		{
			if (this.indexIterator != null && this.indexIterator.hasNext())
			{
				return true ;
			}
			else if (this.index < table.length)
			{
				while (table[this.index] == null)
				{
					if (++this.index >= table.length)
					{
						return false ;
					}
				}
				
				this.indexIterator = table[this.index++].iterator() ;		
				
				if (this.index < table.length)
				{
					return hasNext() ;
				}
				else
				{
					return this.indexIterator.hasNext() ;
				}
			}
			else
			{
				return false ;
			}
		}

		/**
		 * Access the next available element in this hash table.
		 * 
		 * @return the next element in the list.
		 * @throws NoSuchElementException if there 
		 * 			is no more elements in the list.
		 */
		@Override
		public Entry<K, V> next()
		{
			if (!hasNext())
			{
				throw new NoSuchElementException() ;
			}
			else
			{
				return this.lastItemReturned = this.indexIterator.next() ;
			}
		}

		/**
		 * Removes the element returned by the method next.
		 * precondition: can be used only after a call of the method next.
		 * 
		 * @throws IllegalStateException if this method is called before the method next.
		 */
		@Override
		public void remove()
		{
			if (this.lastItemReturned == null)
			{
				throw new IllegalStateException() ;
			}
			else
			{
				this.indexIterator.remove() ;				
				this.lastItemReturned = null ;
			}
		}
	}// End of Inner class SetIterator
	
	/**********************************************************
	 * Inner Class EntrySet is an extension of the class AbstractSet,
	 * which is a full implementation of the Set interface.
	 * EntrySet can be used to access elements of this set table to
	 * its set view.
	 * 
	 * @author  Angel Tapia <angelTapia07084759@gmail.com>
	 ***********************************************************/
	private class EntrySet extends AbstractSet<Map.Entry<K, V>>
	{
		
		/**
		 * Creates an iterator object from the inner 
		 * class SetIterator on this set.
		 * 
		 * @return an iterator on this set.
		 */
		@Override
		public Iterator<Map.Entry<K, V>> iterator()
		{
			return new SetIterator() ;
		}

		/**
		 * Access numKeys
		 * 
		 * @return the number of element in this set.
		 */
		@Override
		public int size()
		{
			return numKeys ;
		}

	}// End of Inner class EntrySet	
	
	
	
	
	/**
	 * Construct a new HashtableChain object of size CAPACITY
	 */
	@SuppressWarnings("unchecked")
	public HashtableChain()
	{
		this.table = new LinkedList[CAPACITY] ;
	}
	
	/**
	 * Relocates the table content to a new table with a
	 * bigger capacity.
	 */
	@SuppressWarnings("unchecked")
	private void rehash()
	{
		LinkedList<Entry<K, V>>[] temp = this.table ;
		this.table = new LinkedList[temp.length * 2 + 1] ;
		
		for (LinkedList<Entry<K, V>> entryList : temp)
		{
			if (entryList != null)
			{
				for (Entry<K, V> entry : entryList)
				{
					put(entry.getKey(), entry.getValue()) ;
				}
			}
		}
	}
	
	/**
	 * Resets this hash table to the initial capacity 
	 * and all of the indices to null ;
	 */
	@SuppressWarnings("unchecked")
	@Override
	public void clear()
	{		
		this.table = new LinkedList[CAPACITY] ;
	}
	
	/**
	 * Calls the method get to search for a key that matches
	 * the specified key in this hash table.
	 * 
	 * @return true if there is a match with the specified key, false otherwise. 
	 */
	@Override
	public boolean containsKey(Object key)
	{
		return get(key) != null ;
	}

	/**
	 * Checks each value in this hash table.
	 * 
	 * @return true if the specified value matches a value
	 * 			from this table; false, otherwise.
	 */
	@Override
	public boolean containsValue(Object value)
	{
		Iterator<Map.Entry<K, V>> temp = new SetIterator() ;
		
		while(temp.hasNext())
		{
			if (temp.next().getValue().equals(value))
			{
				return true ;
			}
		}		
		return false ;
	}

	/**
	 * Creates an object of the inner class EntrySet.
	 * 
	 * @return a set view of mapping contained in this map.
	 */
	@Override
	public Set<Map.Entry<K, V>> entrySet()
	{
		return new EntrySet() ;
	}

	/**
	 * Searches for a specified key in this hash table.
	 * 
	 * @return the value of the key if key is in hash table,
	 * 			null otherwise.
	 */
	@Override
	public V get(Object key)
	{
		int index  = key.hashCode() % table.length ;		
		index = index < 0 ? index + table.length : index ;
		
		if (this.table[index] == null)
		{
			return null ;
		}
		
		for (Entry<K, V> nextEntry : table[index])
		{
			if (nextEntry.key.equals(key))
			{
				return nextEntry.value ;
				
			}
		}		
		return null ;
	}

	/**
	 * Access numKeys
	 * 
	 * @return true if numKeys == 0, false otherwise.
	 */
	@Override
	public boolean isEmpty()
	{
		return this.numKeys == 0 ;
	}

	/**
	 * Iterates over this hash table and creates set view of its keys.
	 * 
	 * @return a set view of the keys of this hash table.
	 */
	@Override
	public Set<K> keySet()
	{
		Set<K> keySet = new HashSet<K>(size()) ;
		
		Iterator<Map.Entry<K, V>> temp = new SetIterator() ;
		
		while(temp.hasNext())
		{
			keySet.add(temp.next().getKey()) ;
		}		
		return keySet ;
	}
	
	/**
	 * Inserts a key-value pair in table.
	 * 
	 * @param key: The key for the new value.
	 * @param value: The value to be stored.
	 * @return if the key already exist, returns the old value; null otherwise.
	 */
	@Override
	public V put(K key, V value)
	{
		int index  = key.hashCode() % this.table.length ;		
		index = index < 0 ? index + this.table.length : index ;

		if (this.table[index] == null)
		{
			this.table[index] = new LinkedList<Entry<K, V>>() ;
		}
				
		for (Entry<K, V> nextEntry : table[index])
		{
			if (nextEntry.key.equals(key))
			{
				V oldVal = nextEntry.value ;
				nextEntry.setValue(value) ;
				return oldVal ;
			}
		}		
		
		// START OF TESTING CODE
		if (!this.table[index].isEmpty())// Testing purpose variable
		{
			this.numOfCollisions++ ;// counts the number of collisions
		}
		this.avarageLikedItems = 1 // Average length of each linked list
				+ (((double) (this.numKeys + 1)  / (double) this.table.length ) / 2.0) ;
		// END TESTING CODES
		
		this.table[index].addFirst(new Entry<K, V>(key, value)) ;
		this.numKeys++ ;		
		
		if(this.numKeys > LOAD_THRESHOLD * this.table.length)
		{
			rehash() ;
		}		
		return null ;
	}

	/**
	 * putAll is not supported by this table.
	 * 
	 * @throws UnsupportedOperationException at this method call.
	 */
	@Override
	public void putAll(Map<? extends K, ? extends V> arg0)
	{		
		throw new UnsupportedOperationException("putAll is not supported by this table!") ;
	}
	
	/**
	 * Removes a value with the specified key.
	 * 
	 * @return The value with the specified key, null if key does not exist
	 */
	@Override
	public V remove(Object key)
	{
		int index  = key.hashCode() % table.length ;		
		index = index < 0 ? index + table.length : index ;
		
		if (this.table[index] == null)
		{
			return null ;
		}
		
		Iterator<Entry<K, V>> search = this.table[index].iterator() ;
		while (search.hasNext())
		{
			Entry<K, V> temp = search.next() ;
			
			if (temp.key.equals(key))
			{
				search.remove() ;
				
				if (this.table[index].isEmpty())
				{
					this.table[index] = null ;
				}
				this.numKeys-- ;
				return temp.value ;					
			}
		}		
		return null ;
	}

	/**
	 * Access numKeys.
	 * 
	 * the number of element in this hash table.
	 */
	@Override
	public int size()
	{
		return this.numKeys ;
	}

	/**
	 * Iterates over this hash table and creates collection view of its values.
	 * 
	 * @return a collection view of the values of this hash table
	 */
	@Override
	public Collection<V> values()
	{
		Collection<V> values = new HashSet<V>(size()) ;
		Iterator<Map.Entry<K, V>> temp = new SetIterator() ;
		
		while(temp.hasNext())
		{
			values.add(temp.next().getValue()) ;
		}
		return values ;
	}
	
	/**
	 * Builds a string representation of this hash table
	 * of the form [index] ==> [key=value]
	 * 
	 * @return a string representation of this hash table.
	 */
	@Override
	public String toString()
	{
		StringBuilder sb = new StringBuilder() ;
		Iterator<Map.Entry<K, V>> temp = new SetIterator() ;
		
		while(temp.hasNext())
		{
			Map.Entry<K, V> entry = temp.next() ;
			int currentIndex = entry.getKey().hashCode() % this.table.length ;
			currentIndex = currentIndex < 0 ? currentIndex + this.table.length : currentIndex ; 
			
			sb.append(String.format("[%3s] ==> [%s]%n", currentIndex, entry )) ;
			
		}		
		return sb.toString() ;
	}
	
	/**
	 * Compares the toString method of this and another hash table chain.
	 * 
	 * @return true if the toString methods are equivalents, false otherwise.
	 */
	@SuppressWarnings("unchecked")
	@Override
	public boolean equals(Object obj)
	{
		if (obj == null || getClass() != obj.getClass())
		{
			return false ;
		}
		HashtableChain<K, V> other = (HashtableChain<K, V>) obj ;
		return toString().equals(other.toString()) ;
	}
}
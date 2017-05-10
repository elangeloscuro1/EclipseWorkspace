package edu.miracosta.cs113.hw11;

/**
 * SearchTree is an interface for a class that implements the BinarySearchTree.
 * 
 * @author Angel Tapia <angelTapia07084759@gmail.com>
 * 		   reference: Koffman and Wolfgang pg.317
 * @version 1.0
 * @param <E>  Generic type parameter
 */
public interface SearchTree<E>
{
	/**
	 * Inserts the item in the corresponding node in the tree.
	 * 
	 * @param item: the item to be inserted.
	 * @return true if the item is successfully inserted; false if the 
	 * 			item already exists.
	 */
	public boolean add(E item) ;
	
	/**
	 * Searches for a specified element.
	 * 
	 * @param target: the element to be searched.
	 * @return true if the item is in the tree; false otherwise.
	 */
	public boolean contains(E target) ;
	
	/**
	 * Searches for a specified element to obtain its reference.
	 * 
	 * @param target: the element to be searched.
	 * @return the reference of the data that is equals to the target; 
	 * 			return null if the target is not in the tree.
	 */
	public E find(E target) ;
	
	/**
	 * Deletes an specified element.
	 * 
	 * @param target: the element to be deleted.
	 * @return the deleted element; null if the element is not found.
	 */
	public E delete(E target) ;
	
	/**
	 * Deletes an specified element.
	 * 
	 * @param target: the element to be deleted.
	 * @return true if the element is successfully deleted; false otherwise.
	 */
	public boolean remove(E target) ;
}
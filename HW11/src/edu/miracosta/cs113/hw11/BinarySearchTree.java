package edu.miracosta.cs113.hw11;

/**
 * BinarySearchTree extends BinaryTree and implements 
 * the add method of the SearchTree interface.
 * 
 * @author Angel Tapia <angelTapia07084759@gmail.com>
 * 		   reference: Koffman and Wolfgang pg.317
 * @version 1.0
 * @param <E>  Generic type parameter
 */
@SuppressWarnings("serial")
public class BinarySearchTree<E extends Comparable<E>> extends BinaryTree<E> implements SearchTree<E>
{
	/** Stores a second return value(type boolean) from the recursive add method */
	protected boolean addReturn ;
	/** stores a second return values (type E) from the recursive (not implemented)*/
	protected E deleteReturn ;
	
	/** Counts the number of item in this tree. */
	private int totalItems ;
	
	/**
	 * Stub Method: contains
	 */
	@Override
	public boolean contains(E target)
	{
		return false ;
	}	
	
	/**
	 * Stub Method: find
	 */
	@Override
	public E find(E target)
	{
		return target ;		
	}
	
	/**
	 * Stub Method: contains
	 */
	@Override
	public E delete(E target)
	{
		return target ;		
	}
	
	/**
	 * Stub Method: remove
	 */
	@Override
	public boolean remove(E target)
	{
		return false ;
	}	
	
	/**Reference: pg. 321
	 * Starter method add.
	 * pre: The object to insert must implement the 
	 *      Comparable interface
	 * @param item the object being inserted
	 * @return true if the object is inserted, false
	 * 			if the object is already exists in the tree.
	 */
	@Override
	public boolean add(E item)
	{
		root = add(root, item) ;
		this.totalItems += addReturn ? 1 : 0 ;
		return addReturn ;
	}

	/**Reference: pg. 321
	 * Recursive add method.
	 * post: The data field addReturn is set true if the item is added to
	 *       the tree, false if the item is already in the tree.
	 * @param localRoot the local root of the subtree
	 * @param item The object to be inserted
	 * @return The new local root that now contains the inserted item
	 */
	private Node<E> add(Node<E> localRoot, E item)
	{
		if (localRoot == null)
		{
			addReturn = true ;
			return new Node<E>(item) ;
		}
		else if (item.compareTo(localRoot.data) == 0)
		{
			addReturn = false ;
			return localRoot ;
		}
		else if (item.compareTo(localRoot.data) < 0)
		{
			localRoot.left = add(localRoot.left, item) ;
			return localRoot ;
		}
		else
		{
			localRoot.right = add(localRoot.right, item) ;
			return localRoot ;
		}
	}
	
	/**
	 * Accesor for TotalItems.
	 * 
	 * @return the number of items in this tree.
	 */
	public int getTotalItems()
	{
		return totalItems ;
	}

	/**
	 * Compares the height of the left and right subtrees recursively.
	 * 
	 * @param node: the current node to compare left and right.
	 * @return the greatest height value between the left and the right subtrees.
	 */
	public int height(Node<E> node)
	{
		if (node == null)
		{
			return 0 ;
		}
		else
		{
			int leftHeight = height(node.left) ;
			int rightHeight = height(node.right) ;
			return 1 + (leftHeight > rightHeight ? leftHeight : rightHeight) ;
		}
	}
	
	/**
	 * Builds a string that contains a representation of the tree
	 * in preorder traversal.
	 * 
	 * @return a string representation of the tree in preorder traversal.
	 */
	public String preOrderTraverse()
	{
		StringBuilder sb = new StringBuilder() ;
		preOrderTraverse(this.root, sb) ; 
		return sb.toString() ;	
	}
	
	/**
	 * Builds a string that contains a representation of the tree
	 * in inorder traversal.
	 * 
	 * @return a string representation of the tree in inorder traversal.
	 */
	public String inOrderTraverse()
	{
		StringBuilder sb = new StringBuilder() ;
		inOrderTraverse(this.root, sb) ; 
		return sb.toString() ;		
	}
	
	/**
	 * Builds a string that contains a representation of the tree
	 * in postorder traversal.
	 * 
	 * @return a string representation of the tree in postorder traversal.
	 */
	public String postOrderTraverse()
	{
		StringBuilder sb = new StringBuilder() ;
		postOrderTraverse(this.root, sb) ; 
		return sb.toString() ;		
	}
}
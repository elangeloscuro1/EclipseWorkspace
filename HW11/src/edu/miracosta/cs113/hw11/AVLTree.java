package edu.miracosta.cs113.hw11 ;

/**
 * AVLTree is an implementation of a self-balancing tree
 * as described by Koffman and Wolfgang.
 * 
 * @author Angel Tapia <angelTapia07084759@gmail.com>
 * 		   reference: Koffman and Wolfgang pg.481
 * @version 1.0
 * @param <E>  Generic type parameter
 */
@SuppressWarnings("serial")
public class AVLTree<E extends Comparable<E>> extends BinarySearchTreeWithRotate<E>
{
	/**Indicates that height of the tree has increased. */
	private boolean increase ;	

	/**Reference: pg. 482
	 * AVLNode extends the protected inner class Node from Binary tree.
	 * AVLNode helps to maintain the balance for this AVLTree.
	 * reference: Koffman and Wolfgang pg.482
	 * 
	 * @param <E>  Generic type parameter
	 */
	private static class AVLNode<E> extends Node<E>
	{
		/** Constant to indicate left-heavy */
		public static final int LEFT_HEAVY = -1 ;

		/** Constant to indicate balanced */
		public static final int BALANCED = 0 ;

		/** Constant to indicate right-heavy */
		public static final int RIGHT_HEAVY = 1 ;

		/** balance is right subtree height - left subtree height */
		private int balance ;

		/**
		 * Constructs the node with the given value as the data field.
		 * 
		 * @param item: The data field.
		 */
		public AVLNode(E item)
		{
			super(item) ;
			balance = BALANCED ;
		}
		
		/**
		 * Returns a string representation of this object.
		 * The value content is appended to the contents.
		 * @return a string representation of this object.
		 */
		@Override
		public String toString()
		{
			return balance + ":" + super.toString() ;
		}
	}

	/**Reference: pg. 484 add starter method.	 * 
	 * @pre the item to insert implements the Comparable interface.
	 * @param item The item being inserted.
	 * @return true if the object is inserted; false if the object already
	 *         exists in the tree
	 * @throws ClassCastException if item is not Comparable
	 */
	@Override
	public boolean add(E item)
	{
		increase = false ;
		root = add((AVLNode<E>) root, item) ;
		return addReturn ;
	}

	/**Reference: pg. 484 
	 * Recursive add method. Inserts the given object into the tree.
	 * 
	 * @post addReturn is set true if the item is inserted, false if the item is
	 *       already in the tree.
	 * @param localRoot: The local root of the subtree
	 * @param item: The object to be inserted
	 * @return The new local root of the subtree with the item inserted
	 */
	private AVLNode<E> add(AVLNode<E> localRoot, E item)
	{

		if (localRoot == null)
		{
			addReturn = true ;
			increase = true ;
			return new AVLNode<E>(item) ;
		}

		if (item.compareTo(localRoot.data) == 0)
		{
			increase = false ;
			addReturn = false ;
			return localRoot ;
		}
		if (item.compareTo(localRoot.data) < 0)
		{
			localRoot.left = add((AVLNode<E>) localRoot.left, item) ;

			if (increase)
			{
				decrementBalance(localRoot) ;
				if (localRoot.balance < AVLNode.LEFT_HEAVY)
				{
					increase = false ;
					return rebalanceLeft(localRoot) ;
				}
			}
		}
		if (item.compareTo(localRoot.data) > 0)
		{
			localRoot.right = add((AVLNode<E>) localRoot.right, item) ;

			if (increase)
			{
				incrementBalance(localRoot) ;
				if (localRoot.balance > AVLNode.RIGHT_HEAVY)
				{
					increase = false ;
					return rebalanceRight(localRoot) ;
				}
			}
		}
		return localRoot ;
	}

	/**Reference: pg. 487
	 * Method to rebalance left.
	 * @pre localRoot is the root of an AVL subtree that is critically left‐heavy.
	 * @post Balance is restored.
	 * @param localRoot Root of the AVL subtree that needs rebalancing
	 * @return a new local root.
	 */
	private AVLNode<E> rebalanceLeft(AVLNode<E> localRoot)
	{
		AVLNode<E> leftChild = (AVLNode<E>) localRoot.left ;
		
		if (leftChild.balance > AVLNode.BALANCED)
		{
			AVLNode<E> leftRightChild = (AVLNode<E>) leftChild.right ;
			
			if (leftRightChild.balance < AVLNode.BALANCED)
			{
				leftChild.balance = AVLNode.BALANCED ;
				leftRightChild.balance = AVLNode.BALANCED ;
				localRoot.balance = AVLNode.RIGHT_HEAVY ;
			}
			else if (leftRightChild.balance > AVLNode.BALANCED)
			{
				leftChild.balance = AVLNode.LEFT_HEAVY ;
				leftRightChild.balance = AVLNode.BALANCED ;
				localRoot.balance = AVLNode.BALANCED ;
			}
			else
			{
				leftChild.balance = AVLNode.BALANCED ;
				localRoot.balance = AVLNode.BALANCED ;
			}
			localRoot.left = rotateLeft(leftChild) ;
		}
		else
		{
			leftChild.balance = AVLNode.BALANCED ;
			localRoot.balance = AVLNode.BALANCED ;
		}
		return (AVLNode<E>) rotateRight(localRoot) ;
	}

	/**
	 * rebalanceRight is symmetric with respect to the rebalanceLeft method.
	 * 
	 * @param localRoot Root of the AVL subtree that needs rebalancing.
	 * @return a new local root.
	 */
	private AVLNode<E> rebalanceRight(AVLNode<E> localRoot)
	{
		AVLNode<E> rightChild = (AVLNode<E>) localRoot.right ;

		if (rightChild.balance < AVLNode.BALANCED)
		{
			AVLNode<E> rightLeftChild = (AVLNode<E>) rightChild.left ;
			
			if (rightLeftChild.balance > AVLNode.BALANCED)
			{
				rightChild.balance = AVLNode.BALANCED ;
				rightLeftChild.balance = AVLNode.BALANCED ;
				localRoot.balance = AVLNode.LEFT_HEAVY ;
			}
			else if (rightLeftChild.balance < AVLNode.BALANCED)
			{
				rightChild.balance = AVLNode.RIGHT_HEAVY ;
				rightLeftChild.balance = AVLNode.BALANCED ;
				localRoot.balance = AVLNode.BALANCED ;
			}
			else
			{
				rightChild.balance = AVLNode.BALANCED ;
				localRoot.balance = AVLNode.BALANCED ;
			}			
			localRoot.right = rotateRight(rightChild) ;
		}
		else
		{
			rightChild.balance = AVLNode.BALANCED ;
			localRoot.balance = AVLNode.BALANCED ;
		}
		return (AVLNode<E>) rotateLeft(localRoot) ;
	}	

	/**Reference: pg. 488
	 * Decrements the balance of the node.
	 * 
	 * @param node: the node to be decremented.
	 */
	private void decrementBalance(AVLNode<E> node)
	{
		node.balance-- ;
		if (node.balance == AVLNode.BALANCED)
		{
			increase = false ;
		}
	}

	/**
	 * Increments the balance of the node.
	 * 
	 * @param node: the node to be incremented.
	 */
	private void incrementBalance(AVLNode<E> node)
	{
		node.balance++ ;
		if (node.balance == AVLNode.BALANCED)
		{
			increase = false ;
		}
	}
}
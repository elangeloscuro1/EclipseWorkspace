package edu.miracosta.cs113.hw11;
// AVLTree.java



/** Self-balancing binary search tree using the algorithm defined
 * by Adelson-Velskii and Landis (AVL)
 * 
 * @author Carlos R. Avila
 *
 */

public class AVLTree<E extends Comparable<E>> 
		extends BinarySearchTreeWithRotate <E> {

	private static final long serialVersionUID = 1L;

	// Nested class AVLNode<E>
	// The AVLNode Class (Listing 9.2)
	/** Class to represent an AVL Node. It extends the
	BinaryTree.Node by adding the balance field. */

	private static class AVLNode<E> extends Node<E> {

		private static final long serialVersionUID = 1L;

		/** Constant to indicate left-heavy */
		public static final int LEFT_HEAVY = -1;
		
		/** Constant to indicate balanced */
		public static final int BALANCED = 0;
		
		/** Constant to indicate right-heavy */
		public static final int RIGHT_HEAVY = 1;
		
		/** balance is right subtree height - left subtree height */
		private int balance;

		// Methods
		/** Construct a node with the given item as the data field.
		 * 	@param item The data field
		 */
		public AVLNode(E item) {
			super(item);
			balance = BALANCED;
		}

		/** Return a string representation of this object.
		 * 	The balance value is appended to the contents.
		 * 
		 * 	@return String representation of this object
		 */

		@Override
		public String toString() {
			return balance+":"+ super.toString();
		}
	}

	// Data Fields
	/** Flag to indicate that height of the tree has increased. */
	private boolean increase;
	
	/** Algorithm for Insertion into an AVL Tree 
	 * 
	 *  1.  if the root is null 
	 *  2.        Create a new tree with the item at the root and return true. 
	 *      else if the item is equal to root. data 
	 *  3.        The item is already in the tree; return false. 
	 *      else if the item is less than root. data 
	 *  4.        Recursively insert the item in the left subtree. 
	 *  5.        if the height of the left subtree has increased (increase is true) 
	 *  6.             Decrement balance. 
	 *  7.             if balance is zero, reset increase to false. 
	 *  8.             if balance is less than -1 
	 *  9.             Reset increase to false. 
	 *  10.            Perform a rebalanceLeft. 
	 *  11. else if the item is greater than root.data 
	 *  11.      The processing is symmetric to Steps 4 through 10. Note that balance 
	 *           is incremented if increase is true.
	 */
	

	// Methods
	/** add starter method.
	 * 	pre: the item to insert implements the Comparable interface.
	 *  @param item The item being inserted.
	 *  @return true if the object is inserted; false
	 *      	if the object already exists in the tree
	 *  @throws Cl assCastExcepti on¥ if item is not Comparable
	 */
	@Override
	public boolean add(E item) {
		increase = false;
		root = add((AVLNode<E>) root, item);
		return addReturn;
	}

	/** Recursive add method. Inserts the given object into the tree.
	 * 	post: addReturn is set true if the item is inserted,
	 *		  false if the item is already in the tree.
	 *	@param localRoot The local root of the subtree
	 *	@param item The object to be inserted
	 *	@return The new local root of the subtree with the item
	 *          inserted
	 */
	private AVLNode<E> add(AVLNode<E> localRoot, E item) {

		if (localRoot == null) {
			addReturn = true;
			increase = true;
			return new AVLNode<E>(item);
		}
	
		if (item.compareTo(localRoot.data) == 0) {
			// Item is already in the tree.
			System.out.println("  Item \"" + item + "\" is already in the tree (item was not added)");
			increase = false;
			addReturn = false;
			return localRoot;
		}

		// If it is less than this value, we recursively call the add method 
		// (Step 4 of the insertion algorithm), passing localRoot.left as the 
		// parameter and replacing the value of localRoot.left with the 
		// returned value.
		if (item.compareTo(localRoot.data) < 0) {
			//item < data
			localRoot.left = add((AVLNode<E>) localRoot.left, item);

			// Upon return from the recursion, we examine the global data field 
			// increase. If increase is true, then the height of the left 
			// subtree has increased, so we decrement the balance by calling 
			// the decrementBalance method. If the balance is now less than	-1, 
			// we reset increase to false and call the rebalanceLeft method. The 
			// return value from the rebalanceLeft method is the return value 
			// from this call to add. If the balance is not less than -1, or if 
			// the left subtree height did not increase, then the return from 
			// this recursive call is the same local root that was passed as 
			// the parameter.
			if (increase) {
				decrementBalance(localRoot);
				if (localRoot.balance < AVLNode.LEFT_HEAVY) {
					increase = false;
					return rebalanceLeft(localRoot);
				}
			}	
		}
		
		// If it is greater than this value, we recursively call the add method 
		// (Step 4 of the insertion algorithm), passing localRoot.right as the 
		// parameter and replacing the value of localRoot.right with the 
		// returned value.
		if (item.compareTo(localRoot.data) > 0) {
			//item > data
			localRoot.right = add((AVLNode<E>) localRoot.right, item);

			// Upon return from the recursion, we examine the global data field 
			// increase. If increase is true, then the height of the right 
			// subtree has increased, so we increment the balance by calling 
			// the incrementBalance method. If the balance is now greater than 1, 
			// we reset decrease to false and call the rebalanceRight method. The 
			// return value from the rebalanceRight method is the return value 
			// from this call to add. If the balance is not greater than 1, or if 
			// the right subtree height did not increase, then the return from 
			// this recursive call is the same local root that was passed as 
			// the parameter.
			if (increase) {
				incrementBalance(localRoot);
				if (localRoot.balance > AVLNode.RIGHT_HEAVY) {
					increase = false;
					return rebalanceRight(localRoot);
				}
			}	
		}
		
		return localRoot;
	}

	/** Initial Algorithm for rebalanceLeft 
	 *  Method rebalanceLeft rebalances a left-heavy tree. Such a tree 
	 *  can be a Left-Left tree (fixed by a single right rotation) or a 
	 *  Left-Right tree (fixed by a left rotation followed by a right 
	 *  rotation). If its left subtree is right-heavy, we have a 
	 *  Left-Right case, so we first rotate left around the left 
	 *  subtree. Finally we rotate the tree right. 
	 *  
	 *  1. if the left subtree has positive balance (Left-Right case) 
	 *  2.       if the left-left subtree has a negative balance (Left-Right-Left case) 
	 *  3.             Set the left subtree (new left subtree) balance to 0.
	 *  4.             Set the left-left subtree (new root) balance to 0.
	 *  5.             Set the local root (new right subtree) balance to -1. 
	 *         else if the left-left subtree has a positive balance (Left-Right-Right case) 
	 *  6.             Set the left subtree (new left subtree) balance to -1.
	 *  7.             Set the left-left subtree (new root) balance to 0.
	 *  8.             Set the local root (new right subtree) balance to 0.
	 *            else (Left-Right Balanced case) 
	 *  9.             Set the left subtree (new left subtree) balance to 0
	 *  10.            Set the local root (new right subtree) balance to 0
	 *  11.       Rotate the left subtree
	 *        else (Left-Left case)
	 *  12.       Set the left subtree balance to 0
	 *  13.       Set the local root balance to 0
	 *  14.   Rotate the local root right
	 *  			 
	 */
	// LISTING 9.3 - The rebalanceLeft Method
	/** Method to rebalance left.
	 * 	pre: localRoot is the root of an AVL subtree that is
	 *       critically left-heavy.
	 *  post: Balance is restored.
	 *  @param localRoot Root of the AVL subtree
	 *         that needs rebalancing
	 *  @return a new localRoot
	 */
	private AVLNode<E> rebalanceLeft(AVLNode<E> localRoot) {
		// Obtain reference to left child.
		AVLNode<E> leftChild = (AVLNode<E>) localRoot.left;
		// See whether left-right heavy.
		if (leftChild.balance > AVLNode.BALANCED) {
			// Obtain reference to left-right child.
			AVLNode<E> leftRightChild = (AVLNode<E>) leftChild.right;
			/** Adjust the balances to be their new values after
			 * 	the rotations are performed.
			 */
			if (leftRightChild.balance < AVLNode.BALANCED) {
				leftChild.balance = AVLNode.BALANCED;
				leftRightChild.balance = AVLNode.BALANCED;
				localRoot.balance = AVLNode.RIGHT_HEAVY;
			} else if (leftRightChild.balance > AVLNode.BALANCED) {
				leftChild.balance = AVLNode.LEFT_HEAVY;
				leftRightChild.balance = AVLNode.BALANCED;
				localRoot.balance = AVLNode.BALANCED ;
			} else {
				leftChild.balance = AVLNode.BALANCED;
				localRoot.balance = AVLNode.BALANCED ;
			}
			// Perform left rotation.
			localRoot.left = rotateLeft(leftChild);
		} else { 
			// Left-Left case
			/** In this case the leftChild (the new root)
			 *  and the root (new right child) wi77 both be balanced
			 *  after the rotation.
			 */
			leftChild.balance = AVLNode.BALANCED;
			localRoot.balance = AVLNode.BALANCED ;
		}
		// Now rotate the local root right.
		return (AVLNode<E>) rotateRight(localRoot) ;
	}
	
	/** Initial Algorithm for rebalanceRight
	 *  Method rebalanceRight rebalances a right-heavy tree. Such a tree 
	 *  can be a Right-Right tree (fixed by a single left rotation) or a 
	 *  Right-Left tree (fixed by a right rotation followed by a left 
	 *  rotation). If its right subtree is left-heavy, we have a 
	 *  Right-Left case, so we first rotate right around the right 
	 *  subtree. Finally we rotate the tree left. 
	 *  
	 *  1. if the right subtree has positive balance (Right-Left case) 
	 *  2.       if the right-right subtree has a positive balance (Right-Left-Right case) 
	 *  3.             Set the right subtree (new right subtree) balance to 0.
	 *  4.             Set the right-right subtree (new root) balance to 0.
	 *  5.             Set the local root (new left subtree) balance to -1. 
	 *         else if the right-right subtree has a negative balance (Right-Left-Left case) 
	 *  6.             Set the right subtree (new right subtree) balance to -1.
	 *  7.             Set the right-right subtree (new root) balance to 0.
	 *  8.             Set the local root (new left subtree) balance to 0.
	 *            else (Right-Left Balanced case) 
	 *  9.             Set the right subtree (new right subtree) balance to 0
	 *  10.            Set the local root (new left subtree) balance to 0
	 *  11.       Rotate the right subtree
	 *        else (Right-Right case)
	 *  12.       Set the right subtree balance to 0
	 *  13.       Set the local root balance to 0
	 *  14.   Rotate the local root left
	 *  			 
	 */
	// LISTING 9.3 - The rebalanceRight Method
	/** Method to rebalance right.
	 * 	pre: localRoot is the root of an AVL subtree that is
	 *       critically right-heavy.
	 *  post: Balance is restored.
	 *  @param localRoot Root of the AVL subtree
	 *         that needs rebalancing
	 *  @return a new localRoot
	 */
//	private AVLNode<E> rebalanceRight(AVLNode<E> localRoot) {
//		// Obtain reference to right child.
//		AVLNode<E> rightChild = (AVLNode<E>) localRoot.right;
//		// See whether right-left heavy.
//		if (rightChild.balance > AVLNode.BALANCED) {
//			// Right-Right case
//			/** In this case the rightChild (the new root)
//			 *  and the root (new left child) will both be balanced
//			 *  after the rotation.
//			 */
//			rightChild.balance = AVLNode.BALANCED;
//			localRoot.balance = AVLNode.BALANCED ;
//		} else { 
//			// Obtain reference to right-left child.
//			AVLNode<E> rightLeftChild = (AVLNode<E>) rightChild.left;
//			/** Adjust the balances to be their new values after
//			 * 	the rotations are performed.
//			 */
//			if (rightLeftChild.balance < AVLNode.BALANCED) {
//				rightChild.balance = AVLNode.BALANCED;
//				rightLeftChild.balance = AVLNode.BALANCED;
//				localRoot.balance = AVLNode.RIGHT_HEAVY; // check this
//			} else if (rightLeftChild.balance > AVLNode.BALANCED) {
//				rightChild.balance = AVLNode.LEFT_HEAVY; // check this
//				rightLeftChild.balance = AVLNode.BALANCED;
//				localRoot.balance = AVLNode.BALANCED ;
//			} else {
//				rightChild.balance = AVLNode.BALANCED;
//				localRoot.balance = AVLNode.BALANCED ;
//			}
//			// Perform right rotation.
//			localRoot.right = rotateRight(rightChild);
//		} 
//		// Now rotate the local root left.
//		return (AVLNode<E>) rotateLeft(localRoot) ;
//	}
	
	private AVLNode<E> rebalanceRight(AVLNode<E> localRoot)
	{
		// Obtain reference to right child.
		AVLNode<E> rightChild = (AVLNode<E>) localRoot.right ;
		// See whether right-left heavy.
		if (rightChild.balance < AVLNode.BALANCED)
		{
			// Obtain reference to right-left child.
			AVLNode<E> rightLeftChild = (AVLNode<E>) rightChild.left ;System.out.println("Right Left heavy:" + localRoot.data) ;
			/**
			 * Adjust the balances to be their new values after the rotations
			 * are performed.
			 */
			if (rightLeftChild.balance > AVLNode.BALANCED)
			{
				rightChild.balance = AVLNode.BALANCED ;
				rightLeftChild.balance = AVLNode.BALANCED ;
				localRoot.balance = AVLNode.LEFT_HEAVY ; // check this
			}
			else if (rightLeftChild.balance < AVLNode.BALANCED)
			{
				rightChild.balance = AVLNode.RIGHT_HEAVY ; // check this
				rightLeftChild.balance = AVLNode.BALANCED ;
				localRoot.balance = AVLNode.BALANCED ;
			}
			else
			{
				rightChild.balance = AVLNode.BALANCED ;
				localRoot.balance = AVLNode.BALANCED ;
			}
			// Perform right rotation.
			localRoot.right = rotateRight(rightChild) ;
		}
		else
		{
			// Right-Right case
			/**
			 * In this case the rightChild (the new root) and the root (new left
			 * child) will both be balanced after the rotation.
			 */
			rightChild.balance = AVLNode.BALANCED ;System.out.println("!!!!!Right Left heavy") ;
			localRoot.balance = AVLNode.BALANCED ;

		}
		// Now rotate the local root left.
		return (AVLNode<E>) rotateLeft(localRoot) ;
	}



	/** The decrementBalance Method
	 *  As we return from an insertion into a node's left subtree, we need to 
	 *  decrement the balance of the node. We also need to indicate whether the 
	 *  subtree height at that node has not increased, by setting increase 
	 *  (currently true) to false. There are two cases to consider: a node that
	 *  is balanced and a node that is right-heavy. If a node is balanced, 
	 *  insertion into its left subtree will cause it to become left-heavy, and 
	 *  its height will also increase by 1 (see Figure 9.19). If a node is 
	 *  right-heavy, insertion into its left subtree will cause it to become 
	 *  balanced, and its height will not increase (see Figure 9.20).
	 *  
	 */
	private void decrementBalance(AVLNode<E> node) {
		// Decrement the balance.
		node.balance--;
		if (node.balance == AVLNode.BALANCED) {
			/** If now balanced, overall height has not increased. */
			increase = false;
		}
	}


	/** The incrementBalance Method
	 *  As we return from an insertion into a node's right subtree, we need to 
	 *  increment the balance of the node. We also need to indicate whether the 
	 *  subtree height at that node has not increased, by setting increase 
	 *  (currently true) to false. There are two cases to consider: a node that
	 *  is balanced and a node that is left-heavy. If a node is balanced, 
	 *  insertion into its right subtree will cause it to become right-heavy, and 
	 *  its height will also increase by 1 (see Figure 9.19). If a node is 
	 *  left-heavy, insertion into its right subtree will cause it to become 
	 *  balanced, and its height will not increase (see Figure 9.20).
	 *  
	 */
	private void incrementBalance(AVLNode<E> node) {
		// Increment the balance.
		node.balance++;
		if (node.balance == AVLNode.BALANCED) {
			/** If now balanced, overall height has not increased. */
			increase = false;
		}
	}
	
}

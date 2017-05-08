package edu.miracosta.cs113.lab17 ;

/**
 * Algorithm:
 * 	 Obtain the reference to the right child.
 * 		-If the right subtree has a negative balance (Right-Left case)
 * 			Obtain the reference of the right-left child.
 * 			if right-left child has a positive balance
 * 				set right child to BALANCED
 * 				set right-left child to BALANCED
 * 				set local root to LEFT_HEAVY
 * 			else if right-left child has a negative balance
 * 				set right child to RIGHT_HEAVY
 * 				set right-left child to BALANCED
 * 				set local root to BALANCED
 * 			else (is balanced)
 * 				set right child to BALANCED
 * 				set local root to LEFT_HEAVY
 * 			Rotate the right subtree right
 * 		Else (Right-Right case)
 * 			set the right subtree to BALANCED
 * 			set the local root to BALANCED
 * 		Rotate the local root to left.
 */
/**
 * Lab17RebalanceRight contains a single pre-defined method rebalanceRight
 * that represents the rebalanceRight of a Self-balancing Search Tree.
 * 
 * @author Angel Tapia<Angeltapia07084759@gmail.com>
 * version 1.0
 */
public class Lab17RebalanceRight
{
	/**
	 * Method that rebalances to the right.
	 * 
	 * @param localRoot a subtree that needs rebalancing to the right.
	 * @return a new rebalanced local root.
	 */
/*	private AVLNode<E> rebalanceRight(AVLNode<E> localRoot)
	{
		// Obtain reference to right child.
		AVLNode<E> rightChild = (AVLNode<E>) localRoot.right ;
		// See whether right-left heavy.
		if (rightChild.balance < AVLNode.BALANCED)
		{
			// Obtain reference to right-left child.
			AVLNode<E> rightLeftChild = (AVLNode<E>) rightChild.left ;
			
			if (rightLeftChild.balance > AVLNode.BALANCED)// right-left-right
			{
				rightChild.balance = AVLNode.BALANCED ;
				rightLeftChild.balance = AVLNode.BALANCED ;
				localRoot.balance = AVLNode.LEFT_HEAVY ;
			}
			else if (rightLeftChild.balance < AVLNode.BALANCED)// right-left-left
			{
				rightChild.balance = AVLNode.RIGHT_HEAVY ;
				rightLeftChild.balance = AVLNode.BALANCED ;
				localRoot.balance = AVLNode.BALANCED ;
			}
			else// is balanced
			{
				rightChild.balance = AVLNode.BALANCED ;
				localRoot.balance = AVLNode.BALANCED ;
			}
			//Rotate the right subtree right
			localRoot.right = rotateRight(rightChild) ;
		}
		else//Right-Right case
		{
			rightChild.balance = AVLNode.BALANCED ;
			localRoot.balance = AVLNode.BALANCED ;
		}
		//Rotate the local root to left.
		return (AVLNode<E>) rotateLeft(localRoot) ;
	}	
*/
}
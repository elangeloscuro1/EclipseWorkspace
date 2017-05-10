package edu.miracosta.cs113.hw11;

/**
 * BinarySearchTreeWithRotate extends the BinaruSearchTree and adds
 * a rotate operation that helps the tree to maintain its balance.
 * 
 * @author Angel Tapia <angelTapia07084759@gmail.com>
 * 		   reference: Koffman and Wolfgang pg.475
 * @version 1.0
 * @param <E>  Generic type parameter
 */
@SuppressWarnings("serial")
public class BinarySearchTreeWithRotate<E extends Comparable<E>> extends BinarySearchTree<E>
{

	/**Reference pg. 476: Listing 9.1
	 * Method to perform a right rotation (rotateRight)
	 * pre:  root is the root of a binary search tree:
	 * post: root.right is the root of a binary search tree,
	 *       root.right.right is raised one level,
	 *       root.right.left does not change levels,
	 *       root.left is lowered one level,
	 *       the new root is returned.
	 * @param  root The root of the binary tree to be rotated
	 * @return The new root of the rotated tree
	 */
	protected Node<E> rotateRight(Node<E> root)
	{
		Node<E> temp = root.left ;
		root.left = temp.right ;
		temp.right = root ;
		return temp ;
	}

	/**Reference pg. 476: Listing 9.1
	 * Method to perform a left rotation (rotateLeft).
	 * (Programming Exercise 1)
	 * pre:  root is the root of a binary search tree:
	 * post: root.left is the root of a binary search tree,
	 *       root.left.left is raised one level,
	 *       root.left.right does not change levels,
	 *       root.right is lowered one level,
	 *       
	 *       the new root is returned.
	 * @param  root The root of the binary tree to be rotated
	 * @return The new root of the rotated tree
	 */
	protected Node<E> rotateLeft(Node<E> root)
	{
		Node<E> temp = root.right ;
		root.right = temp.left ;
		temp.left = root ;
		return temp ;
	}
}
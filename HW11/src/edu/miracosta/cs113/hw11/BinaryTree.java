package edu.miracosta.cs113.hw11;

import java.io.Serializable ;
import java.util.Scanner ;

/**
 * BinaryTree is a generic class that can be used to organize
 * data for quick access.
 * 
 * @author Angel Tapia <angelTapia07084759@gmail.com>
 * 		   reference: Koffman and Wolfgang pg.308
 * @version 1.0
 * @param <E>  Generic type parameter
 */
@SuppressWarnings("serial")
public class BinaryTree<E> implements Serializable
{
	/** root represents the root of the tree */
	protected Node<E> root = null ;

	/**
	 * Inner class Node stores data and links of BinaryTree
	 * @param <E>  Generic type parameter
	 */
	protected static class Node<E> implements Serializable
	{
		/** data represent tree, or subtree, or leaf. */
		protected E data ;
		/** left represents the left branch of the tree. */
		protected Node<E> left ;
		/** right represents the right branch of the tree. */
		protected Node<E> right ;

		/**
		 * Receives the data and initializes left and right to null.
		 * 
		 * @param data: the data to be stored in the node.
		 */
		public Node(E data)
		{
			this.data = data ;
			this.left = null ;
			this.right = null ;
		}
		
		/**
		 * toString access the data in the node.
		 * 
		 * @return the data stored in the node.
		 */
		@Override
		public String toString()
		{
			return this.data.toString() ;
		}
	}
	
	/**
	 * Constructs an empty binary tree.
	 */
	public BinaryTree()
	{
		this.root = null ;
	}	
	
	/**
	 * Constructs a binary tree with the given node as the root.
	 * 
	 * @param root: the given node that represents a root of a tree.
	 */
	protected BinaryTree(Node<E> root)
	{
		this.root = root ;
	}	
	
	/**
	 * Constructs a binary tree with the given node as the root
	 * and the two given subtree.
	 * 
	 * @param data: the given node that represents a root of a tree.
	 * @param leftTree: the left branch/subtree of this root.
	 * @param rightTree: the right branch/subtree of this root.
	 */
	public BinaryTree(E data, BinaryTree<E> leftTree, BinaryTree<E> rightTree)
	{
		this.root = new Node<E>(data) ;
		
		if (leftTree != null)
		{
			this.root.left = leftTree.root ;
		}
		else
		{
			this.root.left = null ;
		}
		if (rightTree != null)
		{
			this.root.right = rightTree.root ;
		}
		else
		{
			this.root.right = null ;
		}
	}
	
	/**
	 * getLeftSubtree access the left subtree of the current root.
	 * 
	 * @return the left subtree of the current root.
	 */
	public BinaryTree<E> getLeftSubtree()
	{
		if (this.root != null && this.root.left != null)
		{
			return new BinaryTree<E>(this.root.left) ;
		}
		return null ;		
	}
	
	/**
	 * getRightSubtree access the right subtree of the current root.
	 * 
	 * @return the right subtree of the current root.
	 */
	public BinaryTree<E> getRightSubtree()
	{
		if (this.root != null && this.root.right != null)
		{
			return new BinaryTree<E>(this.root.right) ;
		}
		return null ;		
	}
	
	/**
	 * getData access the data in the current node.
	 * 
	 * @return the data in the root.
	 */
	public E getData()
	{
		if (this.root.data == null)
		{
			return null ;
		}
		return this.root.data ;

	}
	
	/**
	 * isLeaf checks if the current node is a leaf.
	 * 
	 * @return true of this tree is a leaf; false otherwise.
	 */
	public boolean isLeaf()
	{
		return (this.root.left == null && this.root.right == null) ;
	}	
		
	/**
	 * preOrderTraverse performs a preorder traversal of the specified subtree
	 * appending the data in a StringBuilder object giving a two-dimensional look.
	 * 
	 * @param node: the current subtree (node) of the tree.
	 * @param depth: the current tree level. 
	 * @param sb: Appends each node data, building a representation of the tree.
	 */
	protected void preOrderTraverse(Node<E> node, int depth, StringBuilder sb)
	{
		for (int i = 1; i < depth; i++)
		{
			sb.append(" | ") ;
		}
		if (node == null)
		{
			sb.append("null\n") ;
		}
		else
		{
			sb.append(node.toString() + "\n") ;
			preOrderTraverse(node.left, depth + 1, sb) ;
			preOrderTraverse(node.right, depth + 1, sb) ;
		}
	}	
	
	/**
	 * preOrderTraverse performs a preorder traversal of the specified subtree.
	 * 
	 * @param node: the current subtree (node) of the tree.
	 * @param depth: the current tree level. 
	 * @param sb: Appends each node data, building a representation of the tree.
	 */
	protected void preOrderTraverse(Node<E> node, StringBuilder sb)
	{
		if (node != null)
		{
			sb.append(String.format("%-6s",node.toString())) ;
			preOrderTraverse(node.left, sb) ;
			preOrderTraverse(node.right, sb) ;
		}
	}
	
	/**
	 * inOrderTraverse performs a in order traversal of the specified subtree.
	 * 
	 * @param node: the current subtree (node) of the tree.
	 * @param depth: the current tree level. 
	 * @param sb: Appends each node data, building a representation of the tree.
	 */
	protected void inOrderTraverse(Node<E> node, StringBuilder sb)
	{		
		if (node != null)
		{
			preOrderTraverse(node.left, sb) ;
			sb.append(String.format("%-6s",node.toString())) ;
			preOrderTraverse(node.right, sb) ;
		}
	}
	
	/**
	 * postOrderTraverse performs a post order traversal of the specified subtree.
	 * 
	 * @param node: the current subtree (node) of the tree.
	 * @param depth: the current tree level. 
	 * @param sb: Appends each node data, building a representation of the tree.
	 */
	protected void postOrderTraverse(Node<E> node, StringBuilder sb)
	{
		if (node != null)
		{		
			preOrderTraverse(node.left, sb) ;
			preOrderTraverse(node.right, sb) ;
			sb.append(String.format("%-6s",node.toString())) ;
		}
	}
	
	/**
	 * readBinaryTree constructs a binary tree by reading its data using the class Scanner.
	 * 
	 * @param scan: The Scanner object to read the tree data.
	 * @return a binary tree that contains the data of this tree.
	 */
	public static BinaryTree<String> readBinaryTree(Scanner scan)
	{
		String data = scan.next() ;
	
		if (data.equals("null"))
		{
			return null ;
		}
		else
		{
			BinaryTree<String> leftTree = readBinaryTree(scan) ;
			BinaryTree<String> rightTree = readBinaryTree(scan) ;
			return new BinaryTree<String>(data, leftTree, rightTree) ;
		}
	}
	
	/**
	 * toString uses the method preOrderTraverse
	 * to build a string that contains all of the data in this tree.
	 * 
	 * @return a string that contain all of the data in this tree.
	 */
	@Override
	public String toString()
	{
		StringBuilder sb = new StringBuilder() ;
		preOrderTraverse(this.root, 1, sb) ;
		return sb.toString() ;
	}
	
	/**
	 * Checks if all the data of this and another object are equals.
	 * 
	 * @return true if all the data of this and another object are equals; false otherwise.
	 */
	@Override
	public boolean equals(Object obj)
	{
		if (obj == null || getClass() != obj.getClass())
		{
			return false ;
		}
		
		@SuppressWarnings("unchecked")
		BinaryTree<E> other = (BinaryTree<E>) obj ;		
		return toString().equals(other.toString()) ;
	}
}
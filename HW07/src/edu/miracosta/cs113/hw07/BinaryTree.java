package edu.miracosta.cs113.hw07;

import java.io.Serializable ;
import java.util.Scanner ;

/**
 * BinaryTree is a generic class that can be used to organize
 * data for quick access.
 * 
 * @author Angel Tapia <angelTapia07084759@gmail.com>
 * @version 1.0
 * @param <T>  Generic type parameter
 */
@SuppressWarnings("serial")
public class BinaryTree<T> implements Serializable
{
	/** root represents the root of the tree */
	protected Node<T> root = null ;

	/**
	 * Inner class Node stores data and links of BinaryTree
	 * @param <T>  Generic type parameter
	 */
	protected static class Node<T> implements Serializable
	{
		/** data represent tree, or subtree, or leaf. */
		protected T data ;
		/** left represents the left branch of the tree. */
		protected Node<T> left ;
		/** right represents the right branch of the tree. */
		protected Node<T> right ;

		/**
		 * Constructor receives the data and initializes left and right to null.
		 * 
		 * @param data: the data to be stored in the node.
		 */
		public Node(T data)
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
	 * Constructor that constructs an empty binary tree.
	 */
	public BinaryTree()
	{
		this.root = null ;
	}
	
	
	/**
	 * Constructor that constructs a binary tree with the given node as the root.
	 * 
	 * @param root: the given node that represents a root of a tree.
	 */
	protected BinaryTree(Node<T> root)
	{
		this.root = root ;
	}
	
	
	/**
	 * Constructor that constructs a binary tree with the given node as the root
	 * and the two given subtree.
	 * 
	 * @param data: the given node that represents a root of a tree.
	 * @param leftTree: the left branch/subtree of this root.
	 * @param rightTree: the right branch/subtree of this root.
	 */
	public BinaryTree(T data, BinaryTree<T> leftTree, BinaryTree<T> rightTree)
	{
		this.root = new Node<T>(data) ;
		
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
	public BinaryTree<T> getLeftSubtree()
	{
		if (this.root != null && this.root.left != null)
		{
			return new BinaryTree<T>(this.root.left) ;
		}
		return null ;		
	}
	
	/**
	 * getRightSubtree access the right subtree of the current root.
	 * 
	 * @return the right subtree of the current root.
	 */
	public BinaryTree<T> getRightSubtree()
	{
		if (this.root != null && this.root.right != null)
		{
			return new BinaryTree<T>(this.root.right) ;
		}
		return null ;		
	}
	
	/**
	 * getData access the data in the current node.
	 * 
	 * @return the data in the root.
	 */
	public T getData()
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
	 * preOrderTraverse performs a preorder traversal of the subtree.
	 * 
	 * @param node: the current subtree (node) of the tree.
	 * @param depth: the current tree level. 
	 * @param sb: Appends each node data, building a representation of the tree.
	 */
	private void preOrderTraverse(Node<T> node, int depth, StringBuilder sb)
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
	 * toString builds a string that contains all of the data in this tree.
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
		BinaryTree<T> other = (BinaryTree<T>) obj ;		
		return toString().equals(other.toString()) ;
	}
}
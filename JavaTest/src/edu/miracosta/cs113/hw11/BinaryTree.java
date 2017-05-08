package edu.miracosta.cs113.hw11;
// BinaryTree.java

import java.io.*;
import java.lang.StringBuilder;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;


/** Class for a binary tree that stores type E objects */
public class BinaryTree<E extends Comparable<E>> implements Serializable {

	private static final long serialVersionUID = 1L;
	
	// listing 6.1 (pages 306-312)
	// Nested Inner class Node<E>
	/** Class to encapsulate a tree node. */
	public static class Node<E> implements Serializable {

		private static final long serialVersionUID = 1L;

		// Data fields
		/** The information stored in this node. */
		protected E data;

		/** Reference to the left child. */
		protected Node<E> left;

		/** Reference to the right child. */
		protected Node<E> right;

		// Constructors
		/** Construct a node with given data and no children.
		 * @param data  The data to store in this node
		 */
		public Node(E data) {
			this.data = data;
			left = null;
			right = null;
		}

		//Methods
		/** Return a string representation of the node.
		 * @return A string representation of the data fields
		 */
		public String toString() {
			return data.toString();
		}
	}

	// Data Field
	/** The root of the binary tree */
	public Node<E> root;
	
	// No-parameter constructor
	// Merely sets the data fields to null
	public BinaryTree() {
		root = null;
	}

	// Protected constructor takes a Node as a parameter
	// used by methods internal to the BinaryTree class and its subclasses
	protected BinaryTree(Node<E> root) {
		this.root = root;
	}

	// Three parameter constructor 
	// Data to be referenced by the root node and two BinaryTrees 
	// that will become its left and right subtrees
	public BinaryTree(E data, BinaryTree<E> leftTree, 
			BinaryTree<E> rightTree) {
		root = new Node<E> (data);
		if (leftTree != null) {
			root.left = leftTree.root;
		} else {
			root.left = null;
		}
		if (rightTree != null) {
			root.right = rightTree.root;
		} else {
			root.right = null;
		}
	}

	/** Create a binary tree from an array of objects */
	public BinaryTree(E[] objects) {
	for (int i = 0; i < objects.length; i++)
		add(objects[i]);
	}

	/** add element e into the binary tree
	 * Return true if the element is inserted successfully */
	public boolean add(E e) {
		if (root == null)
			root = new Node<E>(e); // Create a new root
		else {
			// Locate the parent node
			Node<E> parent = null;
			Node<E> current = root;
			while (current != null)
				if (e.compareTo(current.data) < 0) {
					parent = current;
					current = current.left;
				}
				else if (e.compareTo(current.data) > 0) {
					parent = current;
					current = current.right;
				}
				else {
					System.out.println("  Item \"" + e + "\" is already in the tree (item was not added)");
					return false; // Duplicate node not inserted
				}
					
					

			// Create the new node and attach it to the parent node
			if (e.compareTo(parent.data) < 0)
				parent.left = new Node<E>(e);
			else
				parent.right = new Node<E>(e);
		}

		return true; // Element inserted
	}

	/** Return the left subtree.
	 * @return The left subtree or null if either the root 
	 *         or the left subtree is null
	 */
	public BinaryTree<E> getLeftSubtree() {
		if (root != null && root.left != null) {
			return new BinaryTree<E>(root.left);
		} else {
			return new BinaryTree<E>();
		}
	}
	
	/** Return the right subtree.
	 * @return The right subtree or null if either the root 
	 *         or the right subtree is null
	 */
	public BinaryTree<E> getRightSubtree() {
		if (root != null && root.right != null) {
			return new BinaryTree<E>(root.right);
		} else {
			return new BinaryTree<E>();
		}
	}
	
	/** Determine whether this tree is a leaf.
	 * @return true if the root has no children
	 */
	public boolean isLeaf() {
		return root == null || (root.left == null && root.right == null);
	}
	
	/** General output to console toString method
	 * 
	 */
	public String toString() {
		StringBuilder sb = new StringBuilder();
		preOrderTraverse(root, 1, sb);
		return sb.toString();
	}
	
	/** Perform a preorder traversal
	 * @param node  The local root
	 * @param depth The depth
	 * @param sb    The string buffer to save output
	 */
	private void preOrderTraverse(Node<E> node, int depth, StringBuilder sb) {
		
		for (int i=1; i < depth; i++) {
			sb.append("  ");
		}
		
		if (node == null) {
			sb.append("null\n");
		} else {
			sb.append(node.toString());
			sb.append("\n");
			preOrderTraverse(node.left, depth + 1, sb);
			preOrderTraverse(node.right, depth + 1, sb);
		}
	}
	
	// listing 6.2 (page 313)
	/** Method to read a Binary tree.
	 * pre: The input consists of a preorder traversal
	 *      of a binary tree. The line "null" indicates a null tree.
	 *      
	 */
	public static BinaryTree<String> readBinaryTree(Scanner scan) {
		// Read a line and trim leading and trailing spaces.
		String data = scan.next();
		if (data.equals("null")) {
			return null;
		} else {
			BinaryTree<String> leftTree = readBinaryTree(scan);
			BinaryTree<String> rightTree = readBinaryTree(scan);
			return new BinaryTree<String>(data, leftTree, rightTree);
		}
	}
	
	// listing of ObjectOutputStream (page 313)
	public void BinaryTreeOutputToFile(String file, BinaryTree<String> obj) {
		try {
			ObjectOutputStream out =
					new ObjectOutputStream(new FileOutputStream(file));
			out.writeObject(obj);
		} catch (Exception ex) {
			ex.printStackTrace();
			System.exit(1);
		}	
	}
	
	// listing of ObjectInputStream (page 313)
	@SuppressWarnings("unchecked")
	public BinaryTree<E> BinaryTreeInputToFile(String file, BinaryTree<E> obj) {
		try {
			ObjectInputStream in =
					new ObjectInputStream(new FileInputStream(file));
			obj = (BinaryTree<E>) in.readObject();
		} catch (Exception ex) {
			ex.printStackTrace();
			System.exit(1);
		}
		return obj;	
	}
	
	/*  Reference Algorithms for traversals of Binary Tree (page 304)
	 *  there are 3 types of traversals for Binary Trees (Preorder, Inorder, and Postorder)
	 *  Reference website: http://www.dreamincode.net/forums/topic/257154-adding-new-methods-in-binarytree/
	 *   
	 *   Algorithm for						Algorithm for					Algorithm for
	 *   Preorder traversal					Inorder traversal				Postorder traversal
	 *   ------------------------			------------------------		------------------------
	 *   1. if the tree is empty			1. if the tree is empty			1. if the tree is empty
	 *   2.   Return.						2.   Return.					2.   Return.
	 *     else								  else							  else
	 *   3. Visit the root.					3. Inorder traverse				3. Postorder traverse
	 *   4. Preorder traverse				   the left subtree.			   the left subtree.
	 *      the left subtree.				4. Visit the root.				4. Postorder traverse
	 *   5. Preorder traverse				5. Inorder traverse				   the right subtree.
	 *      the right subtree.				   the right subtree.			5. Visit the root.	
	 *      
	 */
	
	/** Inorder traversal from the root*/
	public void inorder() {
		System.out.print(" Inorder Traversal: ");
		inorder(root);
	}

	/** Inorder traversal from a subtree */
	protected void inorder(Node<E> root) {
		if (root == null) return;
		inorder(root.left);
		System.out.print(root.data + " ");
		inorder(root.right);
	}

	/** Postorder traversal from the root */
	public void postorder() {
		System.out.print(" Postorder Traversal: ");
		postorder(root);
	}

	/** Postorder traversal from a subtree */
	protected void postorder(Node<E> root) {
		if (root == null) return;
		postorder(root.left);
		postorder(root.right);
		System.out.print(root.data + " ");
	}

	/** Preorder traversal from the root */
	public void preorder() {
		System.out.print(" Preorder Traversal: ");
		preorder(root);
	}

	/** Preorder traversal from a subtree */
	protected void preorder(Node<E> root) {
		if (root == null) return;
		System.out.print(root.data + " ");
		preorder(root.left);
		preorder(root.right);
	}
	
	/** Algorithm for Breadth-First Traversal of a Binary Tree
	 *    1. Insert the root node in the queue.
	 *    2. while the queue is not empty
	 *    3.    Remove a node from the queue and visit it.
	 *    4.    Place references to its left and right subtrees in the queue.
	 *    
	 * @param btree Binary tree on which to perform the breadth first traversal
	 */
	@SuppressWarnings("hiding")
	public <E extends Comparable <E>> void breadthFirst(BinaryTree<E> btree) {

		Queue<BinaryTree<E>> nodesQueue = new LinkedList<BinaryTree<E>>();
		BinaryTree<E> branch;

		// 
		System.out.print(" Breath-First Traversal: ");

		// 1. Insert the root node in the queue
		nodesQueue.add(btree); 

		// 2. While the queue is not empty
		while (!nodesQueue.isEmpty()) {
			// 3. Remove a node from the queue
			branch = nodesQueue.poll();

			// visitNode(node);
			if (branch.root != null) {
				System.out.print(branch.root + " "); 
				if (branch.getLeftSubtree() != null) 
					nodesQueue.add(branch.getLeftSubtree()); 
				if (branch.getRightSubtree() != null) 
					nodesQueue.add(branch.getRightSubtree()); 
			}
		}
	}

	// Reference website:
	// http://www.experts-exchange.com/Programming/Languages/Java/Q_22953171.html
	// http://stackoverflow.com/questions/5372512/java-binary-search-tree-recursive-copy-tree
	/** Clone method provides a deep copy of root */
	public BinarySearchTree<E> clone() {
		BinarySearchTree<E> newTree = new BinarySearchTree<E>();
		newTree.root = clone(root);
		return newTree;
	}
	
	/** Clone method provides a deep copy of Binary Tree recursively
	 * @param current binary tree branch to clone
	 * @return cloned element b
	 */
	private Node<E> clone(Node <E> current) {
		Node <E> b = new Node <E>(null);
		if(current!=null){
			b.data = current.data;
			if(current.left!=null)b.left   = clone(current.left);
			if(current.right!=null)b.right = clone(current.right);

		}
		return b;
	}
	


	/** Size method
	 * @param node A binary tree node consisting of root node both
	 *             the right and left nodes
	 * @return A integer value set to the number of nodes in a node
	 */
	public int size(Node<E> node) {
	    if (node == null) {
	        return 0;
	    } else {
	        return 1 + (size(node.left) + size(node.right));
	    }
	}
	
	/** Height method
	 * @param node
	 * @return A integer value set to the height to the binary tree
	 */
	public int height(Node<E> node) {
	    if (node == null) {
	        return 0;
	    } else {
	        return 1 +
	        Math.max(height(node.left),
	        		height(node.right));
	    }
	}

}

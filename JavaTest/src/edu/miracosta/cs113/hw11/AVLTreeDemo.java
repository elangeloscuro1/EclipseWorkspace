package edu.miracosta.cs113.hw11;
// AVLTreeDemo.java

/**----------------------------------------------------------------------------
 * Chapter 9 Self-Balancing Search Trees, Page 534, Programming Project #1
 * 
 * File Name:	AVLTreeDemo.java
 * 
 * @author:		Carlos R. Avila
 * 				1382 Andorra Court
 * 				Vista, CA 92081
 * 				(760) 888-7083
 * 
 * @Date:		May 20, 2012
 *
 *-----------------------------------------------------------------------------
 * Description: 
 * 
 * 1. Complete the AVL Tree class by coding the missing methods for insertion
 *    only. Use it to insert a collection of randomly generated numbers. insert
 *    same numbers in a binary search tree that is not balanced. Verify that
 *    each tree is correct by performing and inorder traversal. Also display
 *    format of each tree that was built and compare their heights.
 * 
 *-----------------------------------------------------------------------------
 * References: 
 *   Algorithms for AVL Tree (pages 477-489)
 *      
 *   website: 
 *   http://en.wikipedia.org/wiki/AVL_tree
 *  
 *-----------------------------------------------------------------------------
 * Overall Plan (step-by-step, how you want the code to make it happen):
 * 
 * 	1. Develop AVLTree class from the text book which extends 
 *     BinarySearchTreeWithRotate tree class.
 *  2. For the AVLTree class develop the missing missing methods
 *     - rotateLeft
 *     - recursive add method
 *     - rebalance left method
 *     - rebalance right method
 *     - decrementBalance method
 *     - incrementBalance method
 *  3. Develop the demo program to generate and array of integers for insertion
 *     into an AVL Tree and a Binary Tree.
 *     Try some full tree of size 0, 1, 3, 7, 15, 31 for example.
 *  4. Develop methods for displaying the results and comparisons 
 *  5. Test and debug.   
 *-----------------------------------------------------------------------------
 * Classes needed and Purpose: 
 * 
 * 	// main program class
 *	public class AVLTreeDemo			
 *		
 *		// program main method				
 *		public static void main(String[] args)	// arguments are not required to run
 *
 *		// displayAVLTree method to display the AVL Tree
 *		private static void displayAVLTree()
 *
 *		// displayBinaryTree method to display the Binary Tree
 *		private static void displayBinaryTree()
 *
 *
 *  public class AVLTree<E extends Comparable<E>> 
 *      extends BinarySearchTreeWithRotate <E>
 *      
 *      // Nested class AVLNode<E>
 *      private static class AVLNode<E> extends Node<E>
 *      
 *      	// Constant to indicate left-heavy
 *      	public static final int LEFT_HEAVY = -1;
 *      
 *      	// Constant to indicate balanced
 *      	public static final int BALANCED = 0;
 *      
 *      	// Constant to indicate right-heavy
 *      	public static final int RIGHT_HEAVY = 1;
 *      
 *      	// balance is right subtree height - left subtree height
 *      	private int balance;
 *      
 *      	// Methods
 *      	// Construct a node with the given item as the data field
 *      	public AVLNode(E item)
 *      	
 *      	// Return a string representation of the node.
 *      	public String toString()
 *      
 *      // Data Fields
 *      
 *      // Flag to indicate that height of the tree has increase
 *      private boolean increase;
 *      
 *      // Methods
 *      // Algorithm for Insertion into an AVL Tree 
 *      public boolean add(E item) 
 *      
 *      // Recursive add method. Inserts the given object into the tree
 *      private AVLNode<E> add(AVLNode<E> localRoot, E item) 
 *      
 *      // rebalanceLeft 
 *      private AVLNode<E> rebalanceLeft(AVLNode<E> localRoot)
 *      
 *      // rebalanceRight
 *      private AVLNode<E> rebalanceRight(AVLNode<E> localRoot)
 *      
 *      // decrementBalance
 *      private void decrementBalance(AVLNode<E> node) 
 *      
 *      // incrementBalance
 *      private void incrementBalance(AVLNode<E> node)
 *      

 *  // Binary Tree class
 *  public class BinaryTree<E extends Comparable<E>> implements Serializable 
 *    
 *    //inner class defines a node
 *    protected static class Node<E> implements Serializable // inner class
 *    
 *    public BinaryTree() 
 *	  public BinaryTree(E data, BinaryTree<E> leftTree, BinaryTree<E> rightTree)
 *	  public BinaryTree(E[] objects)
 *	  protected BinaryTree(Node<E> root)
 *	  public boolean add(E e)
 *	  public BinaryTree<E> BinaryTreeInputToFile(String file, BinaryTree<E> obj)
 *	  public BinaryTree<E> BinaryTreeInputToFile(String file, BinaryTree<E> obj)
 *	  public BinarySearchTree<E> clone()
 *    private Node<E> clone(Node <E> current)
 *	  public BinaryTree<E> getLeftSubtree()
 *	  public BinaryTree<E> getRightSubtree() 
 *	  public int height(Node<E> node)  // Height method
 *	  public void inorder()            // Inorder traversal from the root
 *	  protected void inorder(Node<E> root) // Inorder traversal from a subtree
 *	  public boolean isLeaf()
 *	  public void postorder() // Postorder traversal from the root
 *	  protected void postorder(Node<E> root)
 *	  public void preorder()  // Preorder traversal from the root
 *	  protected void preorder(Node<E> root) // Preorder traversal from a subtree
 *	  private void preOrderTraverse(Node<E> node, int depth, StringBuilder sb)
 *	  public int size(Node<E> node)
 *	  public String toString()
 *  
 *   
 *  // BinarySearchTree class
 *  public class BinarySearchTree<E extends Comparable<E>>
                                 extends BinaryTree<E>
                                 implements SearchTree<E>
 *    	public boolean add(E item)
 *    	public Node<E> add(Node<E> localRoot, E item) 
 *    	public boolean contains(E target) 
 *    	public E delete(E target)
 *    	private Node<E> delete(Node<E> localRoot, E item) 
 *    	public E find(E target) 
 *    	private E find(Node<E> localRoot, E target) 
 *    	private E findLargestChild(Node<E> parent) 
 *    	public boolean remove(E target)
 *    
 *    
 *  // BinarySearchTreeWithRotate
 *  public class BinarySearchTreeWithRotate<E extends Comparable<E>>
 *  		                               extends BinarySearchTree<E>
 *  	protected Node<E> rotateRight (Node<E> root)
 *  	protected Node<E> rotateLeft (Node<E> root)
 *  
 *  
 *  // SearchTree Interface
 *  interface SearchTree<E>
 *  	// interface methods
 *  	boolean add(E item);
 *  	boolean contains(E target);
 *  	E find(E target);
 *  	E delete(E target);
 *  	boolean remove(E target);
 *  
 *-----------------------------------------------------------------------------
 */

import java.util.Random;


/**
 * 
 * @author Carlos R. Avila
 *
 */
public class AVLTreeDemo {
	
	public static AVLTree<Integer> integerAVLTree = new AVLTree<Integer>();
	public static BinaryTree<Integer> integerBinaryTree = new BinaryTree<Integer>();

	
	public static void main(String[] args) { // arguments are not required to run
		
		
		integerAVLTree.add(15) ;
		integerAVLTree.add(10) ;
		integerAVLTree.add(25) ;
		integerAVLTree.add(22) ;
		integerAVLTree.add(18) ;
		integerAVLTree.add(19) ;// 17 
		displayAVLTree() ;
		
//		int intNumbers = 15; // total number of integers to populate the trees
//		int rndInteger[] = new int[intNumbers]; // array to store random integers
//		int minValue = 1;    // lowest random integer
//		int maxValue = 1000;  // largest random integer
//				
//		// generate and display the array of random integer values
//		Random randomGenerator = new Random();
//		
//		System.out.print("List of \"" + intNumbers 
//				+ "\" Randomly Generated Integers:\n{");
//		
//		for (int i=0; i<intNumbers; i++) {
//			rndInteger[i] = minValue + randomGenerator.nextInt(maxValue-minValue+1);
//			System.out.print(rndInteger[i]);
//			if (i<intNumbers-1) {
//				System.out.print(", ");
//			}
//		}
//		System.out.println("}");
//
//		// AVL Tree
//		//populate the AVL Tree
//		System.out.println("\n\nPopulating the AVL Tree (Balanced Tree):");
//		for (int i=0; i<intNumbers; i++)  {
//			integerAVLTree.add(rndInteger[i]);
//		}
//
//		// display the AVL Tree
//		System.out.println("\n\nAVL Tree (Balanced Tree):");
//		displayAVLTree();
//
//		// Binary Tree
//		// populate the Binary Tree
//		System.out.println("\n\nPopulating the Binary Tree (Non-balanced Tree):");
//		for (int i=0; i<intNumbers; i++)  {
//			integerBinaryTree.add(rndInteger[i]);
//		}
//
//		// display the Binary Tree
//		System.out.println("\n\nBinary Tree (Non-Balanced Tree):");
//		displayBinaryTree();
//		
//		// display the Tree Comparison
//		System.out.println("\n\n\nTree Comparison");
//		System.out.println("---------------");
//
//		System.out.println("AVL Tree");
//		integerAVLTree.inorder();
//		System.out.println();
//		System.out.println(" Tree height: " 
//				+ integerAVLTree.height(integerAVLTree.root));
//		System.out.println(" Tree size: " 
//				+ integerAVLTree.size(integerAVLTree.root));
//
//		System.out.println("\n\nBinary Tree");
//		integerBinaryTree.inorder();
//		System.out.println();
//		System.out.println(" Tree height: " 
//				+ integerBinaryTree.height(integerBinaryTree.root));
//		System.out.println(" Tree size: " 
//				+ integerBinaryTree.size(integerBinaryTree.root));
//
//		// End of Program
//		System.out.println("\n\nProgram terminated properly without error.");
//		System.exit(0);
	}
	

	// displayAVLTree method to display the AVL Tree
	private static void displayAVLTree() {

		if (integerAVLTree.root == null) {
			System.out.println("\nAVL Tree contains no values (tree is empty)");
		} else {
			System.out.println("\nRoot = \"" + integerAVLTree.root + "\"\n\n"
					+ integerAVLTree);
			System.out.println("AVL Tree Traversal");
			integerAVLTree.inorder();
			System.out.println();
			System.out.println("\n Tree height: " 
					+ integerAVLTree.height(integerAVLTree.root));
			System.out.println(" Tree size: " 
					+ integerAVLTree.size(integerAVLTree.root));

		}
	}

	// displayBinaryTree method to display the Binary Tree
	private static void displayBinaryTree() {

		if (integerBinaryTree.root == null) {
			System.out.println("\nBinary Tree contains no values (tree is empty)");
		} else {
			System.out.println("\nRoot = \"" + integerBinaryTree.root + "\"\n\n"
					+ integerBinaryTree);
			System.out.println("Binary Tree Traversal");
			integerBinaryTree.inorder();
			System.out.println();
			System.out.println("\n Tree height: " 
					+ integerBinaryTree.height(integerBinaryTree.root));
			System.out.println(" Tree size: " 
					+ integerBinaryTree.size(integerBinaryTree.root));
		}
	}

}

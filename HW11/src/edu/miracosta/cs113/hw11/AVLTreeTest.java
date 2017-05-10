package edu.miracosta.cs113.hw11;

/**
 * Algorithm:
 * 	1) Use the class BinaryTree implemented in Homework07.
 * 		-Add a method preOrderTraverse that performs a preorder 
 *		 traversal to build a string.
 *		-Add a method preOrderTraverse that performs a preorder 
 *		 traversal to build a string representation of the values in the tree.
 *		-Add a method inOrderTraverse that performs a inorder 
 *		 traversal to build a string representation of the values in the tree.
 *		-Add a method postOrderTraverse that performs a postorder 
 *		 traversal to build a string representation of the values in the tree.
 *	2) Implement the SearchTree interface according to the book on page 317 ;
 *		-Add abstract methods:
 *			boolean add(E item) ;
 *			boolean contains(E target) ;
 *			E find(E target) ;
 *			E delete(E target) ;
 *			remove(E target) ;
 *	3) Implement the class BinarySearchTree that extends BinaryTree and implements 
 * 		the add method of the SearchTree interface.
 * 		-Create an instance variable totalItems that keeps track of the 
 * 		 total items in the tree.
 * 		-Implement all of the method of SearchTree as stubs methods 
 * 		 except for the method add.
 * 		-Create an Accessor for totalItems.
 * 		-Implement the method add according to the book on page 321.
 * 		-Create a method height that compares the height of the left and 
 * 		 the right subtrees to find the height of the tree.
 * 		-Create a method preOrderTraverse that returns
 * 		 a string representation of the values in the tree in preorder form.
 * 		-Create a method inOrderTraverse that returns
 * 		 a string representation of the values in the tree in inorder form.
 * 		-Create a method postOrderTraverse that returns 
 * 		 a string representation of the values in the tree in postorder form.
 * 	4) Implement the class BinarySearchTreeWithRotate that extends BinarySearchTree
 * 		-Create the rotateRight method as on page 476: Listing 9.1.
 * 		-Create the method rotateLeft method as on page 476: Listing 9.1.
 * 	5) Implement the class AVLTree that extends BinarySearchTreeWithRotate
 * 		-Implement the inner AVLNode as on page 482.
 * 		-Implement the method add as on page 484.
 * 		-Implement the method rebalanceLeft as on page 487.
 * 		-Implement the method rebalanceRight symmetrically
 * 		 with respect to the method rebalanceLeft
 * 		-Implement the method decrementBalance as on page 488.
 * 		-Implement the method incrementBalance.
 * 	6) Create a AVLTree test class.
 * 		main:
 * 		-Create an instance of the AVLTree and an instance of the BinarySearchTree.
 * 		-Generate Random integers to be inserted in both trees.
 * 		-Display the random generated list and the height of each tree.
 * 		-Disply the trees in inorder traverse
 * 		-Display a 2D representation of the trees.
 * 
 */
import java.util.ArrayList ;
/**
 * AVLTreeTest Compares the AVLTree and an unbalanced tree.
 * 
 * @author Angel Tapia <angelTapia07084759@gmail.com>
 * @version 1.0
 */
public class AVLTreeTest
{
	/**
	 * Compares a balanced and unbalanced tree.
	 * 
	 * @param args Command Lines
	 */
	public static void main(String[] args)
	{
		//	A balanced and an unbalanced tree
		AVLTree<Integer> avlTree = new AVLTree<Integer>();
		BinarySearchTree<Integer> bTree = new BinarySearchTree<Integer>();
		
		//	Generate Random integers to be inserted in both trees.
		int totalItems = 15 ;
		ArrayList<Integer> intList = new ArrayList<Integer>(totalItems) ;
		
		for (int i = 0; i < totalItems ; i++)
		{
			int randomInt = (int) (Math.random() * totalItems * 2) ;
			while (intList.contains(randomInt))
			{
				randomInt = (int) (Math.random() * totalItems * 2 ) ;
			}
			intList.add(randomInt) ;
			avlTree.add(randomInt) ;
			bTree.add(randomInt) ;
		}
		
		//	Display the random generated list and the height of each tree.
		System.out.println("List generated randomly (" + totalItems + " items):") ;
		for (Integer integer : intList)
		{
			System.out.print(String.format("%-3s", integer)) ;
		}
		System.out.println("\n\nHeight of Balanced Tree: " + avlTree.height(avlTree.root)) ;
		System.out.println("Height of Unbalanced Tree: " + bTree.height(bTree.root)) ;
		
		//	Disply the trees in inorder traverse
		System.out.println("\nBalanced Tree Inorder traverse:") ;
		System.out.println(avlTree.inOrderTraverse()) ;
		
		System.out.println("\nUnbalanced Tree Inorder traverse:") ;
		System.out.println(bTree.inOrderTraverse()) ;
		
		//	Display a 2D representation of the trees.		
		System.out.println("\nRepresentation of the Balanced Tree in 2D:\n" + avlTree) ;			
		System.out.println("\nRepresentation of the Unbalanced Tree in 2D:\n"  + bTree) ;		
	}
}
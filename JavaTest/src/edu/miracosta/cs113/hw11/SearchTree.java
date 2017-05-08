package edu.miracosta.cs113.hw11;
// SearchTree.java

interface SearchTree<E> {

	// interface methods
	boolean add(E item);
	boolean contains(E target);
	E find(E target);
	E delete(E target);
	boolean remove(E target);
	
}

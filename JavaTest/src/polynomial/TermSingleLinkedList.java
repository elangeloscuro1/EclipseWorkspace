package polynomial;


public class TermSingleLinkedList
{
	/***********************************
	 * Inner Generic Class: Node.
	 ***********************************/
	private static class Node
	{
		private Node link ;
		private Term data ;
		
		public Node(Term data, Node link)
		{
			this.data = data ;
			this.link = link ;
		}
	}// End of inner class	
	
	/** head represents the beginning of the list */
	private Node head ;
	/** size counts the number of terms in the list */
	private int size ;
	
	/**
	 * Default constructor
	 */
	public TermSingleLinkedList()
	{
		size = 0 ;
		head = null ;
	}
	
	/**
	 * Adds a Term to the list sorted by highest exponent.
	 * 
	 * @param term represents the element to be added.
	 */
	public void addSorted(Term term)
	{		
		if (head == null || term.compareTo(head.data) > 0)
		{
			head = new Node(term, head) ;
		}
		else
		{
			Node newNode = head ;
			while (newNode.link != null && term.compareTo(newNode.link.data) <= 0)
			{
				newNode = newNode.link ;
			}
			newNode.link = new Node(term, newNode.link) ;
		}
		size++ ;
	}
	
	/**
	 * Adds a list of elements to the end of the list.
	 * 
	 * @param term represents the element to be added.
	 */
	public void addList(TermSingleLinkedList list)
	{
		Node newNode = list.head ;
		while (newNode != null)
		{
			addSorted(newNode.data) ;
			newNode = newNode.link ;
		}
	}
	
	/** 
	 * @return the number of element in the list.
	 */
	public int getSize()
	{
		return size ;
	}
	/**
	 * 
	 * @return an element from a specific index
	 * 		   or null if index is greater than the list size.
	 */
	public Term get(int index)
	{
		Node temp = head ;
		for (int i = 0 ; temp != null && i < index ; i++)
		{
			temp = temp.link ;
		}
		return temp == null ? null : temp.data ;
	}
	
	/**
	 * Removes the first element in the list.
	 * 
	 * @return return the elements that is removed or null if list is empty.
	 */
	public Term removeFirst()
	{
		if (head == null)
		{
			return null ;
		}
		Term temp = head.data ;
		head = head.link ;
		size-- ;
		return temp ;
	}
	
	/**
	 * display every term in the list as an algebraic expression.
	 */
	public void displayList()
	{
		Node newNode = head ;
		while (newNode != null)
		{			
			System.out.print(newNode.data.getTerm()) ;
			newNode = newNode.link ;
			System.out.print(newNode != null ? " + " : "\n") ;
		}
	}
	
	/**
	 * @return list size information
	 */
	@Override
	public String toString()
	{
		return "Total elements in list: " + size ;
	}
	
	/**
	 * @return true if both object have the same reference
	 */
	@Override
	public boolean equals(Object object)
	{
		return getClass() == object.getClass() && this ==  object;
	}
}

package studentRegistry ;

public class StudentSingleLinkedList
{
	/***********************************
	 * Inner Class: Node.
	 ***********************************/
	private static class Node
	{
		private Node link ;
		private Student data ;

		public Node(Student data, Node link)
		{
			this.data = data ;
			this.link = link ;
		}
	}// End of inner class

	/** head represents the beginning of the list */
	private Node head ;
	/** size counts the number of student in the list */
	private int size ;

	/**
	 * Default constructor
	 */
	public StudentSingleLinkedList()
	{
		size = 0 ;
		head = null ;
	}

	/**
	 * Adds a student to the list.
	 * 
	 * @param name of the student to be added.
	 */
	public void addToStart(String name)
	{
		
		head = new Node(new Student(name, 1), head) ;
		Node temp = head ;
		
		for (int i = 1 ; temp != null ; i++)
		{
			temp.data.setNumberInLine(i) ;
			temp = temp.link ;
		}
		size++ ;
		
	}

	/**
	 * Adds a Student to the end of the list.
	 * 
	 * @param term represents the element to be added.
	 */
	public void addToEnd(String name)
	{
		if (head == null)
		{
			addToStart(name) ;
		}
		else
		{
			Node newNode = head ;
			while (newNode.link != null)
			{
				newNode = newNode.link ;
			}
			newNode.link = new Node(new Student(name, size + 1), newNode.link) ;
		}
		size++ ;
	}

	/**
	 * Removes the first element in the list.
	 * 
	 * @return return the elements that is removed or null if list is empty.
	 */
	public Student removeFirst()
	{
		if (head == null)
		{
			return null ;
		}
		Student temp = head.data ;
		size-- ;
		Node tempHead = head = head.link ;
		for (int i = 0; tempHead != null; i++)
		{
			tempHead.data.setNumberInLine(i + 1) ;
			tempHead = tempHead.link ;
		}
		return temp ;
	}

	/**
	 * searches for the name of the student to be removed.
	 * 
	 * @return the element that has been removed or null if list is empty.
	 */
	public Student remove(String name)
	{
		Node temp = head ;
		Student student = null ;
		int index = 0 ;
		
		if (head == null)
		{
			return null ;
		}
		if (head != null && temp.data.getName().equals(name))
		{
			return removeFirst() ;
		}
		
		for (index = 2 ; temp.link != null ; index++)
		{
			if (temp.link.data.getName().equals(name))
			{
				student = temp.link.data ;
				temp.link = temp.link.link ;
				index-- ;				
			}
			else 
			{				
				temp = temp.link ;
			}
			temp.data.setNumberInLine(index);			
		}
		return student ;
	}

	/**
	 * @return the number of element in the list.
	 */
	public int getSize()
	{
		return size ;
	}

	/**
	 * display every term in the list as an algebraic expression.
	 */
	public void displayList()
	{
		Node newNode = head ;
		while (newNode != null)
		{
			System.out.println(newNode.data) ;
			newNode = newNode.link ;
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
		return getClass() == object.getClass() && this == object ;
	}

}

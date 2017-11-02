package single_list ;

public class SingleLinkedList<E>
{
	

//	public int test()
//	{
//		Node<Integer> nodeRef = (Node<Integer>) head ;
//		int sum = 0 ;
//		while (nodeRef != null && sum < 100)
//		{
//			int next =  (int) nodeRef.data ;
//			sum += next ;
//			nodeRef = nodeRef.next ;
//			System.out.println("SUM: " + sum) ;
//		}
//		return sum ;
//	}
	
	
	public void main1()
	{
		Node<String> nodeRef = (Node<String>) head ;
		while (nodeRef.next != null)
		{
			System.out.println((nodeRef = nodeRef.next).data) ;
		}
		System.out.println((nodeRef.next = new Node<>("Tamika")).data) ;
	}
	

	public void main2()
	{int i = 0 ;
		
		Node<String> nodeRef = (Node<String>) head ;
		while (nodeRef != null && !nodeRef.data.equals("Harry"))
		{
			System.out.println(i++ + " ==> " + (nodeRef = nodeRef.next).data) ;
		}
		if (nodeRef != null)
		{
			nodeRef.data = "Sally";
			nodeRef.next = new Node<>("Harry", nodeRef.next.next);
		}

		//System.out.println(i++ + " ==> " + (nodeRef.data)) ;
	}
	

	
	
	
	//---------------------------------------
	private static class Node<E>
	{
		private E data ;
		private Node<E> next ;

		/**
		 * Creates a new node with a null next field
		 * 
		 * @param data
		 *            The data stored
		 */
		private Node(E data)
		{
			this.data = data ;
			this.next = null ;
		}

		/**
		 * Creates a new node that references another node
		 * 
		 * @param data
		 *            The data stored
		 * @param nodeRef
		 *            The node referenced by new node
		 */
		private Node(E data, Node<E> nodeRef)
		{
			this.data = data ;
			this.next = nodeRef ;
		}
	}//---------------------------------------
	
	
	
	
	private Node<E> head = null ;
	private int size = 0 ;

	private void addFirst(E item)
	{
		head = new Node<E>(item, head) ;
		size++ ;
	}

	private void addAfter(Node<E> node, E item)
	{
		node.next = new Node<E>(item, node.next) ;
		size++ ;
	}

	private E removeAfter(Node<E> node)
	{
		Node<E> temp = node.next ;
		if (temp != null)
		{
			node.next = temp.next ;
			size-- ;
			return temp.data ;
		}
		else
		{
			return null ;
		}
	}

	private E removeFirst()
	{
		if (head == null)
		{
			return null ;
		}
		else
		{
			Node<E> temp = head ;
			head = head.next ;
			size-- ;
			return temp.data ;
		}
	}

	@Override
	public String toString()
	{
		Node<E> nodeRef = head ;
		String result = "" ;
		while (nodeRef != null)
		{
			result += nodeRef.data ;
			if (nodeRef.next != null)
			{
				result += " ==> " ;
			}
			nodeRef = nodeRef.next ;
		}
		return result ;
	}

	private Node<E> getNode(int index)
	{
		Node<E> node = head ;
		int i = 0 ;
		while (i < index && node != null)
		{
			node = node.next ;
			i++ ;
		}
		return node ;
	}

	public E get(int index)
	{
		if (index < 0 || index >= size)
		{
			throw new IndexOutOfBoundsException(Integer.toString(index)) ;
		}
		Node<E> node = getNode(index) ;
		return node.data ;
	}

	public E set(int index, E anEntry)
	{
		if (index < 0 || index >= size)
		{
			throw new IndexOutOfBoundsException(Integer.toString(index)) ;
		}
		Node<E> node = getNode(index) ;
		E result = node.data ;
		node.data = anEntry ;
		return result ;
	}

	public boolean add(E item)
	{
		add(size, item) ;
		return true ;
	}

	public void add(int index, E item)
	{
		if (index < 0 || index > size)
		{
			throw new IndexOutOfBoundsException(Integer.toString(index)) ;
		}
		if (index == 0)
		{
			addFirst(item) ;
		}
		else
		{
			Node<E> node = getNode(index - 1) ;
			addAfter(node, item) ;
		}
	}

}

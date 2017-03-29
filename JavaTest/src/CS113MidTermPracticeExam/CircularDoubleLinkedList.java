package CS113MidTermPracticeExam;

/*		
Stack<Object> stack = new Stack<Object>() ;     
// E push(E obj);  E pop();  E peek(); boolean empty();                ==> Topic 6 slide 6		
Queue<Object> queue = new ArrayQueue<Object>() ;
// boolean offer(E obj); E poll(); E peek();  E remove(); E element() ; ==> Topic 6 slide 40
*/	
public class CircularDoubleLinkedList<T>
{

	public static void main3(String[] args)
	{
		CircularDoubleLinkedList<String> test = new CircularDoubleLinkedList<String>() ;
		test.add("Abel") ;test.display() ;
		
		System.out.println() ;
		test.addHead("Baron") ;test.display() ;		

		System.out.println() ;
		test.addTail("Charles") ;test.display() ;
		
		System.out.println() ;
		System.out.println(test.remove("Abel") + "\n") ;test.display() ;
		
		System.out.println() ;
		test.addHead("Devin") ;test.display() ;	
		
		System.out.println() ;
		System.out.println(test.removeHead("Abel") + "\n") ;test.display() ;

	}
	/*************************************************************************************
	 * Node
	 *************************************************************************************/
	private class Node<T>
	{
		private T data = null ;
		private Node<T> next = null ;
		private Node<T> prev = null ;		
		
		private Node(T data)
		{
			this.data = data ;
		}
	}
	
	public Node<T> head = null ;
	public Node<T> tail = null ;
	/*************************************************************************************
	 * Add
	 *************************************************************************************/
	public boolean add(T e)
	{
		Node<T> temp = new Node<T>(e) ;
		if (head == null)
		{			
			temp.next = temp ;
			temp.prev = temp ;
			head = temp ;
			tail = temp ;
		}
		return true ;
		
	}
	public boolean addHead(T e)
	{
		Node<T> temp = new Node<T>(e) ;
				
		temp.next = head ;
		temp.prev = tail ;
		head = temp ;	
		tail.next = head ;
			
		return true ;
	}
	public boolean addTail(T e)
	{
		Node<T> temp = new Node<T>(e) ;
		
		temp.next = head ;
		temp.prev = tail ;				
		tail.next = temp ;
		tail = temp ;
		head.prev = tail ;
		
		return true ;
	}
	
	public T remove(T e)
	{
		T data = head.next.data ;
		head.next = head.next.next ;
		return data  ;
	}
	public T removeHead(T e)
	{
		
		T data = head.data ;
		head = head.next ;
		tail.next = head ;
		return data  ;
	}
	
	
	
	/*************************************************************************************
	 * display
	 *************************************************************************************/
	public void display()
	{
		Node<T> temp = head ;
		while(temp.next != head)
		{
			System.out.println(temp.data) ;
			temp = temp.next ;
		}
		System.out.println(temp.data) ;
	}
}

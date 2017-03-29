package mydoublelinkedlist;

import java.util.Iterator ;
import java.util.ListIterator ;

public class Test
{
	public static void main(String[] args)
	{
		MyDoubleLinkedList<String> list = new MyDoubleLinkedList<>() ;
		list.add("A") ;
		list.add("B") ;
		list.add("C") ;
		list.add("D") ;
		list.add("E") ;
		list.add("F") ;
		list.add("C") ;

		System.out.println("List----------------------------------------") ;
		System.out.println(list) ;
		
		
		MyDoubleLinkedList<String> list2 = new MyDoubleLinkedList<>() ;
		list2.add("1") ;
		list2.add("2") ;
		list2.add("3") ;
		list2.add("4") ;
		

		System.out.println("List2----------------------------------------") ;
		System.out.println(list2) ;
		
		
		
		System.out.println("List----------------------------------------") ;
		
		list.addAll(4,list2) ;
		
		System.out.println(list) ;
		System.out.println("conatinsAll: " + list.containsAll(list2)) ;
		
		System.out.println("get: " + list.get(2)) ;
		System.out.println("IndexOf: " + list.indexOf("C")) ;
		System.out.println("IsEmpty: " + list.isEmpty()) ;
		System.out.println("lastIndexOf: " + list.lastIndexOf("C")) ;
		
		System.out.println("Remove B: " + list.remove("B")) ;
		System.out.println("Remove 0: " + list.remove(9)) ;
		
		System.out.println("RemoveAll: " + list.retainAll(list2)) ;
		System.out.println("SET: " + list.set(2,"X")) ;
		
		list.subList(0, 4) ;
		list.toArray() ;
		
		
		MyDoubleLinkedList<String> list3 = new MyDoubleLinkedList<>() ;
		list3.add("1") ;
		list3.add("2") ;
		list3.add("3") ;
		list3.add("4") ;
		
		System.out.println(list2.equals(list3)) ;
		System.out.println(list.equals(list2)) ;
		
		
		
		
		 System.out.println("\nIter2----------------------------------------") ;
		 ListIterator iter  = list.listIterator() ;
		 while (iter.hasNext())
		 {
			 System.out.println(iter.nextIndex() + " <> " + iter.next()) ;		
		 }
		
		
		
		list.clear() ;
		System.out.println("IsEmpty: " + list.isEmpty()) ;
		
		
	}
}

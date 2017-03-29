package studentRegistry ;

public class HW03Driver
{

	public static void main(String[] args)
	{
		StudentSingleLinkedList studentList ;
		studentList = new StudentSingleLinkedList() ;
		
		studentList.addToStart("Abidoye") ;
		studentList.addToStart("Olanduni") ;
		studentList.addToStart("Boado") ;
		studentList.addToStart("Annabelle") ;
		studentList.addToStart("Buttler") ;
		studentList.addToStart("Jamens") ;
		studentList.addToStart("Chee") ;		
		studentList.addToEnd("Yong-Han") ;		
		studentList.addToEnd("Debaggis") ;
		studentList.addToEnd("Tarra") ;					
		
		studentList.displayList() ;
		
		System.out.println("\nRemoving first in line: " + studentList.removeFirst()) ;		
		studentList.displayList() ;
		
		System.out.println("\nRemoving by name: " + studentList.remove("Tarra")) ;		
		studentList.displayList() ;
		
		


	}

}

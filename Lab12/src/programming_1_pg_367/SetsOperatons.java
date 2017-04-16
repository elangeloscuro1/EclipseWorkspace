package programming_1_pg_367;
/**
 * Algorithm:
 * 	-Declare three instances of the set interface: setA, setB, and setC.
 * 	-Union: add setA and setB to setC and display.
 * 	-Intersection: Add setA to setC and retain setB for setC.
 * 	-Difference: Add setA to setC and remove setB from setC.
 * 	-Subset:if setB contains setA setC = setA; else, setC = setB
 */
import java.util.HashSet ;
import java.util.Set ;

/**
 * SetsOperatons test the following operation of the 
 * set interface: union, intersection, difference, and subset.
 * 
 * @author Angel Tapia angelTapia07084759@gmail.com
 * version 1.0
 */
public class SetsOperatons
{
	public static void main(String[] args)
	{
		Set<String> setA = new HashSet<String>() ;
		Set<String> setB = new HashSet<String>() ;
		Set<String> setC = new HashSet<String>() ;		
		
		setA.add("1") ;
		setA.add("3") ;
		setA.add("5") ;
		setA.add("7") ;
		
		setB.add("2") ;
		setB.add("3") ;
		setB.add("4") ;
		setB.add("5") ;
		
		System.out.println("SetA:   " + setA) ;			
		System.out.println("SetB:   " + setB) ;		
		System.out.println("SetC:   " + setC) ;		
		
		setC.addAll(setA) ;
		setC.addAll(setB) ;
		System.out.println("\nSetC = setA <union> setB ...") ;
		System.out.println("SetA:   " + setA) ;			
		System.out.println("SetB:   " + setB) ;		
		System.out.println("SetC:   " + setC) ;	
		
		setC = new HashSet<String>() ;
		setC.addAll(setA) ;
		setC.retainAll(setB) ;
		System.out.println("\nSetC = setA <intersection> SetB ...") ;	
		System.out.println("SetA:   " + setA) ;			
		System.out.println("SetB:   " + setB) ;		
		System.out.println("SetC:   " + setC) ;	
		
		setC = new HashSet<String>() ;
		setC.addAll(setA) ;
		setC.removeAll(setB) ;
		System.out.println("\nSetC = setA <difference> SetB ...") ;	
		System.out.println("SetA:   " + setA) ;			
		System.out.println("SetB:   " + setB) ;		
		System.out.println("SetC:   " + setC) ;	
		
			
		setC = new HashSet<String>() ;
		setC = setB.containsAll(setA) ? setA : setB ;
		System.out.println("\nSetC = setA <subset> SetB : " + setB.containsAll(setA)) ;	
		System.out.println("SetA:   " + setA) ;			
		System.out.println("SetB:   " + setB) ;		
		System.out.println("SetC:   " + setC) ;	
	}
}
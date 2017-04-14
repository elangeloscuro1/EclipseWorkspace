package abstractVSinterface;

import java.util.Comparator ;

public class Test implements Comparator<String>
{

	@Override
	public int compare(String arg0, String arg1)
	{
		// TODO Auto-generated method stub
		return 0 ;
	}

	public static void main(String[] args)
	{
		String t1 = "A" ;
		String t2 = "B" ;
		System.out.println(t2.compareTo(t1)) ;
	}

 
	
}

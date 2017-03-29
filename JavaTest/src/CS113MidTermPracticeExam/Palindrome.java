package CS113MidTermPracticeExam;

public class Palindrome
{

	public static void main(String[] args)
	{
		System.out.println(isPalindrome("a")) ;
		System.out.println(isPalindrome("")) ;
		System.out.println(isPalindrome("aba")) ;
		System.out.println(isPalindrome("abab")) ;
		System.out.println(isPalindrome("sas")) ;
		
		
		System.out.println() ;
		System.out.println() ;
		System.out.println(multiplication(5, 7)) ;
		
		
		System.out.println() ;
		System.out.println() ;
		String[] test = {"AA", "BB", "CC"} ;
		System.out.println(findLast(test, test.length -1, "AA")) ;
		System.out.println(findLast(test, test.length -1, "CC")) ;
		System.out.println(findLast(test, test.length, "CC")) ;
		System.out.println(findLast(test, -1, "CC")) ;
		System.out.println(findLast(test, test.length -1, "aa")) ;

		
		

	}

	
	public static boolean isPalindrome(String s)
	{
		if (s.length() <= 1)
		{
			return true ;
		}
		if (s.charAt(0) == s.charAt(s.length() -1))
		{
			return isPalindrome(s.substring(1, s.length() -1)) ;
		}
		return false ;
	}
	
	public static int multiplication(int i, int j)
	{
		if (i == 1 )
		{
			return j ;
		}
		return multiplication(i - 1, j) + j ;
	}
	
	public static int findLast(String[] a ,int startingIndex, String target)
	{
		if (a.length < 1 || startingIndex < 0 || startingIndex >= a.length)
		{
			return - 1 ;
		}
		if (a[startingIndex].equals(target))
		{
			return startingIndex ;
		}
		return findLast(a, startingIndex - 1 , target) ;
	}
	

	
}

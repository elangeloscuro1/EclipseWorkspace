package polynomial ;

public class Poly
{
	private static class Node
	{
		private Node link ;
		private Term data ;

		public Node(Term term, Node link)
		{
			this.data = term ;
			this.link = link ;
		}
	}

	private Node head ;

	public Poly()
	{
		head = null ;
	}

	public void add(Term term)
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
	}
	
	public void display()
	{
		Node newNode = head ;
		while (newNode != null)
		{			
			System.out.print(newNode.data.getTerm()) ;
			newNode = newNode.link ;
			System.out.print(newNode != null ? " + " : "\n") ;
		}
	}
	

	


	
	public Poly addPoly(Poly first, Poly second)
	{
		Poly temp = new Poly() ;
		Node polyA = first.head ;
		Node polyB = second.head ;		
		int constant = 0 ;
		
		while (polyA != null || polyB != null)
		{
			int exponentA = polyA == null ? -1 : polyA.data.getExponent() ;
			int exponentB = polyB == null ? -1 : polyB.data.getExponent() ;
			int currentExponet = exponentA > exponentB ? exponentA : exponentB ;
			int coefficient = 0 ;
			
			
			while (polyA != null && polyA.data.getExponent() == currentExponet)
			{
				if (polyA.data.isConstant())
				{
					constant += polyA.data.getCoefficient() ;
				}
				else
				{
					coefficient += polyA.data.getCoefficient() ;
				}
				polyA = polyA.link ;		
			}		
			
			while (polyB != null && polyB.data.getExponent() == currentExponet)
			{
				if (polyB.data.isConstant())
				{
					constant += polyB.data.getCoefficient() ;
				}
				else
				{
					coefficient += polyB.data.getCoefficient() ;
				}
				polyB = polyB.link ;
			}
			temp.add(new Term(coefficient, currentExponet)) ;
		}
		if (constant != 0)
		{
			temp.add(new Term(constant,  "" , 1)) ;	
		}
		return temp ;		
	}
	
	public static void main1(String[] args)
	{
		Poly list = new Poly() ;
		Poly list2 = new Poly() ;
		Poly list3 = new Poly() ;
		
		list.add(new Term(3 , 0)) ;
		list.add(new Term(3 , 4)) ;
		list.add(new Term(2, 2)) ;
		list.add(new Term(3, 1)) ;
		list.add(new Term(7, "", 1));
		
		list2.add(new Term(2, 3));
		list2.add(new Term(4, 1));
		list2.add(new Term(5, "",1));
		
		list.add(new Term(3 , 4)) ;
		list.add(new Term(2, 2)) ;
		list.add(new Term(3, 1)) ;
		list.add(new Term(7, "", 1));
		
		list2.add(new Term(2, 3));
		list2.add(new Term(4, 1));
		list2.add(new Term(5, "",1));
		
		list.display();	
		list2.display();	
		list3 = list3.addPoly(list, list2) ;
		list3.display();
		
		
/*		list3 = list.addLikeTerms(list, list2) ;
		list3.display();*/
		
		
		
		
		
		
		
		
		
	}

}







































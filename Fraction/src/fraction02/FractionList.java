package fraction02;

import java.util.ArrayList ;

public class FractionList
{
	private int minValue ;
	private int maxValue ;
	private boolean integer ;
	private boolean reduced ;
	private boolean duplicated ;
	private boolean negative ;
	private ArrayList<Fraction> fractionList ;

	/**
	 * 
	 * @param minValue
	 * @param maxValue
	 * @param quantity
	 * @param integer
	 * @param duplicated
	 * @param reduced
	 * @param negative
	 */
	public FractionList(int minValue, int maxValue,int quantity,
			boolean integer, boolean duplicated, boolean reduced, boolean negative)
	{
		if (minValue < 0 || maxValue < 0 || quantity < 0)
		{
			throw new IllegalArgumentException("Values cannot be negative!") ;
		}
		this.minValue = minValue < maxValue ? minValue : maxValue ;
		this.maxValue = maxValue > minValue ? maxValue : minValue ;
		this.integer = integer ;
		this.reduced = reduced ;
		this.negative = negative ;
		this.duplicated = duplicated ;
		
		this.fractionList = new ArrayList<Fraction> (quantity) ;
		
		int randomCheckNum = this.minValue ;
		int rendomCheckDenom = randomCheckNum ;
		
		for (int i = 0 ; i < quantity ; i++)
		{			
			int num = random(this.minValue, this.maxValue) ;
			int denom = random(this.minValue, this.maxValue) ;
			denom = denom != 0 ? denom : 1 ;
			num = negative && random(0, 3) == 1 ? num * - 1 : num ;  
			
			randomCheckNum = num == randomCheckNum + 1 ? ++randomCheckNum : randomCheckNum ;
			rendomCheckDenom = denom == rendomCheckDenom + 1 ? ++rendomCheckDenom : rendomCheckDenom ;				
			
			Fraction newFraction = new Fraction(num, denom) ;			
			boolean exists = fractionList.contains(newFraction) ;
			boolean randomUsed = randomCheckNum == this.maxValue && rendomCheckDenom == this.maxValue ;
			
			if (!this.duplicated && exists || !this.integer && newFraction.isWholeNumber() 
			|| ! this.reduced && Fraction.isReducedFraction(num, denom) )
			{				
				i-- ;				
				this.maxValue = randomUsed || !integer && num == 0 ? ++this.maxValue : this.maxValue ;
			}			
			else
			{
				fractionList.add(newFraction) ;
			}		
		}
	}	
	private int random(int minValue, int maxValue)
	{
		return (int) (Math.random() * (maxValue - minValue + 1)) + minValue ;
	}	
	public int getMinValue()
	{
		return minValue ;
	}
	public int getMaxValue()
	{
		return maxValue ;
	}
	public boolean isInteger()
	{
		return integer ;
	}
	public boolean isReduced()
	{
		return reduced ;
	}
	public boolean isDuplicated()
	{
		return duplicated ;
	}
	public boolean isNegative()
	{
		return negative ;
	}
	public static void main(String[] args)
	{
		FractionList test = new FractionList(1, 1, 50, false, false, false, false) ;
		System.out.println(test.maxValue) ;
		for (Fraction string : test.fractionList)
		{
			System.out.println(string) ;
		}
		
		
	}

}

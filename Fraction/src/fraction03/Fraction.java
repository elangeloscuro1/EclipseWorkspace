package fraction03;



public class Fraction
{
	public static void main(String[] args)
	{
		for (int i = 0 ; i < 5; i++)
		{
			for (int j = 1; j < 5; j++)
			{
				Fraction test = new Fraction(i, j, 1) ;
				if (test.isReducedFraction(i, j))
					System.out.println(test) ;
			}
		}
	}
	/** numerator */
	private int numerator ;
	/** denominator */
	private int denominator ;
	/** greatest common factor */
	private int greatestCommonFactor ;
	
	
	/**
	 * 
	 * @param numerator
	 * @param denominator
	 */
	public Fraction(int numerator, int denominator, int greatestCommonFactor)
	{
		if (denominator == 0)
		{
			throw new ArithmeticException("Denominator cannot be zero!") ;
		}
		this.greatestCommonFactor = greatestCommonFactor ;
		this.numerator = numerator * greatestCommonFactor ;
		this.denominator = denominator * greatestCommonFactor ;
	}
	
	/**
	 * 
	 * @param fraction
	 */
	public Fraction(Fraction fraction)
	{
		this.numerator = fraction.numerator ;
		this.denominator = fraction.denominator ;
		this.greatestCommonFactor = fraction.denominator ;
	}
	
	
	/**
	 * 
	 * @return
	 */
	public int getReducedNumerator()
	{
		return this.numerator / this.greatestCommonFactor ; 
	}
	
	/**
	 * 
	 * @return
	 */
	public int getReducedDenominator()
	{
		return this.denominator / this.greatestCommonFactor ; 
	}
	
	/**
	 * 
	 * @return
	 */
	public boolean isWholeNumber()
	{
		return this.numerator % this.denominator == 0 ;
	}
	
	
	
	
	
	/**
	 * Checks reduced fraction.
	 * 
	 * @param numerator: Current numerator
	 * @param denominator: Current denominator
	 * @return true if a fraction cannot be reduced.
	 */
	public boolean isReducedFraction(int numerator, int denominator)
	{
		numerator = numerator < 0 ? numerator * -1 : numerator ;
		denominator = denominator < 0 ? denominator * -1 : denominator ;		
		return (denominator != 0 && numerator != denominator) 
		 && !( (numerator != 0 && denominator == 1) 
		    || (numerator > 1  && denominator % numerator == 0)  
		    || (denominator > 1  && numerator != 0 && numerator % denominator == 0)  
		    || (denominator > 1  && numerator > 1  && numerator % 2 == 0 && denominator % 2 == 0 ) )  ;
	}
	
	
	/**
	 * @param
	 * @return
	 */
	@Override
	public boolean equals(Object obj)
	{
		if (obj == null || getClass() != obj.getClass())
		{
			return false ;
		}
		Fraction other = (Fraction) obj ;
		return this.numerator == other.numerator 
			&& this.denominator == other.denominator
			&& this.greatestCommonFactor == other.greatestCommonFactor ;
	}
	
	/**
	 * @param
	 * @return
	 */
	@Override
	public String toString()
	{
		if (isWholeNumber())
		{
			return String.format("%3s/%-3s = %4s", this.numerator, 
					this.denominator, getReducedNumerator());
		}
		return String.format("%3s/%-3s = %3s/%-3s", this.numerator, 
				this.denominator, getReducedNumerator(), getReducedDenominator()) ;
	}
}

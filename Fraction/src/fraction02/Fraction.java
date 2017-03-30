package fraction02 ;

/**
 * NOTE:
 *  0 / n is a fraction         => 0/n = 0 
 *	n / 1 is a fraction         => n/1 = n
 *	n / n n != 0 is a fraction  => n/n = 1
 * @author Angel Tapia
 */
public class Fraction
{
	/** numerator */
	private int numerator ;
	/** denominator */
	private int denominator = 1 ;
	/** greatest common factor */
	private int greatestCommonFactor ;
	
	/**
	 * 
	 * @param numerator
	 * @param denominator
	 */
	public Fraction(int numerator, int denominator)
	{
		if (denominator == 0)
		{
			throw new ArithmeticException("Denominator cannot be zero!") ;
		}
		setNumerator(numerator) ;
		setDenominator(denominator) ;
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
	 * @param numerator
	 */
	public void setNumerator(int numerator)
	{		
		this.numerator =  this.denominator > 0 ? numerator : numerator * -1 ;
	}
	
	/**
	 * 
	 * @return
	 */
	public int getNumerator()
	{
		return numerator ;
	}
	
	/** 
	 * 
	 * Precondition: setNumerator before setDenominator
	 * */
	public void setDenominator(int denominator)
	{
		if (denominator == 0)
		{
			throw new ArithmeticException("Denominator cannot be zero!") ;
		}
		this.denominator = denominator > 0 ? denominator : denominator * -1 ;
		this.numerator =  denominator > 0 ? this.numerator : this.numerator * -1 ;
		
		int tempNumerator = this.numerator > 0 ? this.numerator : numerator * -1 ;		
		int tempDenominator = this.denominator > 0 ? this.denominator : this.denominator * -1 ;		
		this.greatestCommonFactor = getGCF(tempNumerator, tempDenominator) ;
	}
	
	/**
	 * 
	 * @return
	 */
	public int getDenominator()
	{
		return denominator ;
	}
	
	/**
	 * 
	 * @return
	 */
	public int getGreatestCommonFactor()
	{
		return greatestCommonFactor ;
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
	 * Checks reduced fraction.
	 * 
	 * @param numerator: Current numerator
	 * @param denominator: Current denominator
	 * @return true if a fraction cannot be reduced.
	 */
	public static boolean isReducedFraction(int numerator, int denominator)
	{
		numerator = numerator > 0 ? numerator : numerator * - 1 ;
		denominator = denominator > 0 ? denominator : denominator * - 1 ;		
		return (denominator != 0 && numerator != denominator) 
		 && !( (numerator != 0 && denominator == 1) 
		    || (numerator > 1  && denominator % numerator == 0)  
		    || (denominator > 1  && numerator != 0 && numerator % denominator == 0)  
		    || (denominator > 1  && numerator > 1  && numerator % 2 == 0 && denominator % 2 == 0 ) )  ;
	}
	
	public boolean isWholeNumber()
	{
		return this.numerator % this.denominator == 0 ;
	}
	
	/**
	 * Finds the greatest common factor of TWO POSITIVE INTEGERS.
	 * 
	 * @param numerator: Current numerator
	 * @param denominator: Current denominator
	 * @return the common greatest factor of two integers.
	 * @throws ArithmeticException if denominator is 0.
	 */
	public int getGCF(int numerator, int denominator)
	{
		if (denominator == 0)
		{
			throw new ArithmeticException("Divided by zero exception") ;
		}

		if (numerator < 0 || denominator < 0)
		{
			throw new IllegalArgumentException("Values cannot be negative!") ;
		}
		numerator = numerator > 0 ? numerator : numerator * -1 ;
		denominator = denominator > 0 ? denominator : denominator * -1 ;		
		int minValue = numerator < denominator ? numerator : denominator ;
		
		return getGCF(numerator, denominator, minValue) ;
	}

	/**
	 * Finds the greatest common factor of TWO POSITIVE INTEGERS.
	 * 
	 * @param numerator: Current numerator
	 * @param denominator: Current denominator
	 * @param minValue: minimum value between numerator and denominator.
	 * @return the common greatest factor of two integers.
	 * @throws ArithmeticException if denominator is 0.
	 */
	private int getGCF(int numerator, int denominator, int minValue)
	{
		if (denominator == 0)
		{
			throw new ArithmeticException("Divided by zero exception") ;
		}
		if (minValue == 1 || numerator == 0)
		{
			return (minValue == 1) ? 1 : (denominator > 0 ? denominator : denominator * -1) ;
		}
		if (numerator % minValue == 0 && denominator % minValue == 0)
		{
			return minValue ;
		}
		return getGCF(numerator, denominator, minValue - 1) ;
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
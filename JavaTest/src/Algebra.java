// Expression: variables, coefficients, operators, exponents, and parentheses


public class Algebra
{
	/*Constants for default values*/
	public static final String DEFAULT_SIGN = "" ;
	public static final int DEFAULT_COEFFICIENT = 0 ;	
	
	 /*Instance Variables*/
	private String sign ;
	private double coefficient ;
	
	
	/*Constructors*/
	public Algebra()
	{
		this.sign = DEFAULT_SIGN ;
		this.coefficient = DEFAULT_COEFFICIENT ;
	}	
	public Algebra(double coefficient)
	{
		setCoefficient(coefficient) ;	
	}	
	
	/*Setters and Getters*/
	public void setSign(String sign)
	{
		this.sign = sign ;
	}	
	public String getSign()
	{
		return sign ;
	}	
	public void setCoefficient(double coefficient)
	{		
		this.sign = coefficient < 0 ? "-" : DEFAULT_SIGN ;
		this.coefficient = coefficient < 0 ? coefficient * -1 : coefficient ;		
	}
	public double getCoefficient()
	{
		return coefficient ;
	}
	
	/*Other Methods*/
	public double solve()
	{
		return Double.parseDouble( sign + coefficient) ;
	}
	
	
	public String getTerm(boolean parentheses)// Implementation
	{	
		//int wholeNumber = 
		return parentheses ? "("+ solve() + ")" : Double.toString(solve()) ;		
	}	
	
	/*toString and equals*/
	@Override
	public String toString()
	{
		return sign + coefficient ;
	}
	@Override
	public boolean equals(Object object)
	{
		if (object == null || getClass() != object.getClass())
		{
			return false ;
		}
		Algebra other = (Algebra) object ;
		return sign.equals(other.sign) && coefficient == other.coefficient  ;
	}	
}
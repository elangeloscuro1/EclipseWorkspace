/**
 * Term is a class that represent an algebraic term
 * 
 * 
 */

package polynomial ;

public class Term implements Comparable
{
	/** Default variable for variable */
	public static final String DEFAULT_VARIABLE = "x" ;		
	
	/** represent the exponent of a term */
	private int exponent ;
	/** coefficient represents the coefficient of a term */
	private int coefficient ;
	/** variable represent the variable of a term */
	private String variable ;
	
	/**
	 * Default constructor initializes every 
	 * instance variable to their default values.
	 */
	public Term()
	{
		this.exponent = 0 ;
		this.coefficient = 0 ;
		this.variable = DEFAULT_VARIABLE ;
	}
	
	/***
	 * Constructor that receives a coefficient and exponent
	 * and uses the default variable.
	 * 
	 * @param coefficient represents the coefficient of a term.
	 * @param exponent represents the exponent of the term.
	 */
	public Term(int coefficient, int exponent)
	{
		this.coefficient = exponent != 0 ? coefficient : 1;	
		this.exponent = exponent != 0 ? exponent : 1 ;			
		this.variable = exponent != 0 ? DEFAULT_VARIABLE : "" ;
	}
	
	/**
	 * Full constructor can receive a set a specific variable
	 * to a term or no variable if the term is a constant.
	 * 
	 * @param coefficient represents the coefficient of a term.
	 * @param variable represents a specific variable to be used.
	 * @param exponent represents the exponent of the term.
	 */
	public Term(int coefficient, String variable, int exponent)
	{
		this.variable = variable ;
		if (isConstant())
		{
			this.coefficient = (int) Math.pow(coefficient, exponent) ;
			this.exponent = 1 ;
		}
		else
		{
			this.exponent = exponent ;
			this.coefficient = coefficient ;
		}
	}
	
	/**
	 * Copy constructor creates a copy of a term.
	 * 
	 * @param term represents the term being copied.
	 */
	public Term(Term term)
	{
		this.coefficient = term.getCoefficient() ;
		this.exponent = term.getExponent() ;
		this.variable = term.getVariable() ;
	}
	
	/**
	 * Mutator for exponent variable.	
	 * @param exponent represents the value of the exponent for the term.
	 */
	public void setExponent(int exponent)
	{
		this.exponent = exponent ;
	}
	
	/**
	 * Accessor for exponent
	 * 
	 * @return the exponent of the term.
	 */
	public int getExponent()
	{
		return exponent ;
	}
	
	/**
	 * Mutator for variable coefficient.
	 * @param coefficient represents the value of the coefficient for the term.
	 */
	public void setCoefficient(int coefficient)
	{
		this.coefficient = coefficient ;
	}
	
	/**
	 * Accessor for variable coefficient.
	 * 
	 * @return the coefficient of the term.
	 */
	public int getCoefficient()
	{
		return coefficient ;
	}
	
	/**
	 * Mutator for variable
	 * 
	 * @param variable represents the new variable for the term.
	 */
	public void setVariable(String variable)
	{
		this.variable = variable ;
	}
	
	/**
	 * Accessor for variable
	 * 
	 * @return the variable for the term.
	 */
	public String getVariable()
	{
		return variable ;
	}
	
	/**
	 * Checks if the term is a constant.
	 * 
	 * @return true if variable has a length of 0.
	 */
	public boolean isConstant()
	{
		return variable.trim().length() == 0 ;
	}
	
	/**
	 * Removes any unnecessary elements to the term.
	 * 
	 * @return a term that is simple to read.
	 */
	public String getTerm()
	{
		String temp = "" ;
				
		switch (exponent)
		{
		case 0:
			temp = Integer.toString(coefficient) + 1 ;
			break ;
		case 1:
			temp = coefficient + variable ;
			break ;	
		default:
			temp = coefficient + variable + "^" + exponent ;
			break ;
		}
		if (!temp.equals("0") && variable.length() != 0)
		{
			switch (coefficient)
			{
			case 0:
				temp = "0" ;
				break ;
			case 1:
				temp = temp.substring(temp.indexOf("1") + 1) ;
				break ;
			case -1:
				temp = temp.substring(temp.indexOf("1") + 1) ;
				break ;
			default:
				break ;
			}
		}
		return temp  ;
	}
	
	/**
	 * @param object represents the object to be compared.
	 * 
	 * @return true if the two of the term have the same exponent.
	 */
	@Override
	public int compareTo(Object object)
	{
		Term other = (Term) object ;

		if (this.exponent > other.getExponent())
		{
			return 1 ;
		}
		else if (this.exponent == other.getExponent())
		{
			return 0 ;
		}
		return -1 ;
	}
	
	/**
	 * @return all of the values of the term
	 */
	@Override
	public String toString()
	{
		return coefficient + variable + "^" + exponent ;
	}
	
	/**
	 * @param object represents the object to be compared.
	 * 
	 * @return true if the two of the term have the same exponent and coefficient.
	 */
	@Override
	public boolean equals(Object object)
	{
		if (object == null || getClass() != object.getClass())
		{
			return false ;
		}
		
		Term other = (Term) object ;
		return exponent == other.getExponent() 
			&& coefficient == other.getCoefficient()  ;
	}
}
// Expression: variables, coefficients, operators, exponents, and parentheses


public class Variable extends Algebra
{
	// Constants for default values
	public static final int DEFAULT_VALUE = 0 ;
	public static final char DEFAULT_VARIABLE = 'a' ;
	
	private char variable ;
	private int value ;
	
	/*Constructors*/
	public Variable()
	{
		super() ;
		this.variable = DEFAULT_VARIABLE ;
		this.value = DEFAULT_VALUE ;
	}		
	public Variable(int coefficient, char variable)
	{
		super(coefficient) ;
		this.variable = variable ;
		this.value = DEFAULT_VALUE ;
	}
	public Variable(int coefficient, char variable, int value)
	{
		super(coefficient) ;
		this.variable = variable ;
		this.value = value ;
	}
	
	/*Setters and Getters*/	
	public void setVariable(char variable)
	{
		this.variable = variable ;
	}
	public char getVariable()
	{
		return variable ;
	}
	public void setValue(int value)
	{
		this.value = value ;
	}
	public int getValue()
	{
		return value ;
	}
	
/*	@Override
	public int solve()
	{
		return super.solve()  ;
	}
	@Override
	public String getTerm(boolean parentheses)
	{
		String temp = "" ;
		int key = getCoefficient() * getSign() ;
		
		switch (key)
		{
		case 0:
			temp = "0" ;			
			break ;
		case 1:
			temp = "" + variable ;		
			break ;
		case -1:
			temp = "-" + variable ;			
			break ;
		default:
			temp = super.getTerm(false) + variable ;
			break ;
		}
		return parentheses ? "("+ temp + ")" : temp ;
	}
*/

	/*toString and equals*/
	@Override
	public String toString()
	{
		return super.toString() + variable ;
	}
	@Override
	public boolean equals(Object object)
	{
		if (object == null || getClass() != object.getClass())
		{
			return false ;
		}
		Variable other = (Variable) object ;
		return super.equals(other)  && true ;
	}

}

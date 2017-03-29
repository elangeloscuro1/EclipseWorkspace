

public class Constant extends Algebra
{
	
	// Constants for default values
	public static final int DEFAULT_VALUE = 0 ;
	
	private int value ;
	
	/*Constructors*/
	public Constant()
	{
		super() ;
		this.value = DEFAULT_VALUE ;
	}		
	public Constant(int coefficient, int value)
	{
		super(coefficient) ;
		this.value = DEFAULT_VALUE ;
	}

}

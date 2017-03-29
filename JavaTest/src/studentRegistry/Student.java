

package studentRegistry;

public class Student
{
	/** Name represents the name of the student */
	private String name ;
	/** NumberInLine represents the position of the student in line */
	private int numberInLine ;
	
	/**
	 * Default constructor initializes all the variable to default values.
	 */
	public Student()
	{
		super() ;
		this.name = "Unknown" ;
		this.numberInLine = 0 ;
	}
	
	/**
	 * Full constructor initializes all of the instance variable.
	 * 
	 * @param name represent the name of the student.
	 * @param numberInLine represents the position of the student in line.
	 */
	public Student(String name, int numberInLine)
	{
		this.name = name ;
		this.numberInLine = numberInLine ;
	}
	
	/**
	 * Copy Constructor copy all of the students fields.
	 * 
	 * @param student the student to be copied
	 */
	public Student(Student student)
	{
		this.name = student.getName();
		this.numberInLine = student.getNumberInLine() ;
	}
	
	/**
	 * Mutator for the name of the student.
	 * 
	 * @param name represents the name of the stident.
	 */
	public void setName(String name)
	{
		this.name = name ;
	}
	
	/**
	 * Accessor for the name of the student.
	 * 
	 * @return the name of the student.
	 */
	public String getName()
	{
		return name ;
	}
	
	/**
	 * Mutator for the number of the student in line.
	 * 
	 * @param numberInLine represent the number of the student in line.
	 */
	public void setNumberInLine(int numberInLine)
	{
		this.numberInLine = numberInLine ;
	}
	
	/**
	 * Accessor for the number of the student in line
	 * 
	 * @return the number of the student in line.
	 */
	public int getNumberInLine()
	{
		return numberInLine ;
	}
	
	/**	
	 * Display all the information of the student .
	 */
	@Override
	public String toString()
	{
		return String.format("No. %-4sName: %s", numberInLine, name) ;
	}
	
	/**
	 * @param object represents the object to be compared.
	 * 
	 * @return true if both objects have the same name .
	 */
	@Override
	public boolean equals(Object object)
	{
		if (object == null || getClass() != object.getClass())
		{
			return false ;
		}		
		Student other = (Student) object ;
		return name.equals(other.getName())  ;
	}
}
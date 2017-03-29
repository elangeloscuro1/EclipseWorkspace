
public class Generic<T>
{
	private T[] array ;
	private int size ;
	
	public Generic()
	{		
		array = (T[]) new Object[10] ;
	}
	public void add(T object)
	{
		array[size++] = object ;
	}
	public T get(int index)
	{
		return array[index] ;
	}

}

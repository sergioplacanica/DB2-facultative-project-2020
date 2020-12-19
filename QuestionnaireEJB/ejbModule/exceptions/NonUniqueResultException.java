package exceptions;

public class NonUniqueResultException extends Exception {
	private static final long serialVersionUID = 1L;
	
	public NonUniqueResultException (String message)
	{
		super(message);
	}
}

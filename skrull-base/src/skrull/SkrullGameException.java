package skrull;

/**
 * Exception class for communicating general information from the game model
 * back to the client view
 * 
 * @author jesse
 *
 */
public class SkrullGameException extends SkrullException {

	public SkrullGameException(String msg) {
		super(msg);
	}

	public SkrullGameException(String msg, Exception e) {
		super(msg, e);
	}

	private static final long serialVersionUID = -1397276691048129416L;

}

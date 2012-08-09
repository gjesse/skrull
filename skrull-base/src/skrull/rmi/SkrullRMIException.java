package skrull.rmi;

import skrull.SkrullException;

/**
 * 
 * This exception can be used to wrap general RMI exceptions 
 * relaying error messages back to the client views.
 * 
 * @author jesse
 *
 */
public class SkrullRMIException extends SkrullException {


	private static final long serialVersionUID = -3028325514122457928L;

	public SkrullRMIException(String msg) {
		super(msg);
	}

	public SkrullRMIException(String msg, Throwable cause) {
		super(msg, cause);
	}

	public SkrullRMIException(Exception cause) {
		super(cause);
	}
}

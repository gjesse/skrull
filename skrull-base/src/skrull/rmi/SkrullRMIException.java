package skrull.rmi;

import skrull.SkrullException;

public class SkrullRMIException extends SkrullException {


	private static final long serialVersionUID = -3028325514122457928L;

	public SkrullRMIException(String msg) {
		super(msg);
	}

	public SkrullRMIException(String msg, Throwable cause) {
		super(msg, cause);
	}
}

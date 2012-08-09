package skrull;

/**
 * General Exception class. All app-specific checked exceptions should extend from this.
 * 
 * As well as debugging, this is used to communicate exceptions and errors back to the client views
 * 
 * @author jesse
 *
 */
public class SkrullException extends Exception {

	private String msg;

	public SkrullException(String msg) {
		super(msg);
		this.msg = msg;
	}

	/**
	 * Exception message is stored here for
	 * display to end user
	 * 
	 * @return msg
	 */
	public String getClientMessage(){
		return msg;
	}
	
	public SkrullException(String msg, Throwable cause) {
		super(msg, cause);
	}

	public SkrullException(Exception cause) {
		super(cause);
	}

	private static final long serialVersionUID = -3336461660381017745L;

}

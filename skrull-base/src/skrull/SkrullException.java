package skrull;

public class SkrullException extends Exception {

	private String msg;

	public SkrullException(String msg) {
		super(msg);
		this.msg = msg;
	}

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

package skrull;

public class SkrullGameException extends SkrullException {

	public SkrullGameException(String msg) {
		super(msg);
	}

	public SkrullGameException(String msg, Exception e) {
		super(msg, e);
	}

	private static final long serialVersionUID = -1397276691048129416L;

}

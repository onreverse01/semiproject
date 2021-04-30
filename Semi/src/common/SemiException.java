package common;

public class SemiException extends RuntimeException {

	public SemiException() {
		super();
	}

	public SemiException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public SemiException(String message, Throwable cause) {
		super(message, cause);
	}

	public SemiException(String message) {
		super(message);
	}

	public SemiException(Throwable cause) {
		super(cause);
	}
}

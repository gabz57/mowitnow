package org.mowitnow.exception;

public class MowitnowParseException extends Exception {

	private static final long serialVersionUID = -9102225959395665424L;

	public MowitnowParseException(String message) {
		super(message);
	}

	public MowitnowParseException(String message, Throwable cause) {
		super(message, cause);
	}

}

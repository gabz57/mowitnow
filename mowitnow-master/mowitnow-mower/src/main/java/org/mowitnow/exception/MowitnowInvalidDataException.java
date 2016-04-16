package org.mowitnow.exception;

public class MowitnowInvalidDataException extends IllegalArgumentException {

	private static final long serialVersionUID = -7478533058760793125L;

	public MowitnowInvalidDataException(String message) {
		super(message);
	}

	public MowitnowInvalidDataException(String message, Throwable cause) {
		super(message, cause);
	}

}

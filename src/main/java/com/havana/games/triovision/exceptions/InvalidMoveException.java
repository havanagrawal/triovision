package com.havana.games.triovision.exceptions;

public class InvalidMoveException extends RuntimeException {

	private static final long serialVersionUID = -750515244315911413L;
	private String message;
	private Throwable cause;

	public InvalidMoveException(String message, Throwable cause) {
		this.message = message;
		this.cause = cause;
	}
	
	public InvalidMoveException(String message) {
		this(message, null);
	}
	
	@Override
	public String getMessage() {
		return message;
	}
	
	@Override
	public synchronized Throwable getCause() {
		return cause;
	}
	
}

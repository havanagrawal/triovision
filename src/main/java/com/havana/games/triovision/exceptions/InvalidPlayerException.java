package com.havana.games.triovision.exceptions;

public class InvalidPlayerException extends RuntimeException {

	private static final long serialVersionUID = 7050325549307016692L;
	
	private String message;
	private Throwable cause;
	
	public InvalidPlayerException(String message, Throwable cause) {
		this.message = message;
		this.cause = cause;
	}
	
	

	public InvalidPlayerException(String message) {
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

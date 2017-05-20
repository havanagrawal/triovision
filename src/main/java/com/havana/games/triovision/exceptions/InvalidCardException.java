package com.havana.games.triovision.exceptions;

public class InvalidCardException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 205938033536276991L;
	private String message;

	public InvalidCardException(String message) {
		this.message = message;
	}
	
	@Override
	public String getMessage() {
		return message;
	}
	
}

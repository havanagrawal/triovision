package com.havana.games.triovision.cardloader;

public class MalformedCardException extends RuntimeException {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1794871465173647461L;
	private String message;

	public MalformedCardException(String message) {
		this.message = message;
	}

	@Override
	public String getMessage() {
		return message;
	}

}

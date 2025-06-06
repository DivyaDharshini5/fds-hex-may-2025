package com.project.simplyfly.exception;

public class InvalidIdException extends RuntimeException{
	/**
	 * 
	 */
	private static final long serialVersionUID = 5937584247889821673L;
	private String message;
	public String getMessage() {
		return message;
	}
	public InvalidIdException(String message) {
		this.message = message;
	}
}

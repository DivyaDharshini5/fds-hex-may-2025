package com.project.simplyfly.exception;

public class ResourceNotFoundException extends RuntimeException {

	private static final long serialVersionUID = -8183706157009762410L;
	private String message;
	public String getMessage() {
		return message;
	}
	public ResourceNotFoundException(String message) {
		this.message = message;
	}
	

}

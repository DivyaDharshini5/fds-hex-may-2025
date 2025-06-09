package com.springboot.hospitalManagement.exception;

public class PatientIDNotFoundException extends RuntimeException{

	
	private static final long serialVersionUID = 1L;
	
	private String message;
	public PatientIDNotFoundException(String message) {
		this.message = message;
	}
	public String getMessage() {
		return message;
	}
	

}

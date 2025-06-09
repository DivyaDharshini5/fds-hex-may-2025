package com.springboot.hospitalManagement.exception;

public class DoctorNotFoundException extends RuntimeException{

	
	private static final long serialVersionUID = 1L;
	
	private String message;
	public DoctorNotFoundException(String message) {
		this.message = message;
	}
	public String getMessage() {
		return message;
	}
	

}

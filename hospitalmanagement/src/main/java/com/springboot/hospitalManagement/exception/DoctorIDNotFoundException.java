package com.springboot.hospitalManagement.exception;

public class DoctorIDNotFoundException extends RuntimeException{

	
	private static final long serialVersionUID = 1L;
	
	private String message;
	public DoctorIDNotFoundException(String message) {
		this.message = message;
	}
	public String getMessage() {
		return message;
	}
	

}

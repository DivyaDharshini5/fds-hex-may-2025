package com.project.simplyfly;

import java.security.SignatureException;
import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.project.simplyfly.exception.ResourceNotFoundException;
@ControllerAdvice
public class GlobalExceptionHandler {
/*whenever a runtimeexception  is thrown in controller class, this method gets called
 * 
 * */
	@ExceptionHandler(exception = RuntimeException.class)
	public ResponseEntity<?> handleRuntime(RuntimeException e){
		Map<String,String > map = new HashMap<>();
		map.put("msg",e.getMessage());
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(map);
		
	}
	/*whenever a custom exception like
	 * resourcenotfoundException is thrown in controller,
	 * this method gets called
	 * *
	 * */
	@ExceptionHandler(exception=ResourceNotFoundException.class)
	public ResponseEntity<?> handleResourceNotFoundException(ResourceNotFoundException r){
		Map<String,String > map = new HashMap<>();
		map.put("msg",r.getMessage());
		return  ResponseEntity.status(HttpStatus.NOT_FOUND).body(map);
	}
	@ExceptionHandler(exception = SignatureException.class)
	public ResponseEntity<?> handleSignatureException(Exception e) {
		Map<String,String> map = new HashMap<>();
		map.put("msg", e.getMessage());
		return ResponseEntity
				.status(HttpStatus.UNAUTHORIZED)
				.body(map);
	}
}

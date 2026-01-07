package com.example.EnrollmentMicroservice.exceptions;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;


@RestControllerAdvice
public class GlobalExceptionHandler {
	
	@ExceptionHandler(Exception.class)
	   public ResponseEntity<Map<String, Object>> handleGenericException(Exception ex) {
	       Map<String, Object> error = new HashMap<>();
	       error.put("timestamp", LocalDateTime.now());
	       error.put("status", HttpStatus.INTERNAL_SERVER_ERROR.value());
	       error.put("error", "Internal Server Error");
	       error.put("message", ex.getMessage());
	       return new ResponseEntity<>(error, HttpStatus.INTERNAL_SERVER_ERROR);
	   }
	
	@ExceptionHandler(EnrollmentNotFoundException.class)
	public ResponseEntity<Map<String,Object>> handleUserNotFound(EnrollmentNotFoundException ex){
		Map<String,Object> error = new HashMap<>();
		error.put("timestamp", LocalDateTime.now());
		error.put("status", HttpStatus.NOT_FOUND.value());
		error.put("error", "Not Found");
		error.put("message", ex.getMessage());
		return new ResponseEntity<>(error,HttpStatus.BAD_REQUEST);
	}
}
package com.example.CourseMicroservice.exceptions;

public class CourseNotFoundException extends RuntimeException {
	
	public CourseNotFoundException(String message) {
		super(message);
	}

}

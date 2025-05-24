package com.SpringBootCognizant.exception;

public class DepartmentIdNotFoundException extends RuntimeException {
	
	public DepartmentIdNotFoundException(String message) {
		super(message);
	}
}

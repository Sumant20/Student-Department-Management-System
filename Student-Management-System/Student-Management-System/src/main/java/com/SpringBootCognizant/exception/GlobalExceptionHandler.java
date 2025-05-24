package com.SpringBootCognizant.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class GlobalExceptionHandler {
	
	@ExceptionHandler(DepartmentIdNotFoundException.class)
	@ResponseStatus(HttpStatus.NOT_FOUND)
	public String handleDepartmentIdNotFoundException(DepartmentIdNotFoundException e) {
		return e.getMessage();
	}
	
	@ExceptionHandler(NullTableException.class)
	@ResponseStatus(HttpStatus.NOT_FOUND)
	public String handleNullDatabaseException(NullTableException e) {
		return e.getMessage();
	}
	@ExceptionHandler(SpecialCharacterFoundException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public String handleSpecialCharacterException(SpecialCharacterFoundException e) {
		return e.getMessage();
	}
	
}

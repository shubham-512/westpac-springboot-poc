package com.wbc.user.errorhandler;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ApiExceptionHandler {
	
	@ExceptionHandler(InvalidUserException.class)
	public ResponseEntity<String> invalidUserHandler(InvalidUserException invalidUserException){
		return new ResponseEntity<>("Invalid user",HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(UserAlreadyExistException.class)
	public ResponseEntity<String> userAlreadyExistHandler(UserAlreadyExistException userAlreadyExistException){
		return new ResponseEntity<>("User already exist",HttpStatus.CONFLICT);
	}
}

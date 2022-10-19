package com.wbc.user.errorhandler;

public class InvalidUserException extends Exception{
	public InvalidUserException() {
		super();
	}
	
	public InvalidUserException(String message) {
		super(message);
	}
	
	
}

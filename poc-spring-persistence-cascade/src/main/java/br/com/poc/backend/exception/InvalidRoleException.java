package br.com.poc.backend.exception;

import org.springframework.beans.factory.annotation.Autowired;

public class InvalidRoleException extends Exception{
	
	private static final long serialVersionUID = 599738488673399792L;
	
	@Autowired
	public InvalidRoleException(String message) {
		super(message);
	};

}

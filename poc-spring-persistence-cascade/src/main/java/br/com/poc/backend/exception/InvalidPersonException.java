package br.com.poc.backend.exception;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class InvalidPersonException extends Exception{

	private static final long serialVersionUID = 599738488673399792L;

	@Autowired
	public InvalidPersonException(String message) {
		super(message);
	};
}

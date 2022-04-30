package br.com.poc.spring.security.backend.security.model;

import java.io.Serializable;

public class LoginCredentials implements Serializable{

	private static final long serialVersionUID = 3524802969904790045L;
	
	private String username;
	private String password;
	
	private LoginCredentials(String username, String password) {
		this.username = username;
		this.password = password;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	

}

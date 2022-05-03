package br.com.poc.spring.security.backend.security.model;

import java.io.Serializable;
import java.util.List;

import br.com.poc.spring.security.backend.model.Role;

public class TokenCredentials implements Serializable {

	private static final long serialVersionUID = -2139104040937333960L;
	
	private Integer id;
	private String token;
	private String username;
	private List<String> roles;
	
	public TokenCredentials(Integer id, String token, String username, List<String> roles) {
		this.id = id;
		this.token = token;
		this.username = username;
		this.roles = roles;
	}
	
	public Integer getId() {
		return id;
	}
	
	public void setId(Integer id) {
		this.id = id;
	}
	
	public String getToken() {
		return token;
	}
	
	public void setToken(String token) {
		this.token = token;
	}
	
	public String getUsername() {
		return username;
	}
	
	public void setUsername(String username) {
		this.username = username;
	}
	
	public List<String> getRoles() {
		return roles;
	}
	
	public void setRoles(List<String> roles) {
		this.roles = roles;
	}	
}

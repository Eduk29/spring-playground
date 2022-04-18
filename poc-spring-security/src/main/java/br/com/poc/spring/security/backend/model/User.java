package br.com.poc.spring.security.backend.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
@Table(name = "POC_SPRING_SECURITY_USER")
public class User {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID_USER", nullable = false, precision = 9, scale = 0)
	private Integer id;

	// Owner for Relationship between User and Role (JoinColumn)
	@JsonIgnoreProperties(value = "users", allowSetters = true)
	@ManyToMany(fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST, CascadeType.MERGE})
	@JoinTable(name = "POC_SPRING_CASCADE_REL_USER_ROLE",
				joinColumns = { @JoinColumn(name = "ID_USER") },
				inverseJoinColumns = { @JoinColumn(name = "ID_ROLE") })
	private List<Role> roles;
	
	@Column(name = "USERNAME_USER", nullable = true, length = 255)
	private String username;
	
	@Column(name = "PASSWORD_USER", nullable = true, length = 255)
	private String password;

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

	public Integer getId() {
		return id;
	}
	
	public void setId(Integer id) {
		this.id = id;
	}

	public List<Role> getRoles() {
		return roles;
	}

	public void setRoles(List<Role> roles) {
		this.roles = roles;
	}
}

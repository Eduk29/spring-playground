package br.com.poc.backend.model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
@Table(name = "POC_SPRING_CASCADE_USER")
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID_USER", nullable = false, precision = 9, scale = 0)
	private Integer id;
	
	// Owner for Relationship between User and Person (JoinColumn)
	@OneToOne(cascade = {CascadeType.MERGE, CascadeType.PERSIST})
	@JoinColumn(name = "ID_PERSON", referencedColumnName = "ID_PERSON")
	@JsonIgnoreProperties(value = "user", allowSetters = true)
	private Person person;
	
	@Column(name = "LOGIN_USER", nullable = true, length = 255)
	private String login;
	
	@Column(name = "PASSWORD_USER", nullable = true, length = 255)
	private String password;

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
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

	public Person getPerson() {
		return person;
	}

	public void setPerson(Person person) {
		this.person = person;
	}
}

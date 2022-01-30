package br.com.poc.backend.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@Entity
@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
@Table(name = "POC_SPRING_CASCADE_ROLE")
public class Role {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID_ROLE", nullable = false, precision = 9, scale = 0)
	private Integer id;
	
	@JsonIgnoreProperties(value = {"roles", "person"})
	@ManyToMany(fetch = FetchType.LAZY, mappedBy = "roles")
	@JsonInclude(Include.NON_NULL)
	private List<User> users;
	
	@Column(name = "CODE_ROLE", nullable = true, length = 255)
	private String code;
	
	@Column(name = "DESCRIPTION_ROLE", nullable = true, length = 255)
	private String description;
	
	@Column(name = "NAME_ROLE", nullable = true, length = 255)
	private String name;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public List<User> getUsers() {
		return users;
	}

	public void setUsers(List<User> users) {
		this.users = users;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}	
}

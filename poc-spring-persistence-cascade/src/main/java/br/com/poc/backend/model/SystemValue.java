package br.com.poc.backend.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@Entity
@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
@Table(name = "POC_SPRING_CASCADE_SYSTEM_VALUE")
public class SystemValue {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID_SYSTEM_VALUE", nullable = false, precision = 8, scale = 0)
	private Integer id;
	
	@Column(name = "DESCRIPTION_SYSTEM_VALUE", nullable = false, length = 255)
	private String description;

	@Column(name = "CODE_SYSTEM_VALUE", nullable = false, length = 255)
	private String code;
	
	@OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL, mappedBy = "type")
	@JsonInclude(Include.NON_NULL)
	@JsonIgnoreProperties(value = "type", allowSetters = true)
	private List<Contact> contacts;

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public List<Contact> getContacts() {
		return contacts;
	}

	public void setContacts(List<Contact> contacts) {
		this.contacts = contacts;
	}

	public Integer getId() {
		return id;
	}	
}

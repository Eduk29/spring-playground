package br.com.poc.backend.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@Entity
@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
@Table(name = "POC_SPRING_CASCADE_CONTACT")
public class Contact {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID_CONTACT", nullable = false, precision = 8, scale = 0)
	private Integer id;
	
	@JsonIgnoreProperties(value = "contacts", allowSetters = true)
	@ManyToMany(fetch = FetchType.LAZY, cascade = { CascadeType.PERSIST, CascadeType.REFRESH }, mappedBy = "contacts")
	@JsonInclude(Include.NON_NULL)
	private List<Person> persons;
	
	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name = "ID_TYPE_CONTACT")
	@JsonIgnoreProperties(value = "contacts", allowSetters = true)
	private SystemValue type;

	@Column(name = "CONTACT_CONTACT", nullable = true, length = 255)
	private String contact;

	public List<Person> getPersons() {
		return persons;
	}

	public void setPersons(List<Person> persons) {
		this.persons = persons;
	}

	public String getContact() {
		return contact;
	}

	public void setContact(String contact) {
		this.contact = contact;
	}

	public Integer getId() {
		return id;
	}

	public SystemValue getType() {
		return type;
	}

	public void setType(SystemValue type) {
		this.type = type;
	}
}

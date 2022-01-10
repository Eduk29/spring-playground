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
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
@Table(name = "POC_SPRING_CASCADE_PERSON")
public class Person {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID_PERSON", nullable = false, precision = 9, scale = 0)
	private Integer id;
	
	@JsonIgnoreProperties(value = "persons", allowSetters = true)
	@ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinTable(name = "POC_SPRING_CASCADE_REL_PERSON_CONTACT",
				joinColumns = { @JoinColumn(name = "ID_PERSON") },
				inverseJoinColumns = { @JoinColumn(name = "ID_CONTACT") })
	private List<Contact> contacts;
	
	@OneToOne(fetch = FetchType.LAZY, cascade = {CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH}, mappedBy = "person")
	@JsonIgnoreProperties(value = "person", allowSetters = true)
	private User user;
	
	@Column(name = "NAME_PERSON", nullable = true, length = 255)
	private String name;
	
	@Column(name = "AGE_PERSON", nullable = true, length = 255)
	private String age;
	
	@Column(name = "HEIGHT_PERSON", nullable = true, length = 255)
	private String height;
	
	@Column(name = "WEIGHT_PERSON", nullable = true, length = 255)
	private String weight;

	public List<Contact> getContacts() {
		return contacts;
	}

	public void setContacts(List<Contact> contacts) {
		this.contacts = contacts;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAge() {
		return age;
	}

	public void setAge(String age) {
		this.age = age;
	}

	public String getHeight() {
		return height;
	}

	public void setHeight(String height) {
		this.height = height;
	}

	public String getWeight() {
		return weight;
	}

	public void setWeight(String weight) {
		this.weight = weight;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
}

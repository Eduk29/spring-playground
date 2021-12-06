package br.com.poc.backend.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.poc.backend.model.Person;
import br.com.poc.backend.repository.PersonRepository;

@Service
@Transactional
public class PersonService {

	@Autowired
	private PersonRepository personRepository;
	
	public List<Person> listAll() {
		return this.personRepository.findAll();
	}
	
	public Person findById(Integer id) {
		return this.personRepository.findById(id);
	}
	
	public Person save(Person person) {
		return this.personRepository.save(person);
	}
	
	public Person update(Person person, Integer id) {
		if (!!this.personRepository.existsById(id)) {
			Integer personId = personRepository.findById(id).getId();
			person.setId(personId);
			return personRepository.save(person);
		}
		
		return null;
	}
	
	public void removeById(Integer id) {
		this.personRepository.deleteById(id);
	}
}

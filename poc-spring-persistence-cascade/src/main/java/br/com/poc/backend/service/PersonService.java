package br.com.poc.backend.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.poc.backend.model.Contact;
import br.com.poc.backend.model.Person;
import br.com.poc.backend.model.SystemValue;
import br.com.poc.backend.repository.PersonRepository;
import br.com.poc.backend.repository.SystemValueRepository;

@Service
@Transactional
public class PersonService {

	@Autowired
	private PersonRepository personRepository;
	
	@Autowired
	private SystemValueRepository systemValueRepository;
	
	public List<Person> listAll() {
		return this.personRepository.findAll();
	}
	
	public Person findById(Integer id) {
		return this.personRepository.findById(id);
	}
	
	public Person save(Person person) {
		this.fillContactWithSystemValue(person.getContacts());
		return this.personRepository.save(person);
	}
	
	public Person update(Person person, Integer id) {
		if (!!this.personRepository.existsById(id)) {
			Integer personId = personRepository.findById(id).getId();
			person.setId(personId);
			personRepository.save(person);
			
			return personRepository.findById(id);
		}
		
		return null;
	}
	
	public void remove(Integer id) {
		this.personRepository.deleteById(id);
	}
	
	private void fillContactWithSystemValue (List<Contact> contacts) {
		for (int counter = 0; counter < contacts.size(); counter++) {
			if (contacts.get(counter).getType().getDescription() == null ||
					contacts.get(counter).getType().getCode() == null) {
				Integer systemTypeId = contacts.get(counter).getType().getId();
				SystemValue systemValueToAdd = this.systemValueRepository.findById(systemTypeId);
				contacts.get(counter).setType(systemValueToAdd);
			}
		}		
	}
}

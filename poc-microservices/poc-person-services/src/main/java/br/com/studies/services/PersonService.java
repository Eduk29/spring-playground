package br.com.studies.services;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import br.com.studies.enums.MessagesEnum;
import br.com.studies.models.Person;
import br.com.studies.repositories.PersonRepository;

@Service
public class PersonService {
    @Autowired
    private PersonRepository personRepository;
    
    @Autowired
    private RestTemplate restTemplate;
    
    private static final String AUTH_SERVICE_URL = "http://authentication-service/authentication/v1/api/register";
    private static final String USER_LOOKUP_URL = "http://authentication-service/authentication/v1/api/user/";
    
    public Person create(Person person) {
        this.setCreatedAtValue(person);
        this.setUpdatedAtValue(person);
        restTemplate.postForEntity(AUTH_SERVICE_URL, person, String.class);
        return personRepository.save(person);
    }
    
    public void deletePersonById(Long id) {
    	this.personExistsInDB(id);
        personRepository.deleteById(id);
    }
    
    public List<Person> getAllPersons() {
        return personRepository.findAll();
    }
    
    public Optional<Person> getById(Long id) {
        return personRepository.findById(id);
    }    

    public Optional<Person> findPerson(String query) {
        return personRepository.findByCpf(query)
                .or(() -> personRepository.findByNameContainingIgnoreCase(query))
                .or(() -> {
                    ResponseEntity<String> response = restTemplate.getForEntity(USER_LOOKUP_URL + query, String.class);
                    if (response.getStatusCode().is2xxSuccessful()) {
                        return personRepository.findByUserId(response.getBody());
                    }
                    return Optional.empty();
                });
    }
    
    public Person updatePerson(Long id, Person updatedPerson) {
        return personRepository.findById(id).map(person -> {
            person.setName(updatedPerson.getName());
            person.setAge(updatedPerson.getAge());
            person.setUpdatedAt(LocalDate.now());
            return personRepository.save(person);
        }).orElseThrow(() -> new RuntimeException("Person not found"));
    }
    
    private void personExistsInDB(Long id) throws RuntimeException {
    	boolean personExists = this.personRepository.existsById(id);
    	if (!personExists) {
    		throw new RuntimeException(MessagesEnum.USER_NOT_FOUND.getDescription());
    	}
    }
    
    private void setCreatedAtValue(Person person) {
    	person.setCreatedAt(LocalDate.now());
    }
    
    private void setUpdatedAtValue(Person person) {
    	person.setUpdatedAt(LocalDate.now());
    }

}

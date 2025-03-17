package br.com.studies.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.studies.models.Person;
import br.com.studies.services.PersonService;

@RestController
@RequestMapping("/persons")
@CrossOrigin(origins = "http://localhost:4200")
public class PersonController {
	@Autowired
    private PersonService personService;
	
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deletePerson(@PathVariable Long id) {
        try {        	
        	this.personService.deletePersonById(id);
        	return ResponseEntity.noContent().build();
        } catch (RuntimeException error) {        	
        	return ResponseEntity.badRequest().body(error.getMessage());
        }
    }
    
    @GetMapping("/search")
    public ResponseEntity<Optional<Person>> findPerson(@RequestParam String query) {  	
        return ResponseEntity.ok(personService.findPerson(query));
    }
    
    @GetMapping
    public ResponseEntity<List<Person>> getAllPersons() {
    	List<Person> persons = personService.getAllPersons();
        return ResponseEntity.ok(persons);
    }
    
    @PostMapping
    public ResponseEntity<Person> createPerson(@RequestBody Person personToSave) {
    	this.personService.create(personToSave);
        return ResponseEntity.ok(personService.create(personToSave));
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<Person> updatePerson(@PathVariable Long id, @RequestBody Person person) {
        return ResponseEntity.ok(personService.updatePerson(id, person));
    }
    

}
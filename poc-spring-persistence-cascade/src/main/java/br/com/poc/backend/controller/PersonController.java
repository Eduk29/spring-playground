package br.com.poc.backend.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.poc.backend.model.Person;
import br.com.poc.backend.service.PersonService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/persons")
@Api(tags = "Persons")
public class PersonController {

	@Autowired
	private PersonService personService;
	
	@GetMapping("")
	@ApiOperation(value = "List all persons.")
	public List<Person> listAll() {
		return this.personService.listAll();
	}
	
	@GetMapping("/{id}")
	@ApiOperation(value = "List a specific person.")
	public Person findById(@PathVariable("id") Integer id) {
		return this.personService.findById(id);
	}
	
	@PostMapping(path = "/new", consumes = "application/json")
	@ApiOperation(value = "Create a new person.")
	public Person save(@RequestBody Person person) {
		return this.personService.save(person);
	}
	
	@PutMapping(path = "/{id}/update")
	@ApiOperation(value = "Update a specific person.")
	public Person update(@RequestBody Person person, @PathVariable("id") Integer id) {
		return this.personService.update(person, id);
	}
	
	@DeleteMapping("/{id}/remove")
	@ApiOperation(value = "Remove a specific person.")
	public void remove(@PathVariable("id") Integer id) {
		personService.remove(id);
	}
	
}

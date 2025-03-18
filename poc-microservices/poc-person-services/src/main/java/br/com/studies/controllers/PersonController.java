package br.com.studies.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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

import br.com.studies.dtos.CustomPageDTO;
import br.com.studies.models.Person;
import br.com.studies.services.PersonService;

@RestController
@RequestMapping("/persons")
@CrossOrigin(origins = "http://localhost:4200")
public class PersonController {
	@Autowired
	private PersonService personService;

	@DeleteMapping("/{id}")
	public ResponseEntity<?> deleteById(@PathVariable(value = "id") Integer id) {
		try {
			this.personService.deletePersonById(id);
			return new ResponseEntity<>(HttpStatus.OK);
		} catch (RuntimeException error) {
			return new ResponseEntity<>(error.getMessage(), HttpStatus.BAD_REQUEST);
		}
	}

	@GetMapping("")
	public ResponseEntity<?> findAll(@RequestParam(value = "$pageNumber", required = true) Integer pageNumber,
			@RequestParam(value = "$pageSize", required = true) Integer pageSize) {
		try {
			CustomPageDTO<Person> response = this.personService.findAll(pageNumber, pageSize);
			return new ResponseEntity<>(response, HttpStatus.OK);
		} catch (RuntimeException error) {
			return new ResponseEntity<>(error.getMessage(), HttpStatus.BAD_REQUEST);
		}

	}

	@GetMapping("{id}")
	public ResponseEntity<?> findById(@PathVariable(value = "id") Integer id) {
		try {
			CustomPageDTO<Person> response = this.personService.findById(id);
			return new ResponseEntity<>(response, HttpStatus.OK);
		} catch (RuntimeException error) {
			return new ResponseEntity<>(error.getMessage(), HttpStatus.BAD_REQUEST);
		}
	}

	@PostMapping("register")
	public ResponseEntity<?> register(@RequestBody Person personToSave) {
		try {
			CustomPageDTO<Person> response = this.personService.register(personToSave);
			return new ResponseEntity<>(response, HttpStatus.OK);
		} catch (RuntimeException error) {
			return new ResponseEntity<>(error.getMessage(), HttpStatus.BAD_REQUEST);
		}
	}

	@GetMapping("/search")
	public ResponseEntity<?> search(
			@RequestParam(value = "$pageNumber", required = false) Integer pageNumber,
			@RequestParam(value = "$pageSize", required = false) Integer pageSize,
			@RequestParam(value = "$filter", required = false) String filter) {
		try {
			CustomPageDTO<Person> response = this.personService.searchByQuery(pageNumber, pageSize, filter);
			return new ResponseEntity<>(response, HttpStatus.OK);
		} catch (RuntimeException error) {
			return new ResponseEntity<>(error.getMessage(), HttpStatus.BAD_REQUEST);
		}
	}

	@PutMapping("/{id}/update")
	public ResponseEntity<?> update(@PathVariable(value = "id") Integer id, @RequestBody Person person) {
		try {
			this.personService.updateById(id, person);
			return new ResponseEntity<>(person, HttpStatus.OK);
		} catch (RuntimeException error) {
			return new ResponseEntity<>(error.getMessage(), HttpStatus.BAD_REQUEST);
		}
	}

}
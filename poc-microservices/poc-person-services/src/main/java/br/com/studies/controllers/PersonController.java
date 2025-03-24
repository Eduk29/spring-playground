package br.com.studies.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
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
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/persons")
@CrossOrigin(origins = "http://localhost:4200")
@Tag(name="Person Management API")
public class PersonController {
	@Autowired
	private PersonService personService;

	@Operation(summary = "Delete a Person by ID", description = "Deletes a person from the system. Requires ADMIN role.")
	@ApiResponses(value = { @ApiResponse(responseCode = "200", description = "Person deleted successfully"),
			@ApiResponse(responseCode = "400", description = "Invalid request or person not found") })
	@DeleteMapping("/{id}")
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<?> deleteById(@PathVariable(value = "id") Integer id) {
		try {
			this.personService.deletePersonById(id);
			return new ResponseEntity<>(HttpStatus.OK);
		} catch (RuntimeException error) {
			return new ResponseEntity<>(error.getMessage(), HttpStatus.BAD_REQUEST);
		}
	}

	@Operation(summary = "Get all persons", description = "Returns a paginated list of all persons. Requires ADMIN role.")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Persons retrieved successfully", content = @Content(mediaType = "application/json", schema = @Schema(implementation = CustomPageDTO.class))),
			@ApiResponse(responseCode = "400", description = "Invalid request parameters") })
	@GetMapping("")
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<?> findAll(@RequestParam(value = "$pageNumber", required = true) Integer pageNumber,
			@RequestParam(value = "$pageSize", required = true) Integer pageSize) {
		try {
			CustomPageDTO<Person> response = this.personService.findAll(pageNumber, pageSize);
			return new ResponseEntity<>(response, HttpStatus.OK);
		} catch (RuntimeException error) {
			return new ResponseEntity<>(error.getMessage(), HttpStatus.BAD_REQUEST);
		}

	}

	@Operation(summary = "Get person by ID", description = "Retrieves a person details by ID. Requires ADMIN role.")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Person found", content = @Content(mediaType = "application/json", schema = @Schema(implementation = CustomPageDTO.class))),
			@ApiResponse(responseCode = "400", description = "Invalid request or person not found") })
	@GetMapping("{id}")
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<?> findById(@PathVariable(value = "id") Integer id) {
		try {
			CustomPageDTO<Person> response = this.personService.findById(id);
			return new ResponseEntity<>(response, HttpStatus.OK);
		} catch (RuntimeException error) {
			return new ResponseEntity<>(error.getMessage(), HttpStatus.BAD_REQUEST);
		}
	}

	@Operation(summary = "Register new person", description = "Registers a new person in the system.")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Person registered successfully", 
                     content = @Content(mediaType = "application/json", schema = @Schema(implementation = CustomPageDTO.class))),
        @ApiResponse(responseCode = "400", description = "Invalid request data")
    })
	@PostMapping("register")
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<?> register(@RequestBody Person personToSave) {
		try {
			CustomPageDTO<Person> response = this.personService.register(personToSave);
			return new ResponseEntity<>(response, HttpStatus.OK);
		} catch (RuntimeException error) {
			return new ResponseEntity<>(error.getMessage(), HttpStatus.BAD_REQUEST);
		}
	}

	@Operation(summary = "Search person by some criteria", description = "Search person by some criteria like name, cpf or id")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Persons retrieved successfully", 
                     content = @Content(mediaType = "application/json", schema = @Schema(implementation = CustomPageDTO.class))),
        @ApiResponse(responseCode = "400", description = "Invalid request data")
    })
	@GetMapping("/search")
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<?> search(@RequestParam(value = "$pageNumber", required = false) Integer pageNumber,
			@RequestParam(value = "$pageSize", required = false) Integer pageSize,
			@RequestParam(value = "$filter", required = false) String filter) {
		try {
			CustomPageDTO<Person> response = this.personService.searchByQuery(pageNumber, pageSize, filter);
			return new ResponseEntity<>(response, HttpStatus.OK);
		} catch (RuntimeException error) {
			return new ResponseEntity<>(error.getMessage(), HttpStatus.BAD_REQUEST);
		}
	}

	@Operation(summary = "Update Person details", description = "Updates a person information.")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Person updated successfully", 
                     content = @Content(mediaType = "application/json", schema = @Schema(implementation = CustomPageDTO.class))),
        @ApiResponse(responseCode = "400", description = "Invalid request data")
    })
	@PutMapping("/{id}/update")
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<?> update(@PathVariable(value = "id") Integer id, @RequestBody Person person) {
		try {
			CustomPageDTO<Person> response = this.personService.updateById(id, person);
			return new ResponseEntity<>(response, HttpStatus.OK);
		} catch (RuntimeException error) {
			return new ResponseEntity<>(error.getMessage(), HttpStatus.BAD_REQUEST);
		}
	}

}
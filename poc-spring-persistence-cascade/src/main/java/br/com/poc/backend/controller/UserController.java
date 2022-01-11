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

import br.com.poc.backend.exception.InvalidPersonException;
import br.com.poc.backend.model.Person;
import br.com.poc.backend.model.User;
import br.com.poc.backend.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/users")
@Api(tags = "Users API")
public class UserController {

	@Autowired
	private UserService userService;
	
	@GetMapping("")
	@ApiOperation(value = "List all users.")
	public List<User> listAll() {
		return this.userService.listAll();
	}
	
	@GetMapping("/{id}")
	@ApiOperation(value = "List a specific user.")
	public User findById(@PathVariable("id") Integer id) {
		return this.userService.findById(id);
	}
	
	@PutMapping(path = "/{id}/update")
	@ApiOperation(value = "Update a specific user.")
	public User update(@RequestBody User user, @PathVariable("id") Integer id) {
		return this.userService.update(user, id);
	}
	
	@PostMapping(path = "/new", consumes = "application/json")
	@ApiOperation(value = "Create a new user.")
	public User save(@RequestBody User user) throws InvalidPersonException {
		return this.userService.save(user);
	}
	
	@DeleteMapping("/{id}/remove")
	@ApiOperation(value = "Remove a specific user.")
	public void remove(@PathVariable("id") Integer id) throws InvalidPersonException {
		this.userService.remove(id);
	}
}

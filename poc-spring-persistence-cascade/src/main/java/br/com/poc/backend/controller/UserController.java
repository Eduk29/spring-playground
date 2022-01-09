package br.com.poc.backend.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
	@ApiOperation(value = "List a specific person.")
	public User findById(@PathVariable("id") Integer id) {
		return this.userService.findById(id);
	}
}

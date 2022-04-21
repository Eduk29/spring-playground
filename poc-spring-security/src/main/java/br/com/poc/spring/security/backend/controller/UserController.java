package br.com.poc.spring.security.backend.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.poc.spring.security.backend.model.User;
import br.com.poc.spring.security.backend.service.UserService;

@RestController
@RequestMapping("/users")
public class UserController {

	@Autowired
	private UserService userService;
	
	@GetMapping("")
	public ResponseEntity<?> findAll() {
		List<User> users = this.userService.listAll();
		return new ResponseEntity<List<User>>(users, HttpStatus.OK);
	}
}

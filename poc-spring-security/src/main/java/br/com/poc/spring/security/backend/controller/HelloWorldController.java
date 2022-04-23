package br.com.poc.spring.security.backend.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/hello-world")
public class HelloWorldController {

	@GetMapping("")
	private ResponseEntity<String> helloWorld() {
		return new ResponseEntity<String>("Hello user!", HttpStatus.OK);
	}
	
	@GetMapping("/anonymous")
	private ResponseEntity<String> helloWorldAnonymous() {
		return new ResponseEntity<String>("Hello anonymous user, please login!!!", HttpStatus.OK);
	}
}

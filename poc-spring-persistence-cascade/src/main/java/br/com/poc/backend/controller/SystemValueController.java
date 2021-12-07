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
import br.com.poc.backend.model.SystemValue;
import br.com.poc.backend.service.PersonService;
import br.com.poc.backend.service.SystemValueService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/system-values")
@Api(tags = "System Values")
public class SystemValueController {

	@Autowired
	private SystemValueService systemValueService;
	
	@GetMapping("")
	@ApiOperation(value = "List all system values.")
	public List<SystemValue> listAll() {
		return this.systemValueService.listAll();
	}
	
	@GetMapping("/{id}")
	@ApiOperation(value = "List a specific system value.")
	public SystemValue findById(@PathVariable("id") Integer id) {
		return this.systemValueService.findById(id);
	}
	
	@PostMapping(path = "/new", consumes = "application/json")
	public SystemValue save(@RequestBody SystemValue systemValue) {
		return this.systemValueService.save(systemValue);
	}
	
	@PutMapping(path = "/{id}/update") 
	public SystemValue update(@RequestBody SystemValue systemValue, @PathVariable("id") Integer id) {
		return this.systemValueService.update(systemValue, id);
	}
	
	@DeleteMapping("/{id}/remove")
	public void remove(@PathVariable("id") Integer id) {
		this.systemValueService.remove(id);
	}
	
}

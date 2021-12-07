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

import br.com.poc.backend.model.SystemValueType;
import br.com.poc.backend.service.SystemValueTypeService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/system-value-types")
@Api(tags = "System Value Types")
public class SystemValueTypeController {

	@Autowired
	private SystemValueTypeService systemValueTypeService;
	
	@GetMapping("")
	@ApiOperation(value = "List all system values.")
	public List<SystemValueType> listAll() {
		return this.systemValueTypeService.listAll();
	}
	
	@GetMapping("/{id}")
	@ApiOperation(value = "List a specific system value.")
	public SystemValueType findById(@PathVariable("id") Integer id) {
		return this.systemValueTypeService.findById(id);
	}
	
	@PostMapping(path = "/new", consumes = "application/json")
	@ApiOperation(value = "Create a new system value type.")
	public SystemValueType save(@RequestBody SystemValueType systemValueType) {
		return this.systemValueTypeService.save(systemValueType);
	}
	
	@PutMapping(path = "/{id}/update")
	@ApiOperation(value = "Update a specific system value type.")
	public SystemValueType update(@RequestBody SystemValueType systemValueType, @PathVariable("id") Integer id) {
		return this.systemValueTypeService.update(systemValueType, id);
	}
	
	@DeleteMapping("/{id}/remove")
	@ApiOperation(value = "Remove a specific system value type.")
	public void remove(@PathVariable("id") Integer id) {
		this.systemValueTypeService.remove(id);
	}
	
}

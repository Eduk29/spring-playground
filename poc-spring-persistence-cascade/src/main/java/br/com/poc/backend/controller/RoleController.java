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
import br.com.poc.backend.exception.InvalidRoleException;
import br.com.poc.backend.model.Role;
import br.com.poc.backend.model.User;
import br.com.poc.backend.service.RoleService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/roles")
@Api(tags = "Roles API")
public class RoleController {

	@Autowired
	private RoleService roleService;
	
	@GetMapping("")
	@ApiOperation(value = "List all roles.")
	public List<Role> listAll() {
		return this.roleService.listAll();
	}
	
	@GetMapping("/{id}")
	@ApiOperation(value = "List a specific role.")
	public Role findById(@PathVariable("id") Integer id) {
		return this.roleService.findById(id);
	}
	
	@PostMapping(path = "/new", consumes = "application/json")
	@ApiOperation(value = "Create a new role.")
	public Role save(@RequestBody Role role) throws InvalidRoleException {
		return this.roleService.save(role);
	}
	
	@PutMapping(path = "/{id}/update")
	@ApiOperation(value = "Update a specific role.")
	public Role update(@RequestBody Role role, @PathVariable("id") Integer id) {
		return this.roleService.update(role, id);
	}
	
	@DeleteMapping("/{id}/remove")
	@ApiOperation(value = "Remove a specific role.")
	public void remove(@PathVariable("id") Integer id) throws InvalidRoleException {
		this.roleService.remove(id);
	}
}

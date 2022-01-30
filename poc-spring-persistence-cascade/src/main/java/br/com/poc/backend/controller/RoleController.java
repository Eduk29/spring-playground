package br.com.poc.backend.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.poc.backend.model.Role;
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
	@ApiOperation(value = "List all users.")
	public List<Role> listAll() {
		return this.roleService.listAll();
	}
	
	@GetMapping("/{id}")
	@ApiOperation(value = "List a specific user.")
	public Role findById(@PathVariable("id") Integer id) {
		return this.roleService.findById(id);
	}
}

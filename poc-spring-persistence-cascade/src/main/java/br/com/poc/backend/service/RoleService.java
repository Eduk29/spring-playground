package br.com.poc.backend.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.poc.backend.exception.InvalidPersonException;
import br.com.poc.backend.exception.InvalidRoleException;
import br.com.poc.backend.model.Person;
import br.com.poc.backend.model.Role;
import br.com.poc.backend.model.User;
import br.com.poc.backend.repository.RoleRepository;

@Service
@Transactional
public class RoleService {

	@Autowired
	private RoleRepository roleRepository;
	
	public List<Role> listAll() {
		List<Role> roles = this.roleRepository.findAll();
		return roles;
	}

	public Role findById(Integer id) {
		return this.roleRepository.findById(id);
	}
	
	public Role save(Role role) throws InvalidRoleException {
		boolean hasRoleInDB = this.validateRoleCode(role);
		if (hasRoleInDB) {
			throw new InvalidRoleException("This role has already in database, please verify!");
		}
		return this.roleRepository.save(role);
	}
	
	public Role update(Role role, Integer id) {
		if (!!this.roleRepository.existsById(id)) {
			Integer roleId = roleRepository.findById(id).getId();
			role.setId(roleId);
			roleRepository.save(role);
			
			return roleRepository.findById(id);
		}
		
		return null;
	}
	
	public void remove(Integer id) throws InvalidRoleException {
		this.roleRepository.deleteById(id);
	}
	
	private boolean validateRoleCode(Role role) {
		return this.roleRepository.existsByCode(role.getCode());
	}
}

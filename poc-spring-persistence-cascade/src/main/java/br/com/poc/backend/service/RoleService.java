package br.com.poc.backend.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
}

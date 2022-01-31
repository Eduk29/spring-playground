package br.com.poc.backend.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import br.com.poc.backend.model.Role;

public interface RoleRepository extends CrudRepository<Role, Long> {

	List<Role> findAll();
	Role findById(Integer id);
	Boolean existsById(Integer id);
	Boolean existsByCode(String code);
	void deleteById(Integer id);
}

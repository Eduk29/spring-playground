package br.com.poc.spring.security.backend.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.poc.spring.security.backend.model.User;

public interface UserRepository extends JpaRepository<User, Long> {

	Boolean existsById(Integer id);
	Boolean existsByUsername(String username);
	List<User> findAll();
	User findById(Integer id);
	User findByUsername(String username);
	User findByUsernameLike(String username);
	void deleteById(Integer id);	
}

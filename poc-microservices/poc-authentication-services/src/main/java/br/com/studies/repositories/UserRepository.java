package br.com.studies.repositories;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RestResource;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Repository;

import br.com.studies.models.User;
import io.swagger.v3.oas.annotations.Hidden;

@Repository
@Hidden
public interface UserRepository extends CrudRepository<User, Integer> {
	void deleteById(@NonNull Integer id);

	boolean existsById(@NonNull Integer id);

	Page<User> findAll(Pageable pageable);
	
	Page<User> findById(Integer id, Pageable pageable);
	
	@RestResource(exported = false)
	Optional<User> findByUsername(String username);
	
	Page<User> findByUsername(String username, Pageable pageable);
}

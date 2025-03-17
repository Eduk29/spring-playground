package br.com.studies.repositories;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import br.com.studies.models.User;
import io.swagger.v3.oas.annotations.Hidden;

@Repository
@Hidden
public interface AuthenticationRepository extends CrudRepository<User, Integer> {
	Optional<User> findByUsername(String username);
}

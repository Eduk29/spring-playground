package br.com.studies.repositories;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Repository;

import br.com.studies.models.Role;
import io.swagger.v3.oas.annotations.Hidden;

@Repository
@Hidden
public interface RoleRepository extends CrudRepository<Role, Long> {
	Optional<Role> findByName(String name);

	boolean existsById(@NonNull Integer id);
}

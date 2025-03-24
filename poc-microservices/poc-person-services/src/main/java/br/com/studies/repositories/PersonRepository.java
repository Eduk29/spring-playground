package br.com.studies.repositories;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import br.com.studies.models.Person;
import io.swagger.v3.oas.annotations.Hidden;

@Repository
@Hidden
public interface PersonRepository extends CrudRepository<Person, Integer> {
	Page<Person> findByCpf(String cpf, Pageable pageable);
	
	Optional<Person> findByCpf(String cpf);

	Page<Person> findById(Integer id, Pageable pageable);
	
	Page<Person> findByNameContainsIgnoreCase(Pageable pageble, String name);

	Page<Person> findAll(Pageable pageable);
}
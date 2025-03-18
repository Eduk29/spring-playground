package br.com.studies.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import br.com.studies.models.Person;

@Repository
public interface PersonRepository extends CrudRepository<Person, Integer> {
	Page<Person> findByCpf(String cpf, Pageable pageable);
	
	Optional<Person> findByCpf(String cpf);

	Page<Person> findById(Integer id, Pageable pageable);
	
	Page<Person> findByNameContainsIgnoreCase(Pageable pageble, String name);

	List<Person> findAll();

	Page<Person> findAll(Pageable pageable);
}
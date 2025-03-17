package br.com.studies.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import br.com.studies.models.Person;

@Repository
public interface PersonRepository extends CrudRepository<Person, Long> {
	Optional<Person> findByCpf(String cpf);
    Optional<Person> findByUserId(String userId);
    Optional<Person> findByNameContainingIgnoreCase(String name);
    List<Person> findAll();
}
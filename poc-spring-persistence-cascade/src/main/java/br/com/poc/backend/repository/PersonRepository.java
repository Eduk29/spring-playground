package br.com.poc.backend.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import br.com.poc.backend.model.Person;

@Repository
public interface PersonRepository extends CrudRepository<Person, Long> {

	List<Person> findAll();
	Boolean existsById(Integer id);
	Person findById(Integer id);
	Person findByName(String name);
	<S extends Person> S save(Person person);
	void deleteById(Integer id);
}

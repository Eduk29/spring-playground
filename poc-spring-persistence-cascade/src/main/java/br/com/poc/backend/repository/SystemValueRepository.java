package br.com.poc.backend.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import br.com.poc.backend.model.SystemValue;

@Repository
public interface SystemValueRepository extends CrudRepository<SystemValue, Long> {

	List<SystemValue> findAll();
	SystemValue findById(Integer id);
	SystemValue findByCode(String code);
	
}

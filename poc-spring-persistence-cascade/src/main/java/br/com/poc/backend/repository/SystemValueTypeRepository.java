package br.com.poc.backend.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import br.com.poc.backend.model.SystemValueType;

@Repository
public interface SystemValueTypeRepository extends CrudRepository<SystemValueType, Long> {

	List<SystemValueType> findAll();
	SystemValueType findById(Integer id);
	SystemValueType findByCode(String code);
	void deleteById(Integer id);
}

package br.com.poc.backend.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.poc.backend.model.SystemValue;
import br.com.poc.backend.repository.SystemValueRepository;

@Service
@Transactional
public class SystemValueService {

	@Autowired
	private SystemValueRepository systemValueRepository;
	
	public List<SystemValue> listAll() {
		return this.systemValueRepository.findAll();
	}
	
	public SystemValue findById(Integer id) {
		return this.systemValueRepository.findById(id);
	}
	
	public SystemValue findByCode(String code) {
		return this.systemValueRepository.findByCode(code);
	}
	
	public SystemValue save (SystemValue systemValue) {
		return this.systemValueRepository.save(systemValue);
	}
	
	public SystemValue update (SystemValue systemValue, Integer id) {
		systemValue.setId(id);
		return this.systemValueRepository.save(systemValue);
	}
	
	public void remove (Integer id) {
		this.systemValueRepository.deleteById(id);
	}
}

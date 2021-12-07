package br.com.poc.backend.service;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.poc.backend.model.SystemValue;
import br.com.poc.backend.model.SystemValueType;
import br.com.poc.backend.repository.SystemValueTypeRepository;

@Service
@Transactional
public class SystemValueTypeService {

	@Autowired
	private SystemValueTypeRepository systemValueTypeRepository;
	
	public List<SystemValueType> listAll() {
		List<SystemValueType> systemValueTypeList = this.systemValueTypeRepository.findAll();
		this.prepareSystemValueTypeForRemoveContacts(systemValueTypeList);
		return systemValueTypeList;
	}
	
	public SystemValueType findById(Integer id) {
		SystemValueType systemValueType = this.systemValueTypeRepository.findById(id);
		List<SystemValueType> systemValueTypeList = new ArrayList<>();
		systemValueTypeList.add(systemValueType);
		this.prepareSystemValueTypeForRemoveContacts(systemValueTypeList);
		return systemValueTypeList.get(0);
	}
	
	public SystemValueType findByCode(String code) {
		return this.systemValueTypeRepository.findByCode(code);
	}
	
	public SystemValueType save (SystemValueType systemValueType) {
		return this.systemValueTypeRepository.save(systemValueType);
	}
	
	public SystemValueType update (SystemValueType systemValueType, Integer id) {
		systemValueType.setId(id);
		return this.systemValueTypeRepository.save(systemValueType);
	}
	
	public void remove (Integer id) {
		this.systemValueTypeRepository.deleteById(id);
	}
	
	private void prepareSystemValueTypeForRemoveContacts(List<SystemValueType> systemValueTypeList) {
		for (int counter = 0; counter < systemValueTypeList.size(); counter++) {
			List<SystemValue> systemValueList = systemValueTypeList.get(counter).getSystemValues();
			this.removeContactsFromSystemValue(systemValueList);
		}
	}
	
	private void removeContactsFromSystemValue(List<SystemValue> systemValueList) {
		for(int counter = 0; counter < systemValueList.size(); counter++) {
			systemValueList.get(counter).setContacts(null);
		}
	}
}

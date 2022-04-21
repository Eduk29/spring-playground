package br.com.poc.spring.security.backend.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.poc.spring.security.backend.model.User;
import br.com.poc.spring.security.backend.repository.UserRepository;

@Service
@Transactional
public class UserService {

	@Autowired
	private UserRepository userRepository;
	
	public List<User> listAll() {
		return this.userRepository.findAll();
	}
}

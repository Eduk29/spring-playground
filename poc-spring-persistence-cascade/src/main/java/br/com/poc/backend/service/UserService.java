package br.com.poc.backend.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.poc.backend.model.User;
import br.com.poc.backend.repository.UserRepository;

@Service
@Transactional
public class UserService {

	@Autowired
	private UserRepository userRepository;
	
	public List<User> listAll() {
		return this.userRepository.findAll();
	}
	
	public User findById(Integer id) {
		return this.userRepository.findById(id);
	}
}

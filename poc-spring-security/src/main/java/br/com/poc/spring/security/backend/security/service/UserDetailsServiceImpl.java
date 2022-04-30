package br.com.poc.spring.security.backend.security.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import br.com.poc.spring.security.backend.model.User;
import br.com.poc.spring.security.backend.security.model.UserDetailsImpl;
import br.com.poc.spring.security.backend.service.UserService;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

	@Autowired
	private UserService userService;

	@Override
	public UserDetailsImpl loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = this.userService.listByUsername(username);
		
		if (user == null) {
            throw new UsernameNotFoundException("Usuário não encontrado!");
        }
		
		return new UserDetailsImpl(user);
	}
}

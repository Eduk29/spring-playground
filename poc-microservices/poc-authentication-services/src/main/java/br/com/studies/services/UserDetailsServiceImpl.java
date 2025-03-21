package br.com.studies.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import br.com.studies.dtos.UserInformationResponseDTO;
import br.com.studies.models.User;
import br.com.studies.models.UserDetailsImpl;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

	@Autowired
	private UserService userService;

	@Override
	public UserDetailsImpl loadUserByUsername(String username) throws UsernameNotFoundException {
		if (this.userService.userExistInDB(username)) {
			User user  = this.userService.findUsername(username);
			UserDetailsImpl userDetails = new UserDetailsImpl(user);
			return userDetails;			
		} else {			
			throw new UsernameNotFoundException("User not found!");
		}
	}
}

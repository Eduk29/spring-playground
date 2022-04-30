package br.com.poc.spring.security.backend.security.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import br.com.poc.spring.security.backend.model.Role;
import br.com.poc.spring.security.backend.security.model.LoginCredentials;
import br.com.poc.spring.security.backend.security.model.TokenCredentials;
import br.com.poc.spring.security.backend.security.model.UserDetailsImpl;
import br.com.poc.spring.security.backend.security.service.UserDetailsServiceImpl;
import br.com.poc.spring.security.backend.security.utils.JWTTokenUtils;

@RestController
@RequestMapping("/authentication")
public class AuthenticationController {

	@Autowired
	private AuthenticationManager authenticationManager;
	
	@Autowired
	private UserDetailsServiceImpl userDetailsServiceImpl;
	
	@Autowired
	private JWTTokenUtils jwtTokenUtils;

	@RequestMapping(value = "sign-in", method = RequestMethod.POST)
	public ResponseEntity<?> authenticate(@RequestBody LoginCredentials loginCredentials) throws Exception {
		try {
			this.authenticate(loginCredentials.getUsername(), loginCredentials.getPassword());
			
			UserDetailsImpl userDetails = userDetailsServiceImpl.loadUserByUsername(loginCredentials.getUsername());
			TokenCredentials tokenCredentials = this.createJWTTokenResponse(userDetails);
			
			return ResponseEntity.ok(tokenCredentials);
		} catch (UsernameNotFoundException e) {
			throw new UsernameNotFoundException(e.getMessage());
		}
	}
	
	private void authenticate (String username, String password) throws Exception {
		try {
			UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(username, password);
			authenticationManager.authenticate(usernamePasswordAuthenticationToken);
		} catch (BadCredentialsException e) {
			throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Bad Credentials, please verify!");
		}
	}
	
	private TokenCredentials createJWTTokenResponse (UserDetailsImpl userDetails) {
		String username = userDetails.getUsername();
		List<Role> roles = userDetails.getRoles();
		String token = this.jwtTokenUtils.generateToken(userDetails);
		
		return new TokenCredentials(token, username, roles);
	}
}

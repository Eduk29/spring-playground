package br.com.studies.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import br.com.studies.dtos.LoginRequestDTO;
import br.com.studies.dtos.LoginResponseDTO;
import br.com.studies.dtos.UserInformationResponseDTO;
import br.com.studies.models.User;
import br.com.studies.models.UserDetailsImpl;
import br.com.studies.repositories.RoleRepository;
import br.com.studies.repositories.UserRepository;
import br.com.studies.utils.JwtTokenUtils;

@Service
public class AuthenticationService {

	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private JwtTokenUtils jwtTokenUtils;

	public AuthenticationService(UserRepository userRepository, AuthenticationManager authenticationManager,
			PasswordEncoder passwordEncoder, RoleRepository roleRepository) {
		this.authenticationManager = authenticationManager;
		this.userRepository = userRepository;
	}

	public LoginResponseDTO authenticate(LoginRequestDTO loginRequestDTO) {
		authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(loginRequestDTO.getUsername(), loginRequestDTO.getPassword()));
		User authenticatedUser = userRepository.findByUsername(loginRequestDTO.getUsername()).orElseThrow();
		return new LoginResponseDTO(authenticatedUser, jwtTokenUtils);
	}

	public UserInformationResponseDTO whoIAm() {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
		User user = this.userRepository.findByUsername(userDetails.getUsername()).orElseThrow();
		UserInformationResponseDTO userInformationConverted = new UserInformationResponseDTO(user);

		return userInformationConverted;
	}
	
	public LoginResponseDTO renewToken(String token) {
		String username = jwtTokenUtils.extractUsername(token);
		User user = this.userRepository.findByUsername(username).orElseThrow();
		return new LoginResponseDTO(user, jwtTokenUtils);
	}
}

package br.com.studies.services;

import java.util.Collections;
import java.util.NoSuchElementException;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import br.com.studies.dtos.CustomPageDTO;
import br.com.studies.dtos.RegisterUserRequestDTO;
import br.com.studies.dtos.UserInformationResponseDTO;
import br.com.studies.models.Role;
import br.com.studies.models.User;
import br.com.studies.repositories.RoleRepository;
import br.com.studies.repositories.UserRepository;
import br.com.studies.utils.PaginationUtils;

@Service
public class UserService {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private RoleRepository roleRepository;

	@Autowired
	private final PasswordEncoder passwordEncoder;

	public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder, RoleRepository roleRepository) {
		this.userRepository = userRepository;
		this.passwordEncoder = passwordEncoder;
		this.roleRepository = roleRepository;
	}

	public void deleteById(Integer userId) {
		this.userIDExistsInDB(userId);
		this.userRepository.deleteById(userId);
	}

	public CustomPageDTO<UserInformationResponseDTO> findAll(Integer pageNumber, Integer pageSize) {
	    if (!PaginationUtils.validatePageNumber(pageNumber)) {
	        pageNumber = PaginationUtils.setDefaultPageNumber();
	    }

	    if (!PaginationUtils.validatePageSize(pageSize)) {
	        pageSize = PaginationUtils.setDefaultPageSize();
	    }

	    Pageable pageable = PageRequest.of(pageNumber, pageSize);
	    Page<User> page = this.userRepository.findAll(pageable);
	    Page<UserInformationResponseDTO> responsePage = page.map(UserInformationResponseDTO::new);

	    return new CustomPageDTO<UserInformationResponseDTO>(responsePage);
	}

	public CustomPageDTO<UserInformationResponseDTO> findById(Integer userId) {
		this.userIDExistsInDB(userId);
		Pageable pageable = PageRequest.of(0, 1);
		Page<User> page = this.userRepository.findById(userId, pageable);
		Page<UserInformationResponseDTO> responsePage = page.map(UserInformationResponseDTO::new);
		CustomPageDTO<UserInformationResponseDTO> response = new CustomPageDTO<UserInformationResponseDTO>(responsePage);
		return response;
	}
	
	public CustomPageDTO<UserInformationResponseDTO> findByUsername(String username) {
	    Pageable pageable = PageRequest.of(0, 1);
	    Page<User> page = this.userRepository.findByUsername(username, pageable);
	    Page<UserInformationResponseDTO> responsePage = page.map(UserInformationResponseDTO::new);

	    return new CustomPageDTO<>(responsePage);
	}
	
	public User findUsername(String username) {
	    User user = this.userRepository.findByUsername(username).orElseThrow();

	    return user;
	}

	public CustomPageDTO<UserInformationResponseDTO> promote(Integer userId) throws Exception {
	    User user = userRepository.findById(userId)
	            .orElseThrow(() -> new NoSuchElementException("User not found"));

	    Role adminRole = roleRepository.findByName("ADMIN")
	            .orElseThrow(() -> new NoSuchElementException("Role ADMIN not found"));

	    if (user.getRoles().stream().anyMatch(role -> role.getName().equals(adminRole.getName()))) {
	        throw new Exception("User is already a system administrator");
	    }

	    user.getRoles().add(adminRole);
	    User updatedUser = userRepository.save(user);
	    UserInformationResponseDTO response = new UserInformationResponseDTO(updatedUser);

	    return new CustomPageDTO<UserInformationResponseDTO>(response);
	}

	public CustomPageDTO<UserInformationResponseDTO> register(RegisterUserRequestDTO registerUserRequestDTO) throws RuntimeException {
		Set<String> roleNames = registerUserRequestDTO.getRoles();

		if (roleNames == null || roleNames.isEmpty()) {
	        roleNames = Collections.singleton("USER");
	    }

		Set<Role> roles = roleNames.stream()
				.map(rolename -> this.roleRepository.findByName(rolename).orElseThrow(() -> new NoSuchElementException("Role " + rolename + "not found: ")))
				.collect(Collectors.toSet());

		this.isUsernameRegistered(registerUserRequestDTO.getUsername());

		User user = this.constructUser(registerUserRequestDTO, roles);
		User userRegistered = this.userRepository.save(user);
		
		return this.findById(userRegistered.getId());
	}

	public CustomPageDTO<UserInformationResponseDTO> updateById(RegisterUserRequestDTO registerUserRequestDTO, Integer userId) {
	    this.userIDExistsInDB(userId);

	    User existingUser = userRepository.findById(userId)
	            .orElseThrow(() -> new NoSuchElementException("User not found"));

	    Set<Role> existingRoles = existingUser.getRoles();
	    User updatedUser = this.constructUser(registerUserRequestDTO, existingRoles);
	    updatedUser.setId(userId);

	    User savedUser = userRepository.save(updatedUser);
	    
	    return this.findById(savedUser.getId());
	}
	
	public Boolean userExistInDB(String username) {
		return userRepository.findByUsername(username).isPresent();
	}

	private User constructUser(RegisterUserRequestDTO registerUserRequestDTO, Set<Role> roles) {
		User user = new User();
		String encodedPassword = this.passwordEncoder.encode(registerUserRequestDTO.getPassword());
		user.setPassword(encodedPassword);
		user.setPersonId(registerUserRequestDTO.getPersonId());
		user.setRoles(roles);
		user.setUsername(registerUserRequestDTO.getUsername());
		return user;
	}

	private void isUsernameRegistered(String username) throws RuntimeException {
		Boolean isUsernameRegistered = this.userRepository.findByUsername(username).isPresent();
		if (isUsernameRegistered) {
			throw new RuntimeException("Username already exists");
		}
	}

	private void userIDExistsInDB(Integer id) throws RuntimeException {
		boolean existsUser = this.userRepository.existsById(id);
		if (!existsUser) {
			throw new RuntimeException("User not found");
		}
	}
}

package br.com.poc.backend.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.poc.backend.exception.InvalidPersonException;
import br.com.poc.backend.model.Person;
import br.com.poc.backend.model.User;
import br.com.poc.backend.repository.UserRepository;

@Service
@Transactional
public class UserService {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private PersonService personService;

	public List<User> listAll() {
		return this.userRepository.findAll();
	}

	public User findById(Integer id) {
		return this.userRepository.findById(id);
	}
	
	public User update(User user, Integer id) {
		if (!!this.userRepository.existsById(id)) {
			Integer userId = userRepository.findById(id).getId();
			user.setId(userId);
			userRepository.save(user);
			
			return userRepository.findById(id);
		}
		
		return null;
	}

	public User save(User user) throws InvalidPersonException {
		try {
			this.validateReceivedUser(user);
			this.personReceivedIsAlreadyInDB(user);
			this.personReceivedAlreadyHasAnUserInDB(user);
			
			if (canCreateNewUser(user)) {
				if (this.needToFillUser(user)) {
					user = this.fillUserWithPerson(user);
				}
				this.userRepository.save(user);
				return this.findById(user.getId());
			}
			
			return null;

		} catch (InvalidPersonException error) {
			throw new InvalidPersonException(error.getMessage());
		}
	}
	
	public void remove(Integer id) throws InvalidPersonException {
		User userToRemove = this.findById(id);
		userToRemove.setPerson(null);
		this.userRepository.save(userToRemove);
		this.userRepository.deleteById(id);
	}
	
	private boolean canCreateNewUser(User user) throws InvalidPersonException {
		return !!this.validateReceivedUser(user) && (
				!!this.personReceivedIsAlreadyInDB(user) &&
				!this.personReceivedAlreadyHasAnUserInDB(user) || (
				!this.personReceivedIsAlreadyInDB(user) &&
				!!this.personService.validatePersonData(user.getPerson())
				));
	}

	private boolean needToFillUser(User user) {
		return user.getPerson().getId() != null && (user.getPerson().getName() == null
				|| user.getPerson().getAge() == null || user.getPerson().getWeight() == null
				|| user.getPerson().getHeight() == null || user.getPerson().getContacts() == null);
	}

	private User fillUserWithPerson(User user) {
		Integer personIdToFind = user.getPerson().getId();
		Person personToAdd = this.personService.findById(personIdToFind);
		user.setPerson(personToAdd);
		return user;
	}

	private boolean validateReceivedUser(User user) throws InvalidPersonException {
		if (user.getPerson() == null) {
			throw new InvalidPersonException("Data received from person is invalid, please verify!");
		}
		return true;
	}

	private boolean personReceivedIsAlreadyInDB(User user) throws InvalidPersonException {
		Integer personId = user.getPerson().getId();
		Person personInDB = this.personService.findById(personId);
		if (personInDB == null && !this.personService.validatePersonData(user.getPerson())) {
			throw new InvalidPersonException("Person received not found in database, please verify!");
		}
		return true;
	}

	private boolean personReceivedAlreadyHasAnUserInDB(User user) throws InvalidPersonException {
		Integer personId = user.getPerson().getId();
		if (personId != null &&
				this.validateReceivedUser(user) &&
				this.personReceivedIsAlreadyInDB(user) &&
				this.personService.personHasUser(personId)) {
			throw new InvalidPersonException("Person received has already an user in database, please verify!");
		}
		return false;
	}
}

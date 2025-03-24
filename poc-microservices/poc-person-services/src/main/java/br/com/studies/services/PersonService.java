package br.com.studies.services;

import java.time.LocalDate;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import br.com.studies.dtos.CustomPageDTO;
import br.com.studies.dtos.UserDTO;
import br.com.studies.dtos.UserWithRolesDTO;
import br.com.studies.enums.MessagesEnum;
import br.com.studies.models.Person;
import br.com.studies.repositories.PersonRepository;
import br.com.studies.utils.FilterUtils;
import br.com.studies.utils.PaginationUtils;

@Service
public class PersonService {
	@Autowired
	private PersonRepository personRepository;
	
	@Autowired
	private UserService userService;

	public void deletePersonById(Integer id) {
		this.personExistsInDB(id);
		personRepository.deleteById(id);
	}

	public CustomPageDTO<Person> findAll(Integer pageNumber, Integer pageSize) {
		if (!PaginationUtils.validatePageNumber(pageNumber)) pageNumber = PaginationUtils.setDefaultPageNumber();
		if (!PaginationUtils.validatePageSize(pageSize)) pageSize = PaginationUtils.setDefaultPageSize();

		Pageable pageable = PageRequest.of(pageNumber, pageSize);
		Page<Person> page = this.personRepository.findAll(pageable);

		List<Person> enriched = page.getContent().stream()
			.map(this::buildPersonWithUser)
			.collect(Collectors.toList());

		return new CustomPageDTO<>(new PageImpl<>(enriched, pageable, page.getTotalElements()));
	}

	public CustomPageDTO<Person> findById(Integer id) {
		this.personExistsInDB(id);

		Pageable pageable = PageRequest.of(0, 1);
		Page<Person> page = this.personRepository.findById(id, pageable);

		List<Person> enriched = page.getContent().stream()
			.map(this::buildPersonWithUser)
			.collect(Collectors.toList());

		return new CustomPageDTO<>(new PageImpl<>(enriched, pageable, page.getTotalElements()));
	}

	public CustomPageDTO<Person> register(Person person) {
		this.cpfExistsInDb(person.getCpf());

		Person personToRegister = this.constructPerson(person);
		Person personRegistered = this.personRepository.save(personToRegister);

		return this.findById(personRegistered.getId());
	}
	
	public CustomPageDTO<Person> searchByQuery(Integer pageNumber, Integer pageSize, String query) {
		this.validateSearchFilter(query);
		Pageable pageable = PageRequest.of(pageNumber, pageSize);
		Page<Person> page;

		switch (FilterUtils.getModeSearch(query)) {
			case "name":
				page = this.personRepository.findByNameContainsIgnoreCase(pageable, FilterUtils.getParameterSearch(query));
				break;
			case "cpf":
				page = this.personRepository.findByCpf(FilterUtils.getParameterSearch(query), pageable);
				break;
			default:
				page = new PageImpl<>(Collections.emptyList(), pageable, 0);
				break;
		}

		List<Person> enriched = page.getContent().stream()
			.map(this::buildPersonWithUser)
			.collect(Collectors.toList());

		return new CustomPageDTO<>(new PageImpl<>(enriched, pageable, page.getTotalElements()));
	}

	public CustomPageDTO<Person> updateById(Integer id, Person personToUpdate) {
	    this.personExistsInDB(id);
	    this.cpfExistsInDBWithAnotherPerson(id, personToUpdate);
	    
	    personToUpdate.setId(id);
	    Person personUpdated = this.personRepository.save(personToUpdate);
	    
	    return this.findById(personUpdated.getId());
	}
	
	private Person buildPersonWithUser(Person person) {
		Person enriched = new Person(person);
		try {
			UserWithRolesDTO user = this.userService.getUserById(person.getUserId());
			if (user != null) {
				UserDTO userDTO = new UserDTO(
					user.getId(),
					user.getUsername(),
					user.getCreatedAt(),
					user.getUpdatedAt()
				);
				enriched.setUser(userDTO);
				enriched.setRoles(user.getRoles());
			}
		} catch (Exception e) {
			System.out.printf("⚠️ Erro ao buscar usuário para pessoa ID %d: %s%n", person.getId(), e.getMessage());
		}
		return enriched;
	}

	private Person constructPerson(Person personToAdd) {
		Person person = new Person(personToAdd);
		this.setCreatedAtValue(person);
		this.setUpdatedAtValue(person);
		return person;
	}

	private void cpfExistsInDb(String cpf) throws RuntimeException {
		boolean cpfExistsInDb = this.personRepository.findByCpf(cpf).isPresent();
				
		if (cpfExistsInDb) {
			throw new RuntimeException(MessagesEnum.CPF_MUST_BE_UNIQUE.getDescription());
		}
	}
	
	private void cpfExistsInDBWithAnotherPerson(Integer id, Person person) {
		Optional<Person> personInDb = this.personRepository.findByCpf(person.getCpf());

	    if (personInDb.isPresent() && !personInDb.get().getId().equals(id)) {
	        throw new RuntimeException(MessagesEnum.CPF_MUST_BE_UNIQUE.getDescription());
	    }
		
	}

	private void personExistsInDB(Integer id) throws RuntimeException {
		boolean personExists = this.personRepository.existsById(id);
		if (!personExists) {
			throw new RuntimeException(MessagesEnum.USER_NOT_FOUND.getDescription());
		}
	}

	private void setCreatedAtValue(Person person) {
		person.setCreatedAt(LocalDate.now());
	}

	private void setUpdatedAtValue(Person person) {
		person.setUpdatedAt(LocalDate.now());
	}

	private void validateSearchFilter(String filter) throws RuntimeException {
		if (filter == null) {
			throw new RuntimeException(MessagesEnum.FILTER_MUST_BE_PROVIDED.getDescription());
		}
	}
}

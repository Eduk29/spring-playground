package br.com.studies.models;

import java.time.LocalDate;
import java.util.List;

import br.com.studies.dtos.UserDTO;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;
import jakarta.persistence.UniqueConstraint;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Table(name = "persons", uniqueConstraints = {@UniqueConstraint(columnNames = "cpf"), @UniqueConstraint(columnNames = "userIdentifier")})
@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Person {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    
    @Column(nullable = false)
    private String name;
    
    @Column(nullable = false)
    private String email;
    
    @Column(nullable = false)
    private Integer age;
    
    @Column(nullable = false)
    private String birthday;
    
    @Column(nullable = false, unique = true, length = 11)
    private String cpf;
    
    @Column(nullable = false, unique = true, name="user_id")
    private String userId;
    
    @Column(nullable = true, name="created_at")
    private LocalDate createdAt;
    
    @Column(nullable = true, name="updated_at")
    private LocalDate updatedAt;
    
    @Transient
    private List<String> roles;
    
    @Transient
    private UserDTO user;
    
    public Person(Person person) {
    	this.id = person.id;
    	this.name = person.name;
    	this.email = person.email;
    	this.birthday = person.birthday;
    	this.age = person.age;
    	this.cpf = person.cpf;
    	this.userId = person.userId;
    	this.createdAt = person.createdAt;
    	this.updatedAt = person.updatedAt;
    	this.roles = person.roles;
    	this.user = person.user;
    }

}


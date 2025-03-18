package br.com.studies.models;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDate;
import java.util.List;

import br.com.studies.dtos.User;

@Entity
@Table(name = "persons", uniqueConstraints = {@UniqueConstraint(columnNames = "cpf"), @UniqueConstraint(columnNames = "userIdentifier")})
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
    private Integer age;
    
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
    private User user;
    
    public Person(Person person) {
    	this.id = person.id;
    	this.name = person.name;
    	this.age = person.age;
    	this.cpf = person.cpf;
    	this.userId = person.userId;
    	this.createdAt = person.createdAt;
    	this.updatedAt = person.updatedAt;
    	this.roles = person.roles;
    	this.user = person.user;
    }

}


package br.com.studies.models;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDate;
import java.util.List;

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
    private Long id;
    
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

}


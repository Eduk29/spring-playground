package br.com.studies.dtos;

import java.time.LocalDate;
import java.util.List;

import org.springframework.security.core.Transient;

import br.com.studies.models.User;
import lombok.*;

@Transient
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PersonDTO {

    private Long id;
    private String name;
    private Integer age;
    private String cpf;
    private String userId;
    private LocalDate createdAt;
    private LocalDate updatedAt;
    private List<String> roles;
    private User user;
}

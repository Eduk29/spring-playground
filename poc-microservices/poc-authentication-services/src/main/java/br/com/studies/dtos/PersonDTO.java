package br.com.studies.dtos;

import org.springframework.security.core.Transient;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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
    private String createdAt;
    private String updatedAt;
}

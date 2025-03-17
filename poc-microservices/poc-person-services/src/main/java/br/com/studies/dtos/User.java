package br.com.studies.dtos;

import org.springframework.security.core.Transient;

import lombok.*;

@Transient
@Getter
@Setter
@AllArgsConstructor
@Builder
public class User {

	private Long id;
	private String username;
	private String password;
}

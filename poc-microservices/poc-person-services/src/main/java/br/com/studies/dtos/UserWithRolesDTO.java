package br.com.studies.dtos;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserWithRolesDTO {
	private Long id;
	private String username;
	private String createdAt;
	private String updatedAt;
	private List<String> roles;
}
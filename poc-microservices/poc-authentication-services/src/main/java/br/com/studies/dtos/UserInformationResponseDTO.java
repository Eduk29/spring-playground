package br.com.studies.dtos;

import java.util.Set;

import br.com.studies.models.User;
import br.com.studies.utils.RoleUtils;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserInformationResponseDTO {
	private Integer id;
	private String username;
	private String personId;
	private Set<String> roles;
	private String createdAt;
	private String updatedAt;
	
	public UserInformationResponseDTO(User user) {
		this.id = user.getId();
		this.username = user.getUsername();
        this.personId = user.getPersonId();
        this.roles = RoleUtils.convertRoleResponse(user.getRoles());
        this.createdAt = user.getCreatedAt().toString();
        this.updatedAt = user.getUpdatedAt().toString();
    }
}

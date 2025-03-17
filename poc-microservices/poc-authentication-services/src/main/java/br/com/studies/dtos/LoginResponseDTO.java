package br.com.studies.dtos;

import java.util.Set;

import br.com.studies.models.User;
import br.com.studies.models.UserDetailsImpl;
import br.com.studies.utils.JwtTokenUtils;
import br.com.studies.utils.RoleUtils;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class LoginResponseDTO {

	private String username;
	private String token;
	private long expiresIn;
	private Set<String> roles;
	
	public LoginResponseDTO(User user, JwtTokenUtils jwtTokenUtils) {
		this.username = user.getUsername();
        this.roles = RoleUtils.convertRoleResponse(user.getRoles());
        this.token = jwtTokenUtils.generateToken(new UserDetailsImpl(user));
        this.expiresIn = jwtTokenUtils.getExpirationTime();
    }
}

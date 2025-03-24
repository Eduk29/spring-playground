package br.com.studies.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import br.com.studies.dtos.CustomPageDTO;
import br.com.studies.dtos.UserWithRolesDTO;
import br.com.studies.utils.JwtTokenUtils;
import io.swagger.v3.oas.annotations.Hidden;

@Service
@Hidden
public class UserService {

	@Value("${authentication.service.url}")
	private String authenticationServiceUrl;

	@Autowired
	private RestTemplate restTemplate;

	@Autowired
	private JwtTokenUtils jwtTokenUtils;

	public UserWithRolesDTO getUserById(String userId) {
		String url = authenticationServiceUrl + "/users/" + userId + "?$enrichPerson=false";

		HttpHeaders headers = new HttpHeaders();
		headers.set("Authorization", "Bearer " + jwtTokenUtils.getAuthToken());
		headers.setContentType(MediaType.APPLICATION_JSON);
		HttpEntity<Void> entity = new HttpEntity<>(headers);

		try {
			ResponseEntity<CustomPageDTO<UserWithRolesDTO>> response = restTemplate.exchange(
					url,
					HttpMethod.GET,
					entity,
					new ParameterizedTypeReference<CustomPageDTO<UserWithRolesDTO>>() {}
			);

			if (response.getBody() == null || response.getBody().getContent().isEmpty()) return null;

			return response.getBody().getContent().get(0);

		} catch (Exception e) {
			System.out.println("⚠️ Erro ao buscar usuário por ID: " + userId + " → " + e.getMessage());
			return null;
		}
	}
}

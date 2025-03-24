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
import br.com.studies.dtos.PersonDTO;
import br.com.studies.utils.JwtTokenUtils;

@Service
public class PersonService {

	@Value("${person.service.url}")
	private String personServiceUrl;

	@Autowired
	private JwtTokenUtils jwtTokenUtils;

	private final RestTemplate restTemplate = new RestTemplate();

	public PersonDTO getPersonById(String personId) {
		String url = personServiceUrl + "/persons/" + personId;

		String token = jwtTokenUtils.getAuthToken();
		if (token == null) {
			throw new RuntimeException("Token is null");
		}

		HttpHeaders headers = new HttpHeaders();
		headers.set("Authorization", "Bearer " + token);
		headers.setContentType(MediaType.APPLICATION_JSON);

		HttpEntity<String> entity = new HttpEntity<>(null, headers);

		try {
			ResponseEntity<CustomPageDTO<PersonDTO>> response = restTemplate.exchange(
					url,
					HttpMethod.GET,
					entity,
					new ParameterizedTypeReference<CustomPageDTO<PersonDTO>>() {}
				);

				CustomPageDTO<PersonDTO> body = response.getBody();

				if (body == null || body.getContent() == null || body.getContent().isEmpty()) {
					System.out.println("⚠️ Pessoa não encontrada para o ID: " + personId);
					return null;
				}

				return body.getContent().get(0);

			} catch (Exception e) {
				throw new RuntimeException("❌ Erro ao buscar pessoa para ID: " + personId, e);
			}
	}
}

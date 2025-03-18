package br.com.studies.services;

import java.util.List;
import java.util.stream.Collectors;

import javax.crypto.SecretKey;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureException;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;

@Service
public class AuthenticationService {

	@Value("${auth.service.url}")
	private String authServiceUrl;
	
    @Value("${security.jwt.secret-key}")
    private String secretKey;

	private final RestTemplate restTemplate = new RestTemplate();

	public void authenticate(String token) throws SignatureException {
		try {
			String url = authServiceUrl + "/authentication/validate-token";
			HttpHeaders headers = new HttpHeaders();
			headers.set("Authorization", "Bearer " + token);
			headers.setContentType(MediaType.APPLICATION_JSON);

			HttpEntity<String> entity = new HttpEntity<>(null, headers);

			ResponseEntity<Void> response = restTemplate.exchange(url, HttpMethod.GET, entity, Void.class);

			if (!response.getStatusCode().is2xxSuccessful()) {
				throw new RuntimeException("Invalid or expired token 123.");
			}

			Claims claims = extractClaims(token);

			List<String> roles = (List<String>) claims.get("roles");
			
			List<SimpleGrantedAuthority> authorities = roles.stream()
					.map(role -> role.startsWith("ROLE_") ? role : role).map(SimpleGrantedAuthority::new)
					.collect(Collectors.toList());

			UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(
					claims.getSubject(), null, authorities);
			SecurityContextHolder.getContext().setAuthentication(authentication);
			
			System.out.println("Usuário autenticado: " + claims.getSubject());
			System.out.println("Roles do JWT: " + claims.get("roles"));
			System.out.println("Roles no SecurityContext: " + SecurityContextHolder.getContext().getAuthentication().getAuthorities());

		} catch (Exception e) {
			SecurityContextHolder.clearContext();
			throw new RuntimeException("Invalid or expired token 456.");
		}
	}

	private Claims extractClaims(String token) {
	    SecretKey key = getSignInKey();

	    return Jwts.parserBuilder()
	            .setSigningKey(key)
	            .build()
	            .parseClaimsJws(token.replace("Bearer ", ""))
	            .getBody();
	}

	private SecretKey getSignInKey() {
	    byte[] keyBytes;
	    
	    if (secretKey.length() < 32) {
	        throw new IllegalArgumentException("Secret key must be at least 32 bytes for HS256");
	    }

	    try {
	        keyBytes = Decoders.BASE64.decode(secretKey);
	    } catch (IllegalArgumentException e) {
	        keyBytes = secretKey.getBytes();
	    }

	    return Keys.hmacShaKeyFor(keyBytes);
	}
}

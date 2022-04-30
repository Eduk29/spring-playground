package br.com.poc.spring.security.backend.security.utils;

import java.io.Serializable;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import br.com.poc.spring.security.backend.security.model.UserDetailsImpl;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Component
public class JWTTokenUtils implements Serializable {

	private static final long serialVersionUID = 8556809946930026077L;
	private static final long JWT_TOKEN_VALIDITY = 5 * 60 * 60;
	
	@Value("${jwt.secret}")
	private String secret;
	
	public String generateToken(UserDetailsImpl userDetails) {
		Map<String, Object> claims = new HashMap<>();
		return doGenerateToken(claims, userDetails.getUsername());
	}
	
	public String getUsernameFromToken(String token) {
		return getClaimFromToken(token, Claims::getSubject);
	}
	
	public Boolean validateToken(String token, UserDetailsImpl userDetails) {
		final String username = getUsernameFromToken(token);
		return username.equals(userDetails.getUsername()) && !isTokenExpired(token);
	}
	
	private String doGenerateToken(Map<String, Object> claims, String subject) {
		Long nowDateInMillis = System.currentTimeMillis();
		Date nowDate = new Date(nowDateInMillis);
		Date experationDate = new Date(nowDateInMillis + JWT_TOKEN_VALIDITY * 1000);
		
		return Jwts.builder()
					.setClaims(claims)
					.setSubject(subject)
					.setIssuedAt(nowDate)
					.setExpiration(experationDate)
					.signWith(SignatureAlgorithm.HS256, secret).compact();
					
	}
	
	private Claims getAllClaimsFromToken(String token) {
		return Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody();
	}
	
	private <T> T getClaimFromToken(String token, Function<Claims, T> claimsResolver) {
		final Claims claims = getAllClaimsFromToken(token);
		return claimsResolver.apply(claims);
	}
	
	private Date getExpirationDateFromtoken(String token) {
		return getClaimFromToken(token, Claims::getExpiration);
	}
	
	private Boolean isTokenExpired(String token) {
		final Date expiration = getExpirationDateFromtoken(token);
		return expiration.before(new Date());
	}
	
}
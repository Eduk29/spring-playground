package br.com.studies.utils;

import br.com.studies.models.Role;
import br.com.studies.models.User;
import br.com.studies.models.UserDetailsImpl;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import jakarta.servlet.http.HttpServletRequest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.http.HttpHeaders;
import org.springframework.security.core.userdetails.UserDetails;


import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.security.Key;
import java.util.Base64;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class JwtTokenUtilsTest {
	private JwtTokenUtils jwtTokenUtils;
    private HttpServletRequest request;

    private final String secretKey = "mysupersecuresecretkeythatshardtoguess1234567890!!";
    private final long expirationTime = 1000 * 60 * 60;

    private UserDetailsImpl userDetails;

    @BeforeEach
    void setUp() {
        request = mock(HttpServletRequest.class);
        jwtTokenUtils = new JwtTokenUtils(request);

        injectPrivateField(jwtTokenUtils, "secretKey", secretKey);
        injectPrivateField(jwtTokenUtils, "jwtExpiration", expirationTime);

        User user = new User();
        user.setId(1);
        user.setUsername("eduardo");
        user.setPassword("encoded-password");
        user.setPersonId("123");
        user.setRoles(Set.of(new Role(1, "ADMIN"), new Role(2, "USER")));

        userDetails = new UserDetailsImpl(user);
    }

    @Test
    void shouldGenerateAndValidateToken() {
        String token = jwtTokenUtils.generateToken(userDetails);

        assertNotNull(token);
        assertTrue(jwtTokenUtils.isTokenValid(token, userDetails));
    }

    @Test
    void shouldExtractUsernameFromToken() {
        String token = jwtTokenUtils.generateToken(userDetails);
        String username = jwtTokenUtils.extractUsername(token);

        assertEquals("eduardo", username);
    }

    @Test
    void shouldDetectExpiredToken() throws InterruptedException {
        injectPrivateField(jwtTokenUtils, "jwtExpiration", 1L); // 1 ms

        String token = jwtTokenUtils.generateToken(userDetails);

        Thread.sleep(10);

        RuntimeException exception = assertThrows(RuntimeException.class, () -> {
            jwtTokenUtils.isTokenExpired(token);
        });

        assertEquals("Token expired", exception.getMessage());
    }
    
    @Test
    void shouldCatchExpiredJwtException_whenParsingExpiredToken() throws Exception {
        String rawSecret = "mysupersecuresecretkeythatisstrong123456789";
        String base64Secret = Base64.getEncoder().encodeToString(rawSecret.getBytes());

        jwtTokenUtils = new JwtTokenUtils(mock(HttpServletRequest.class));
        injectPrivateField(jwtTokenUtils, "secretKey", base64Secret);
        injectPrivateField(jwtTokenUtils, "jwtExpiration", expirationTime);

        Method keyMethod = JwtTokenUtils.class.getDeclaredMethod("getSignInKey");
        keyMethod.setAccessible(true);
        Key key = (Key) keyMethod.invoke(jwtTokenUtils);

        String expiredToken = Jwts.builder()
            .setSubject("expired-user")
            .setClaims(Map.of("roles", List.of("USER")))
            .setIssuedAt(new Date(System.currentTimeMillis() - 10000))
            .setExpiration(new Date(System.currentTimeMillis() - 5000))
            .signWith(key, SignatureAlgorithm.HS256)
            .compact();

        RuntimeException ex = assertThrows(RuntimeException.class, () -> {
            jwtTokenUtils.isTokenExpired(expiredToken);
        });

        assertEquals("Token expired", ex.getMessage());
        assertTrue(ex.getCause() instanceof ExpiredJwtException);
    }

    @Test
    void shouldReturnAuthTokenFromHeader() {
        when(request.getHeader(HttpHeaders.AUTHORIZATION)).thenReturn("Bearer abc.def.ghi");

        String extracted = jwtTokenUtils.getAuthToken();
        assertEquals("abc.def.ghi", extracted);
    }

    @Test
    void shouldReturnNullIfNoAuthorizationHeader() {
        when(request.getHeader(HttpHeaders.AUTHORIZATION)).thenReturn(null);

        String extracted = jwtTokenUtils.getAuthToken();
        assertNull(extracted);
    }

    @Test
    void shouldReturnExpirationTime() {
        assertEquals(expirationTime, jwtTokenUtils.getExpirationTime());
    }

    
    @Test
    void shouldThrowException_whenKeyIsLessThan32Bytes() throws Exception {
        JwtTokenUtils jwtUtils = new JwtTokenUtils(mock(HttpServletRequest.class));
        injectPrivateField(jwtUtils, "secretKey", "short-key");

        Method method = JwtTokenUtils.class.getDeclaredMethod("getSignInKey");
        method.setAccessible(true);

        Exception exception = assertThrows(InvocationTargetException.class, () -> {
            method.invoke(jwtUtils);
        });

        Throwable actual = exception.getCause();

        assertTrue(actual instanceof IllegalArgumentException);
        assertEquals("Secret key must be at least 32 bytes for HS256", actual.getMessage());
    }
    
    @Test
    void shouldThrowRuntimeException_whenExpirationIsBeforeNow() throws Exception {

        String rawSecret = "mysupersecuresecretkeythatisstrong123456789";
        String base64Secret = Base64.getEncoder().encodeToString(rawSecret.getBytes());

        jwtTokenUtils = new JwtTokenUtils(mock(HttpServletRequest.class));
        injectPrivateField(jwtTokenUtils, "secretKey", base64Secret);
        injectPrivateField(jwtTokenUtils, "jwtExpiration", expirationTime);

        Method keyMethod = JwtTokenUtils.class.getDeclaredMethod("getSignInKey");
        keyMethod.setAccessible(true);
        Key key = (Key) keyMethod.invoke(jwtTokenUtils);

        String token = Jwts.builder()
            .setSubject("custom-expired-user")
            .setIssuedAt(new Date(System.currentTimeMillis() - 10_000))
            .setExpiration(new Date(System.currentTimeMillis() - 1)) // acabou de expirar
            .signWith(key, SignatureAlgorithm.HS256)
            .compact();

        RuntimeException exception = assertThrows(RuntimeException.class, () -> {
            jwtTokenUtils.isTokenExpired(token);
        });

        assertEquals("Token expired", exception.getMessage());
    }
    
    private void injectPrivateField(Object target, String fieldName, Object value) {
    	try {
    		var field = target.getClass().getDeclaredField(fieldName);
    		field.setAccessible(true);
    		field.set(target, value);
    	} catch (Exception e) {
    		throw new RuntimeException("Erro ao injetar dependência: " + fieldName, e);
    	}
    }

    private Key getTestSignInKey() {
        byte[] keyBytes = java.util.Base64.getEncoder().encode(secretKey.getBytes());
        return Keys.hmacShaKeyFor(keyBytes);
    }
}

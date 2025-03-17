package br.com.studies.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.studies.dtos.LoginRequestDTO;
import br.com.studies.dtos.LoginResponseDTO;
import br.com.studies.dtos.UserInformationResponseDTO;
import br.com.studies.services.AuthenticationService;
import br.com.studies.utils.JwtTokenUtils;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("authentication")
@Tag(name="Authenticaion API")
public class AuthenticationController {
	
    @Value("${security.jwt.secret-key}")
    private String secretKey;

	@Autowired
	private AuthenticationService authenticationService;

	@Autowired
	private HttpServletRequest request;
	
	@Autowired
	private JwtTokenUtils jwtTokenUtils;

    @Operation(summary = "Login user", description = "Authenticate user and return JWT token.")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Successfully authenticated", 
                     content = @Content(mediaType = "application/json", schema = @Schema(implementation = LoginResponseDTO.class))),
        @ApiResponse(responseCode = "401", description = "Unauthorized")
    })
	@CrossOrigin(origins = "http://localhost:4200")
	@PostMapping("login")
	public ResponseEntity<?> login(@RequestBody LoginRequestDTO loginCredentialsDTO) {
		LoginResponseDTO response = authenticationService.authenticate(loginCredentialsDTO); 
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

    @Operation(summary = "Get user information", description = "Returns details of the authenticated user.")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "User information retrieved successfully", 
                     content = @Content(mediaType = "application/json", schema = @Schema(implementation = UserInformationResponseDTO.class))),
        @ApiResponse(responseCode = "401", description = "Unauthorized")
    })
	@CrossOrigin(origins = "http://localhost:4200")
	@GetMapping("me")
	@PreAuthorize("isAuthenticated()")
	public ResponseEntity<?> whoIAm() {
		try {
			this.isTokenValid();
			UserInformationResponseDTO currentUser = this.authenticationService.whoIAm();
			return new ResponseEntity<>(currentUser, HttpStatus.OK);
		} catch (RuntimeException error) {
			return new ResponseEntity<>(error.getMessage(), HttpStatus.UNAUTHORIZED);
		}
	}

    @Operation(summary = "Validate JWT", description = "Validates if the provided JWT is still active.")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Token is valid"),
        @ApiResponse(responseCode = "401", description = "Invalid or expired token")
    })
	@GetMapping("validate-token")
	@PreAuthorize("isAuthenticated()")
	public ResponseEntity<?> validateJWT() {
		try {
			this.isTokenValid();
			return new ResponseEntity<>(HttpStatus.OK);
		} catch (RuntimeException error) {
			return new ResponseEntity<>(error.getMessage(), HttpStatus.UNAUTHORIZED);
		}
	}
	
    @Operation(summary = "Renew JWT", description = "Generates a new JWT token if the current one is about to expire.")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "New token generated successfully", 
                     content = @Content(mediaType = "application/json", schema = @Schema(implementation = LoginResponseDTO.class))),
        @ApiResponse(responseCode = "401", description = "Invalid or expired token, user needs to login again")
    })
	@GetMapping("/renew-token")
    public ResponseEntity<?> renew() {
		try {
			String token = request.getHeader("Authorization").replace("Bearer", "");
        	LoginResponseDTO response = this.authenticationService.renewToken(token);
        	return new ResponseEntity<>(response, HttpStatus.OK);
		} catch (RuntimeException e) {
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Please login again");
		}
    }
    
	private boolean isTokenValid() {
		String token = request.getHeader("Authorization").replace("Bearer", "");
		return jwtTokenUtils.isTokenExpired(token);
	}
}

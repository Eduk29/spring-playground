package br.com.studies.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.studies.dtos.CustomPageDTO;
import br.com.studies.dtos.RegisterUserRequestDTO;
import br.com.studies.dtos.UserInformationResponseDTO;
import br.com.studies.models.User;
import br.com.studies.services.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("users")
@Tag(name="User Management API")
public class UserController {

	@Autowired
	private UserService userService;

    @Operation(summary = "Delete a user by ID", description = "Deletes a user from the system. Requires ADMIN role.")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "User deleted successfully"),
        @ApiResponse(responseCode = "400", description = "Invalid request or user not found")
    })
	@DeleteMapping("{id}")
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<?> deleteById(@PathVariable(value = "id") Integer id) {
		try {
			this.userService.deleteById(id);
			return new ResponseEntity<>(HttpStatus.OK);
		} catch (RuntimeException error) {
			return new ResponseEntity<>(error.getMessage(), HttpStatus.BAD_REQUEST);
		}
	}

    @Operation(summary = "Get all users", description = "Returns a paginated list of all users. Requires ADMIN role.")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Users retrieved successfully", 
                     content = @Content(mediaType = "application/json", schema = @Schema(implementation = CustomPageDTO.class))),
        @ApiResponse(responseCode = "400", description = "Invalid request parameters")
    })
	@GetMapping("")
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<?> findAll(
			@RequestParam(value = "$pageNumber", required = true) Integer pageNumber,
			@RequestParam(value = "$pageSize", required = true) Integer pageSize) {
		try {			
			CustomPageDTO<UserInformationResponseDTO> response = this.userService.findAll(pageNumber, pageSize);
			return new ResponseEntity<>(response, HttpStatus.OK);
		} catch (RuntimeException error) {
			return new ResponseEntity<>(error.getMessage(), HttpStatus.BAD_REQUEST); 
		}
	}
    
    @Operation(summary = "Get user by ID", description = "Retrieves a user's details by ID. Requires ADMIN role.")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "User found", 
                     content = @Content(mediaType = "application/json", schema = @Schema(implementation = User.class))),
        @ApiResponse(responseCode = "400", description = "Invalid request or user not found")
    })
	@GetMapping("{id}")
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<?> findById(@PathVariable(value = "id") Integer id) {
		try {
			CustomPageDTO<UserInformationResponseDTO> response = this.userService.findById(id);
			return new ResponseEntity<>(response, HttpStatus.OK);
		} catch (RuntimeException error) {
			return new ResponseEntity<>(error.getMessage(), HttpStatus.BAD_REQUEST);
		}
	}

    @Operation(summary = "Promote user to admin", description = "Promotes a user to a higher role. Requires ADMIN role.")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "User promoted successfully"),
        @ApiResponse(responseCode = "400", description = "Invalid request or user not found")
    })
	@GetMapping("{id}/promote")
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<?> promote(@PathVariable(value = "id") Integer id) {
		try {
			CustomPageDTO<UserInformationResponseDTO> response = this.userService.promote(id);
			return new ResponseEntity<>(response, HttpStatus.OK);
		} catch (Exception error) {
			return new ResponseEntity<>(error.getMessage(), HttpStatus.BAD_REQUEST);
		}
	}

    @Operation(summary = "Register new user", description = "Registers a new user in the system.")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "User registered successfully", 
                     content = @Content(mediaType = "application/json", schema = @Schema(implementation = RegisterUserRequestDTO.class))),
        @ApiResponse(responseCode = "400", description = "Invalid request data")
    })
	@PostMapping("register")
	public ResponseEntity<?> register(@RequestBody RegisterUserRequestDTO registerUserRequestDTO) {
		try {
			CustomPageDTO<UserInformationResponseDTO> response = this.userService.register(registerUserRequestDTO);
			return new ResponseEntity<>(response, HttpStatus.OK);
		} catch (RuntimeException error) {
			return new ResponseEntity<>(error.getMessage(), HttpStatus.BAD_REQUEST);
		}
	}
    
    @Operation(summary = "Update user details", description = "Updates a user's information.")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "User updated successfully", 
                     content = @Content(mediaType = "application/json", schema = @Schema(implementation = RegisterUserRequestDTO.class))),
        @ApiResponse(responseCode = "400", description = "Invalid request data")
    })
	@PutMapping("{id}/update")
	public ResponseEntity<?> update(@PathVariable(value = "id") Integer id,
			@RequestBody RegisterUserRequestDTO registerUserRequestDTO) {
		try {
			CustomPageDTO<UserInformationResponseDTO> response = this.userService.updateById(registerUserRequestDTO, id);
			return new ResponseEntity<>(response, HttpStatus.OK);
		} catch (RuntimeException error) {
			return new ResponseEntity<>(error.getMessage(), HttpStatus.BAD_REQUEST);
		}
	}
}

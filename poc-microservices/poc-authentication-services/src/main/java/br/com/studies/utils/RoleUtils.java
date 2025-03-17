package br.com.studies.utils;

import java.util.HashSet;
import java.util.Set;

import br.com.studies.models.Role;

public abstract class RoleUtils {

	public static Set<String> convertRoleResponse(Set<Role> roles) {
		Set<String> rolesConverted = new HashSet<String>(); 
		roles.stream()
			.map(role -> rolesConverted.add(role.getName()))
			.toList();
		
		return rolesConverted;
	}
}

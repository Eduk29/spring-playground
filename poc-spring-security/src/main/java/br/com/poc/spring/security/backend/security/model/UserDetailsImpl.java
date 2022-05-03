package br.com.poc.spring.security.backend.security.model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import br.com.poc.spring.security.backend.model.Role;
import br.com.poc.spring.security.backend.model.User;

public class UserDetailsImpl implements UserDetails {

	private static final long serialVersionUID = -8489053074208206273L;

	private User user;

	public UserDetailsImpl(User user) {
		this.user = user;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
        List<Role> roles = user.getRoles();
        List<SimpleGrantedAuthority> authorities = new ArrayList<>();
         
        for (Role role : roles) {
            authorities.add(new SimpleGrantedAuthority(role.getCode()));
        }
         
        return authorities;
	}
	
	@Override
	public String getPassword() {
		return this.user.getPassword();
	}

	@Override
	public String getUsername() {
		return this.user.getUsername();
	}
	
	public List<Role> getRoles() {
		return this.user.getRoles();
	}
	
	public Integer getId() {
		return this.user.getId();
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}

	@Override
	public String toString() {
		return "Id:" + user.getId() + "\n" + "Username:" + user.getUsername() + "\n" + "\n";
	}
}

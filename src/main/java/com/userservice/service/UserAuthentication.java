package com.userservice.service;

import java.util.Collection;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import com.userservice.entity.User;

public class UserAuthentication implements Authentication {

	private static final long serialVersionUID = -2297904461662559146L;
	private final User user;
	private boolean authenticated = true;

	public UserAuthentication(User user) {
		this.user = user;
	}

	public String getName() {
		return user.getUsername();
	}

	public Collection<? extends GrantedAuthority> getAuthorities() {
		return user.getAuthorities();
	}

	public Object getCredentials() {
		return user.getPassword();
	}

	public User getDetails() {
		return user;
	}

	public Object getPrincipal() {
		return user.getEmail();
	}

	public boolean isAuthenticated() {
		return authenticated;
	}

	public void setAuthenticated(boolean authenticated) {
		this.authenticated = authenticated;
	}
}

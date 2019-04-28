package com.recipe.service;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import com.recipe.entity.Role;
import com.recipe.entity.User;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

/**
 * This class manages all data of users.
 */
public class UserDetailsImpl implements UserDetails {

	private User user;

	public UserDetailsImpl(User user) {
		this.user = user;
	}
	/**
	 * Collection of users.
	 */
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		Collection<GrantedAuthority> authorities = new HashSet<GrantedAuthority>();
		Set<Role> roles = user.getRoles();
		for (Role role : roles) {
			authorities.add(new SimpleGrantedAuthority(role.getRole()));
		}
		return authorities;
	}

	/**
	 * Get user password.
	 */
	@Override
	public String getPassword() {
		return user.getPassword();
	}

	/**
	 * Get username.
	 */
	@Override
	public String getUsername() {
		return user.getEmail();
	}

	/**
	 * Get if account is expired or not.
	 */
	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	/**
	 * Get if account is locked.
	 */
	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	/**
	 * Get if credentials are expired.
	 */
	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	/**
	 * Get if login is enabled.
	 */
	@Override
	public boolean isEnabled() {
		return true;
	}

	/**
	 * Get whole user object.
	 */
	public User getUser() {
		return this.user;
	}
}

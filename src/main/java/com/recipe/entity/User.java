package com.recipe.entity;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * User class provides full featured users (sic!) :)
 */

@Getter
@Setter
@ToString
@Entity
@Table(name = "users")
public class User {

	@Id @GeneratedValue
	private Long id;
	
	@Column(unique = true, nullable = false)
	private String email;
	
	@Column(nullable = false)
	private String password;

	private String fullName;

	@ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinTable(
		name = "users_roles",
		joinColumns = {@JoinColumn(name = "user_id")},
		inverseJoinColumns = {@JoinColumn(name = "role_id")}
	)
	private Set<Role> roles = new HashSet<Role>();
	
	@OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	private Set<Consumption> consumptions = new HashSet<>();

	public void addRoles(String roleName) {
		if (this.roles == null || this.roles.isEmpty()) 
			this.roles = new HashSet<>();
		this.roles.add(new Role(roleName));
	}
}

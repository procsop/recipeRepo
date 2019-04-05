package com.recipe.entity;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.*;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

/**
 * User class provides full featured users (sic!) :)
 */
@Getter
@Setter
@Entity
@Table( name="users" )
public class User {

	@Id @GeneratedValue
	private Long id;
	
	@Column( unique=true, nullable=false )
	private String email;
	
	@Column( nullable=false )
	private String password;
	
	private String fullName;
		
	@ManyToMany( cascade = CascadeType.ALL, fetch = FetchType.EAGER )
	@JoinTable( 
		name = "users_roles", 
		joinColumns = {@JoinColumn(name="user_id")}, 
		inverseJoinColumns = {@JoinColumn(name="role_id")}  
	)
	private Set<Role> roles = new HashSet<Role>();
	@OneToMany( mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.EAGER )
	private Set<Consumption> consumptions = new HashSet<>();

	public void addRoles(String roleName) {
		if (this.roles == null || this.roles.isEmpty()) 
			this.roles = new HashSet<>();
		this.roles.add(new Role(roleName));
	}

	/**
	 * Get user as string concatenated id,email,pwd.
	 */
	@Override
	public String toString() {
		return "User [id=" + id + ", email=" + email + ", password=" + password + "]";
	}

	
}
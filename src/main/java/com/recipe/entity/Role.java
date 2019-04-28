package com.recipe.entity;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * Role class controls the set of users by roles.
 */
@Getter
@Setter
@ToString
@Entity
@Table(name = "roles")
public class Role {

	@Id
	@GeneratedValue
	private Long id;
	
	private String role;
	
	@ManyToMany(mappedBy = "roles")
	private Set<User> users = new HashSet<User>();
		
	public Role(){}
	public Role(String role){
		this.role=role;
	}
}

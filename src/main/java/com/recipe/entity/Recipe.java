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
 * This class is used to manage recipes.
 */

@Getter
@Setter
@ToString
@Entity
@Table(name = "recipes")
public class Recipe {

	@Id @GeneratedValue
	private Long id;
	
	@Column(nullable = false)
	private String name;
	
	@Column(nullable = false)
	private String description;
    
    /**
     * SQL part of database-work.
     */
    @ManyToMany( cascade = CascadeType.ALL, fetch = FetchType.EAGER )
	@JoinTable(
		name = "recipe_ingredients",
		joinColumns = {@JoinColumn(name="recipe_id")},
		inverseJoinColumns = {@JoinColumn(name="ingredient_id")}
	)
    private Set<Ingredient> ingredients = new HashSet<Ingredient>();
    
    @OneToMany(mappedBy = "recipe", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Set<Consumption> consumptions;
}

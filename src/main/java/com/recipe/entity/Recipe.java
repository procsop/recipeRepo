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

/**
 * This class is used to manage recipes.
 */
@Entity
@Table( name="recipes" )
public class Recipe {

	@Id @GeneratedValue
	private Long id;
	
	@Column( nullable=false )
	private String name;
	
	@Column( nullable=false )
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

    /**
     * Constructor, essential getters and setters.
     */
    public Recipe() {
    }
    
    /**
     * Potentially setters and getters.
     */
    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Set<Ingredient> getIngredients() {
		return ingredients;
	}

	public void setIngredients(Set<Ingredient> ingredients) {
		this.ingredients = ingredients;
	}

    public Set<Consumption> getConsumptions() {
        return this.consumptions;
    }

    public void setConsumptions(Set<Consumption> consumptions) {
        this.consumptions = consumptions;
    }
    
	public void addIngredients(Ingredient ingredient) {
		this.ingredients.add(ingredient);
	}

    /**
     * Get recipe as string with ID concatenated name and desc.
     */
    @Override
    public String toString() {
        return "{" +
            " id='" + getId() + "'" +
            ", name='" + getName() + "'" +
            ", description='" + getDescription() + "'" +
            "}";
    }

	
}
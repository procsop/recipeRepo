package com.recipe.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

/**
 * Ingredient class is about the meals containing how much minerals.
 */

@Getter
@Setter
@Entity
@Table( name="ingredients" )
public class Ingredient {

	@Id @GeneratedValue
	private Long id;
	
	@Column( nullable=false )
	private String name;

    private String protein;
    private String fat;
    private String carbohydrate;
    private String magnesium;
    private String calcium;
    private String iron;

    /**
     * Get ID and NAME as string.
     */
    @Override
    public String toString() {
        return "{" +
            " id='" + getId() + "'" +
            ", name='" + getName() + "'" +
            "}";
    }
	
}
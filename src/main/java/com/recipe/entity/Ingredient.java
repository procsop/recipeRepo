package com.recipe.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Ingredient class is about the meals containing how much minerals.
 */
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

    public Ingredient() {
    }
    
    /**
     * Potentially setters and getter.
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

    public String getProtein() {
        return this.protein;
    }

    public void setProtein(String protein) {
        this.protein = protein;
    }

    public String getFat() {
        return this.fat;
    }

    public void setFat(String fat) {
        this.fat = fat;
    }

    public String getCarbohydrate() {
        return this.carbohydrate;
    }

    public void setCarbohydrate(String carbohydrate) {
        this.carbohydrate = carbohydrate;
    }

    public String getMagnesium() {
        return this.magnesium;
    }

    public void setMagnesium(String magnesium) {
        this.magnesium = magnesium;
    }

    public String getCalcium() {
        return this.calcium;
    }

    public void setCalcium(String calcium) {
        this.calcium = calcium;
    }

    public String getIron() {
        return this.iron;
    }

    public void setIron(String iron) {
        this.iron = iron;
    }

	
//    @ManyToMany( mappedBy = "recipes")
//	private Set<User> users = new HashSet<User>();

//    public Set<User> getUsers() {
//        return this.users;
//    }
//
//    public void setUsers(Set<User> users) {
//        this.users = users;
//    }

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
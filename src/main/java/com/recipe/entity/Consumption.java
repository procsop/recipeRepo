package com.recipe.entity;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "consumption")
public class Consumption {

    @Id @GeneratedValue
    private Long id;

    //@Id
    @ManyToOne
    @JoinColumn
    private User user;

    //@Id
    @ManyToOne
    @JoinColumn
    private Recipe recipe;

    private Date date;

    public Consumption(){
    }
    public Consumption(User user, Recipe recipe, Date date){
        this.user=user;
        this.recipe=recipe;
        this.date=date;
    }

    public User getUser() {
        return this.user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Recipe getRecipe() {
        return this.recipe;
    }

    public void setRecipe(Recipe recipe) {
        this.recipe = recipe;
    }

    public Date getDate() {
        return this.date;
    }

    public void setDate(Date date) {
        this.date = date;
    }


}
package com.recipe.entity;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
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
    public Consumption(User user, Recipe recipe, Date date) {
        this.user = user;
        this.recipe = recipe;
        this.date = date;
    }
}
package com.recipe.service;

import com.recipe.entity.Recipe;
import com.recipe.entity.User;

import java.text.ParseException;

public interface UserService {
	
	public String registerUser(User user);

	public User findByEmail(String email);
	public Recipe recommendRecipe() throws NumberFormatException, ParseException;
	public String addConsumption(Recipe selectedRecipe);
}

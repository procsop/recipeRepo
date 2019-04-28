package com.recipe.service;

import com.recipe.entity.Recipe;
import com.recipe.entity.User;

import java.text.ParseException;

public interface UserService {
	
	String registerUser(User user);

	public User findByEmail(String email);
	Recipe recommendRecipe() throws NumberFormatException, ParseException;
	String addConsumption(Recipe selectedRecipe);
}

package com.recipe.service;

import java.util.List;

import com.recipe.entity.Recipe;
import com.recipe.repo.RecipeRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RecipeService{
	
	private RecipeRepository recipeRepository;

	@Autowired
	public RecipeService(RecipeRepository recipeRepository) {
		this.recipeRepository = recipeRepository;
	}

    public String addRecipe(Recipe recipe) {
		recipeRepository.save(recipe);
		return "ok";
	}

	public List<Recipe> getRecipes() {
		return recipeRepository.findAll();
	}

	public Recipe getRecipeById(Long selectedRecipeId) {
		return recipeRepository.findById(selectedRecipeId);
	}

}

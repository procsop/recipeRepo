package com.recipe.service;

import java.util.List;

import com.recipe.entity.Recipe;
import com.recipe.repo.RecipeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Well functionated recipeservice to manage a repository.
 */
@Service
public class RecipeService {

	private RecipeRepository recipeRepository;

	@Autowired
	public RecipeService(RecipeRepository recipeRepository) {
		this.recipeRepository = recipeRepository;
	}

	/**
	 * Add recipe to repo.
	 */
  public String addRecipe(Recipe recipe) {
		recipeRepository.save(recipe);
		return "ok";
	}

	/**
	 * Get all recipes.
	 */
	public List<Recipe> getRecipes() {
		return recipeRepository.findAll();
	}

	/**
	 * Get recipe by id.
	 */
	public Recipe getRecipeById(Long selectedRecipeId) {
		return recipeRepository.findById(selectedRecipeId);
	}
}

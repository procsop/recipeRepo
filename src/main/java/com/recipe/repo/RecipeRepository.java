package com.recipe.repo;

import java.util.List;

import com.recipe.entity.Recipe;

import org.springframework.data.repository.CrudRepository;

public interface RecipeRepository extends CrudRepository<Recipe, Long> {
	List<Recipe> findAll();

	//List<Ingredient> findIngredientByRecipeId(Long id);

	Recipe findById(Long selectedRecipeId);
	//Ingredient findByIngredientsIngredient(String string);

	List<Recipe> findAllByOrderByIngredientsFatDesc();

	List<Recipe> findAllByOrderByIngredientsProteinDesc();
}
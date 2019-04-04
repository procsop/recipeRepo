package com.recipe.service;

import java.util.List;
import com.recipe.entity.Ingredient;
import com.recipe.repo.IngredientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Ingredient service class to improve get and add functions.
 */
@Service
public class IngredientService{
	
	private IngredientRepository ingredientRepository;

	@Autowired
	public IngredientService(IngredientRepository ingredientRepository) {
		this.ingredientRepository = ingredientRepository;
	}

	/**
	 * Adds an ingredient to a meal
	 * @param ingredient
	 * @return
	 */
  public String addIngredient(Ingredient ingredient) {
		ingredientRepository.save(ingredient);
		return "ok";
	}

	/**
	 * Gets an ingredient of a meal
	 * @return
	 */
	public List<Ingredient> getIngredients() {
		return ingredientRepository.findAll();
	}

}

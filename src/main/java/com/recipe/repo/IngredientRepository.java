package com.recipe.repo;

import org.springframework.data.repository.CrudRepository;
import java.util.List;
import com.recipe.entity.Ingredient;

/**
 * This class extends CrudRepository and gives an interface to us to get a list of ingredients.
 */
public interface IngredientRepository extends CrudRepository<Ingredient, Long> {
	List<Ingredient> findAll();
}

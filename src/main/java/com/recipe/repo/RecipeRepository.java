package com.recipe.repo;

import java.util.List;
import com.recipe.entity.Recipe;
import org.springframework.data.repository.CrudRepository;

/**
 * Interface for recipes.
 */
public interface RecipeRepository extends CrudRepository<Recipe, Long> {
	List<Recipe> findAll();

	//List<Ingredient> findIngredientByRecipeId(Long id);
    /**
     * Fint recipe by id.
     * @param selectedRecipeId
     * @return
     */
	Recipe findById(Long selectedRecipeId);
	//Ingredient findByIngredientsIngredient(String string);

    /**
     * Get Recipes ordered by contained fat.
     */
    List<Recipe> findAllByOrderByIngredientsFatDesc();
    
    /**
     * Get Recipes ordered by conatined protein.
     */
	List<Recipe> findAllByOrderByIngredientsProteinDesc();
}
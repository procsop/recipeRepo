package com.recipe.repo;

import java.util.List;
import com.recipe.entity.Recipe;
import org.springframework.data.repository.CrudRepository;

/**
 * Interface for recipes.
 */
public interface RecipeRepository extends CrudRepository<Recipe, Long> {
	List<Recipe> findAll();

    /**
     * Fint recipe by id.
     * @param selectedRecipeId
     * @return
     */
	Recipe findById(Long selectedRecipeId);

    /**
     * Get Recipes ordered by contained fat.
     */
    List<Recipe> findAllByOrderByIngredientsFatDesc();
    
    /**
     * Get Recipes ordered by conatined protein.
     */
	List<Recipe> findAllByOrderByIngredientsProteinDesc();
}
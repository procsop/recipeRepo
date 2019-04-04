package com.recipe.controller;

import java.text.ParseException;

import com.recipe.entity.Ingredient;
import com.recipe.entity.Recipe;
import com.recipe.entity.User;
import com.recipe.service.IngredientService;
import com.recipe.service.RecipeService;
import com.recipe.service.UserDetailsImpl;
import com.recipe.service.UserService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * HomeController class will control all of requests for user, home, recipes, registration...
 */
@Controller
public class HomeController {

	private final Logger log = LoggerFactory.getLogger(this.getClass());

	private UserService userService;
	private RecipeService recipeService;
	private IngredientService ingredientService;

	@Autowired
	public void setUserService(UserService userService) {
		this.userService = userService;
	}

	@Autowired
	public void setRecipeService(RecipeService recipeService) {
		this.recipeService = recipeService;
	}

	@Autowired
	public void setIngredientService(IngredientService ingredientService) {
		this.ingredientService = ingredientService;
	}

	@RequestMapping("/")
	public String home() {
		return "index";
	}

	@RequestMapping("/user")
	public String user(Model model) {
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		UserDetailsImpl userObj = ((UserDetailsImpl) principal);
		model.addAttribute("user", userObj.getUser());
		return "user";
	}

	@RequestMapping("/registration")
	public String registration(Model model){
		model.addAttribute("user", new User());
		return "registration";
	}
	
	@PostMapping("/reg")
    public String reg(@ModelAttribute User user) {
		log.info("Uj user!");
		log.debug(user.getFullName());
		log.debug(user.getEmail());
		log.debug(user.getPassword());
		userService.registerUser(user);
        return "auth/login";
	}
	@RequestMapping("/recipes")
	public String recipes(Model model) {
		model.addAttribute("recipes", recipeService.getRecipes());
		// model.addAttribute("ingredients", recipeService.getIngredients());
		return "recipes";
	}

	@RequestMapping("/recommend")
	public String recommendRecipe(Model model) throws NumberFormatException, ParseException {
		model.addAttribute("recommendRecipe", userService.recommendRecipe());
		String ezStringBazdmeg = userService.recommendRecipe().getName();
		log.debug(ezStringBazdmeg);
		//model.addAttribute("ingredients", recipeService.getIngredients());
		return "recommend";
	}

	@RequestMapping("/ingredients")
	public String ingredients(Model model) {
		model.addAttribute("ingredients", ingredientService.getIngredients());
		return "ingredients";
	}

	@RequestMapping("/admin/addingredient")
	public String addIngredient(Model model){
		model.addAttribute("ingredient", new Ingredient());
		return "addingredient";
	}

	@PostMapping("/addingredient")
	public String addIngredient(@ModelAttribute Ingredient ingredient) {
		log.info("Uj hozzávaló!");
		log.debug(ingredient.getName());
		ingredientService.addIngredient(ingredient);
		return "ingredients";
	}

	@RequestMapping("/admin/addrecipe")
	public String addRecipe(Model model){
		model.addAttribute("recipe", new Recipe());
		return "addrecipe";
	}

	@PostMapping("/addrecipe")
	public String addRecipe(@ModelAttribute Recipe recipe) {
		log.info("Uj recept!");
		log.debug(recipe.getName());
		log.debug(recipe.getDescription());
		log.debug(recipe.getIngredients().toString());
		recipeService.addRecipe(recipe);
		return "recipes";
	}
	@PostMapping("/addconsumption")
	public String addconsumption(@RequestParam("id") Long selectedRecipeId) {
		Recipe selectedRecipe = recipeService.getRecipeById(selectedRecipeId);
		log.info("Fogyasztás rögzítve!");
		log.debug(selectedRecipe.getName());
		userService.addConsumption(selectedRecipe);
		return "recipes";
	}
}

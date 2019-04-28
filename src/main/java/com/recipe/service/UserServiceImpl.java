package com.recipe.service;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Set;
import java.util.TreeMap;
import java.util.concurrent.TimeUnit;

import com.recipe.entity.Consumption;
import com.recipe.entity.Ingredient;
import com.recipe.entity.Recipe;
import com.recipe.entity.Role;
import com.recipe.entity.User;
import com.recipe.repo.RecipeRepository;
import com.recipe.repo.RoleRepository;
import com.recipe.repo.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService, UserDetailsService {

	private UserRepository userRepository;
	private RoleRepository roleRepository;
	private RecipeRepository recipeRepository;
	
	private final int PREV_DAYS = 3;
	private final int PROT_RATE = 120;
	private final int FAT_RATE = 80;
	private final String USER_ROLE = "USER";

	@Autowired
	public UserServiceImpl(UserRepository userRepository,
							RoleRepository roleRepository,
							RecipeRepository recipeRepository) {
		this.userRepository = userRepository;
		this.roleRepository = roleRepository;
		this.recipeRepository = recipeRepository;
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = findByEmail(username);
		if (user == null) {
			throw new UsernameNotFoundException(username);
		}
		return new UserDetailsImpl(user);
	}

	@Override
	public User findByEmail(String email) {
		return userRepository.findByEmail(email);
	}

	@Override
	public String registerUser(User userToRegister) {
		User userCheck = userRepository.findByEmail(userToRegister.getEmail());

		if (userCheck != null) {
			return "alreadyExists";
		}

		Role userRole = roleRepository.findByRole(USER_ROLE);
		if (userRole != null) {
			userToRegister.getRoles().add(userRole);
		} else {
			userToRegister.addRoles(USER_ROLE);
		}

		userRepository.save(userToRegister);

		return "ok";
	}

	@Override
	public String addConsumption(Recipe selectedRecipe) {
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		User actualUser = ((UserDetailsImpl) principal).getUser();

		actualUser.getConsumptions().add(new Consumption(actualUser, selectedRecipe, new Date()));

		userRepository.save(actualUser);
		return "ok";
	}

	@Override
	public Recipe recommendRecipe() throws NumberFormatException, ParseException {
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		User actualUser = ((UserDetailsImpl) principal).getUser();

		int sumProtein = 0;
		int sumFat = 0;

		Set<Consumption> consumptions = actualUser.getConsumptions();
		for (Consumption consumption : consumptions) {
			if (getDateDiff(consumption) <= PREV_DAYS) {
				Set<Ingredient> ingredients = consumption.getRecipe().getIngredients();
				for (Ingredient ingredient : ingredients) {
					sumProtein += Integer.parseInt(ingredient.getProtein());
					sumFat += Integer.parseInt(ingredient.getFat());
				}
			}
		}

		double proteinRate = sumProtein / PREV_DAYS / PROT_RATE;
		double fatRate = sumFat / PREV_DAYS / FAT_RATE;

		TreeMap<String, Double> nutriRates = new TreeMap<String, Double>();
		nutriRates.put("proteinRate", proteinRate);
		nutriRates.put("fatRate", fatRate);

		//rates.add(proteinRate);
		//rates.add(fatRate);
		//Collections.sort(rates);
		//Double worstNutrition = rates.get(0);
		String worstNutrition = nutriRates.firstKey();
		return getRecommendRecipe(worstNutrition);
	}

	public Recipe getRecommendRecipe(String worstNutrition) {
		List<Recipe> recipes = null;
		switch (worstNutrition) {
			case "fatRate":  recipes = recipeRepository.findAllByOrderByIngredientsFatDesc(); break;
			case "proteinRate":  recipes = recipeRepository.findAllByOrderByIngredientsProteinDesc(); break;
			default: break;
		}
		Recipe recommendedRecipe = recipes.get(0);
		return recommendedRecipe;
	}

	public long getDateDiff(Consumption consumption) throws ParseException {
		DateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
		Date today = formatter.parse(formatter.format(new Date()));
		Date consumptionDate = formatter.parse(formatter.format(consumption.getDate()));
		long diffInMillies = Math.abs(consumptionDate.getTime() - today.getTime());
		long diff = TimeUnit.DAYS.convert(diffInMillies, TimeUnit.MILLISECONDS);

		return diff;
	}
}

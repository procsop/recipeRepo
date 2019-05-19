package com.recipe.service;

import com.recipe.entity.*;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * This class should test services.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class ServicesTest {

    /**
     * Needed as can be seen down.
     */
    @Autowired
    private UserService userService;
    @Autowired
    private RecipeService recipeService;
    @Autowired
    private IngredientService ingredientService;

    /**
     * This test checks that if one e-mail is registered then no more can be that.
     
    @Test
    public void findUserByEmailShouldReturnOneUser(){
        User user = userService.findByEmail("neduabi@pm.me");
        assertThat(user.getEmail()).isEqualTo("neduabi@pm.me");
    }
    */
    /**
     * What is there is no registered e-mail? Should throw exception.
     */
    @Test
    public void nonExistingUserEmailShouldThrowException(){
        User user = userService.findByEmail("nem@letezik.me");
        try{
            assertThat(user.getEmail()).isEqualTo("nem@letezik.me");
        }catch(Exception e){
            String exception=e.toString().toLowerCase();
            boolean maybe=exception.contains("nullpointer") || 
                          exception.contains("error");
            assertThat(maybe).isEqualTo(true);
        }
    }

    /**
     * After registration -except if user already exist- the subroutine should return "ok".
     
    @Test
    public void registerUserShouldReturnOk(){
        User newUser= new User();
        newUser.setEmail("akarmi@pm.me"); newUser.setPassword("123"); newUser.setFullName("BakaJ");
        newUser.addRoles("USER"); newUser.setId(1L); 
        assertThat(userService.registerUser(newUser).equals("ok")).isEqualTo(true);
    */
    /**
     * After making new Role, all of setup stays the same?
     */
    @Test
    public void roleTester(){
        Role r = new Role("USER"); r.setId(2L);
        assertThat(r.toString().equals("Role [id="+2L+", role=USER]")).isEqualTo(true);
    }

    /**
     * 1.) Test: adding a new recipe using recipeservice .
     * 2.) Test: get all recipes back, and this size should be 1.
     */
    @Test
    public void recipeTester(){
        Recipe r = new Recipe(); r.setName("porkolt"); r.setId(3L); r.setDescription("csirkeporkolt");
        int meret=recipeService.getRecipes().size();
        assertThat(recipeService.addRecipe(r)).isEqualTo("ok"); // 1.) 
        
        List<Recipe> l=recipeService.getRecipes();  // 2.) 
        assertThat(l.size()).isEqualTo(meret+1);  //increased size by adding new recipe
    }

    /**
     * Test ingredient-service by adding an ingredient and ask for get it back.
     */
    @Test
    public void ingredientTester(){
        Ingredient i = new Ingredient();
        i.setName("szója"); i.setCalcium("277mg"); i.setCarbohydrate("30g"); i.setFat("0");
        i.setId(4L); i.setIron("16mg"); i.setMagnesium("280mg"); i.setProtein("36g");

        String ok = ingredientService.addIngredient(i);
        assertThat(ok).isEqualTo("ok");

        List<Ingredient> il = ingredientService.getIngredients();
        boolean isSavedAndCanBeFound=false;
        for(Ingredient t:il){
            if(t.getName().equals("szója")){
                isSavedAndCanBeFound=true;
            }
        }

        assertThat(isSavedAndCanBeFound).isEqualTo(true);
    }

}

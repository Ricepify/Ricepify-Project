package com.Ricepify.Controllers;

import com.Ricepify.bo.MealBO;
import com.Ricepify.Models.Recipe;
import com.Ricepify.Models.SiteUser;
import com.Ricepify.Repositories.RecipeRepository;
import com.Ricepify.Repositories.SiteUserRepository;
import com.Ricepify.Service.MealsService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.io.IOException;
import java.security.Principal;
import java.util.List;
@Controller
public class MealsController {

    private final MealsService mealsService;
    private List<MealBO> randomMealsList;
    private final RecipeRepository recipeRepository;
    private final SiteUserRepository siteUserRepository;

    public MealsController(MealsService mealsService, RecipeRepository recipeRepository ,
                           SiteUserRepository siteUserRepository) {
        this.mealsService = mealsService;
        this.recipeRepository = recipeRepository;
        this.siteUserRepository=siteUserRepository;
    }

    @GetMapping("/")
    public String getRandomMeals(Model model) {
        // Initialize the class-level field with the random meals
        try {
            randomMealsList = mealsService.getRandomMeals(8);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        model.addAttribute("meals", randomMealsList);
        return "home";
    }

    @GetMapping("/mealDetail")
    public String mealDetail(@RequestParam("id") String id, Model model) {
        MealBO meal = null;

        for (MealBO randomMeal : randomMealsList) {
            if (randomMeal.getId().equals(id)) {
                meal = randomMeal;
                System.out.println(meal.getMealName());
                break;
            }
        }

        model.addAttribute("meal", meal);

        return "mealDetail";
    }

    @PostMapping("/addToFavorites")

    public String addToFavorites(@RequestParam("mealId") String id , Principal p) {
        if (p != null){
            String username = p.getName();
            SiteUser siteUser = siteUserRepository.findByUsername(username);
            for (MealBO randomMeal : randomMealsList) {
                if (randomMeal.getId().equals(id)) {

                    Recipe recipe = new Recipe();
                    recipe.setRecipeTitle(randomMeal.getMealName());
                    recipe.setRecipeImage(randomMeal.getImage());
                    recipe.setRecipeDescription(randomMeal.getInstructions());
                    recipe.setRecipeCategory(randomMeal.getCategory());
                    recipe.setRecipeArea(randomMeal.getArea());
                    recipe.setRecipeMode("FromAPI");
                    recipe.setSiteUser(siteUser);
                    recipeRepository.save(recipe);

                    break;
                }
            }
        }

        return  "redirect:/";
    }
}
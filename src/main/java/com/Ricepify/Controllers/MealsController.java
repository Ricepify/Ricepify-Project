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
// TODO MOHAMMAD MAKE IT WORK
    @PostMapping("/addToFavorites")
    public String addToFavorites(@RequestParam("id") String id, Principal p) {
        if (p != null) {
            String username = p.getName();
            SiteUser siteUser = siteUserRepository.findByUsername(username);
            System.out.println("123asdf : asdf");
            for (int i = 0 ; i<8;i++){
                System.out.println("567sdf : asdf");
                if (randomMealsList.get(i).getId().equals(id)) {


                    System.out.println("asdf : asdf");
                    Recipe recipe = new Recipe();
                    recipe.setRecipeTitle(randomMealsList.get(i).getMealName());
                    recipe.setRecipeImage(randomMealsList.get(i).getImage());
                    recipe.setRecipeDescription(randomMealsList.get(i).getInstructions());
                    recipe.setRecipeCategory(randomMealsList.get(i).getCategory());
                    recipe.setRecipeArea(randomMealsList.get(i).getArea());
                    recipe.setRecipeMode("FromAPI");
                    recipe.setSiteUser(siteUser);
                    //ADD if it is existed before and some logic
                    recipeRepository.save(recipe);
                    break;
                }

            }
        }


        return "redirect:/";

    }
//    @GetMapping("/1")
//    public String getRandomMeal(Model model) {
//        // Initialize the class-level field with the random meals
////        randomMealsList = mealsService.getRandomMeals(8);
//        model.addAttribute("meals", randomMealsList);
//        return "home";
//    }
}
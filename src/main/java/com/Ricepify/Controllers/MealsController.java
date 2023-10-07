package com.Ricepify.Controllers;

import com.Ricepify.Models.RecipeEntity;
import com.Ricepify.Service.MealService;
import com.Ricepify.bo.MealBO;
import com.Ricepify.Models.SiteUserEntity;
import com.Ricepify.Repositories.RecipeRepository;
import com.Ricepify.Repositories.SiteUserRepository;
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

    private final MealService mealService;
    private List<MealBO> randomMealsList;
    private final RecipeRepository recipeRepository;
    private final SiteUserRepository siteUserRepository;

    public MealsController(MealService mealService, RecipeRepository recipeRepository, SiteUserRepository siteUserRepository) {
        this.mealService = mealService;
        this.recipeRepository = recipeRepository;
        this.siteUserRepository = siteUserRepository;
    }


    @GetMapping("/")
    public String getRandomMeals(Model model) {
        // Initialize the class-level field with the random meals
        try {
            randomMealsList = mealService.getRandomMeals(8);
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
            SiteUserEntity siteUserEntity = siteUserRepository.findByUsername(username);
            System.out.println("123asdf : asdf");
            for (int i = 0 ; i<8;i++){
                System.out.println("567sdf : asdf");
                if (randomMealsList.get(i).getId().equals(id)) {


                    System.out.println("asdf : asdf");
                    RecipeEntity recipeEntity = new RecipeEntity();
                    recipeEntity.setRecipeTitle(randomMealsList.get(i).getMealName());
                    recipeEntity.setRecipeImage(randomMealsList.get(i).getImage());
                    recipeEntity.setRecipeDescription(randomMealsList.get(i).getInstructions());
                    recipeEntity.setRecipeCategory(randomMealsList.get(i).getCategory());
                    recipeEntity.setRecipeArea(randomMealsList.get(i).getArea());
                    recipeEntity.setRecipeMode("FromAPI");
                    recipeEntity.setSiteUserEntity(siteUserEntity);
                    //ADD if it is existed before and some logic
                    recipeRepository.save(recipeEntity);
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
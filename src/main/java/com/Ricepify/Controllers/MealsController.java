package com.Ricepify.Controllers;

import com.Ricepify.Models.RecipeEntity;
import com.Ricepify.Service.MealService;
import com.Ricepify.bo.MealBO;
import com.Ricepify.Models.SiteUserEntity;
import com.Ricepify.Repositories.RecipeRepository;
import com.Ricepify.Repositories.SiteUserRepository;
import com.Ricepify.clint.RecipeClint;
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

        model.addAttribute("meal",mealService.findMealById(id,randomMealsList) );

        return "mealDetail";
    }

    @PostMapping("/addToFavoritesExternal")
    public String addToFavoritesExt(@RequestParam("id") String id, Principal p) {
        if (p != null) {
            String username = p.getName();
            SiteUserEntity siteUserEntity = siteUserRepository.findByUsername(username);

            mealService.addFromAPIToFavUserRecipesInDB(siteUserEntity,id);

        }



        return "redirect:/";

    }
        //Testing
//        private final RecipeClint recipeClint;
//    public MealsController(MealService mealService, List<MealBO> randomMealsList, RecipeRepository recipeRepository, SiteUserRepository siteUserRepository, RecipeClint recipeClint) {
//        this.mealService = mealService;
//        this.randomMealsList = randomMealsList;
//        this.recipeRepository = recipeRepository;
//        this.siteUserRepository = siteUserRepository;
//        this.recipeClint = recipeClint;
//    }
//
//    @GetMapping("/")
//    public String getRandomMeals(Model model) {
//        // Initialize the class-level field with the random meals
//        try {
//            for(int i =0 ; i < 5 ;i++){
//                randomMealsList.add(mealService.getMealById(i+52772));
//            }
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        }
//        model.addAttribute("meals", randomMealsList);
//        return "home";
//    }
}
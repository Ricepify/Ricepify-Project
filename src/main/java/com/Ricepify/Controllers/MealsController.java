package com.Ricepify.Controllers;
import com.Ricepify.Service.ExternalApi.ExternalApiService;
import com.Ricepify.Service.MealService;
import com.Ricepify.bo.MealBO;
import com.Ricepify.Models.SiteUserEntity;
import com.Ricepify.Repositories.RecipeRepository;
import com.Ricepify.Repositories.SiteUserRepository;
import com.Ricepify.bo.externalAPI.MealResponse;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.io.IOException;
import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

@Controller
public class MealsController {

    private final MealService mealService;
    private List<MealBO> randomMealsList;
    private final RecipeRepository recipeRepository;
    private final SiteUserRepository siteUserRepository;

    private final ExternalApiService externalApiService;
    public MealsController(MealService mealService, RecipeRepository recipeRepository, SiteUserRepository siteUserRepository, ExternalApiService externalApiService) {
        this.mealService = mealService;
        this.recipeRepository = recipeRepository;
        this.siteUserRepository = siteUserRepository;
        this.externalApiService = externalApiService;
    }

    @GetMapping("/searchByAreaOrCategory")
    public String search(@RequestParam(name = "query") String query,
                         @RequestParam(name = "searchType") String searchType,
                         Model model) {

        List<MealResponse> searchResults = new ArrayList<>();
        if ("category".equals(searchType)) {
            searchResults = externalApiService.searchByCategory(query);
        } else if ("area".equals(searchType)) {
            searchResults = externalApiService.searchByArea(query);
        }

        System.out.println(searchResults);
        model.addAttribute("meals", searchResults);
        return "search-results";
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


//    @GetMapping("/1")
//    public String getRandomMeal(Model model) {
//        // Initialize the class-level field with the random meals
////        randomMealsList = mealsService.getRandomMeals(8);
//        model.addAttribute("meals", randomMealsList);
//        return "home";
//    }
}
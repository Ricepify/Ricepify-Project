package com.Ricepify.Controllers;


import com.Ricepify.Models.RandomMealEntity;
import com.Ricepify.Service.MealsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
@Controller
public class MealsController {

    private final MealsService mealsService;
    private List<RandomMealEntity> randomMealsList;

    @Autowired
    public MealsController(MealsService mealsService) {
        this.mealsService = mealsService;
    }

    @GetMapping("/")
    public String getRandomMeals(Model model) {
        // Initialize the class-level field with the random meals
        randomMealsList = mealsService.getRandomMeals(8);
        model.addAttribute("meals", randomMealsList);
        return "home";
    }

    @GetMapping("/mealDetail")
    public String mealDetail(@RequestParam("id") String id, Model model) {
        RandomMealEntity meal = null;

        for (RandomMealEntity randomMeal : randomMealsList) {
            if (randomMeal.getId().equals(id)) {
                meal = randomMeal;
                System.out.println(meal.getMealName());
                break;
            }
        }

        model.addAttribute("meal", meal);

        return "mealDetail";
    }
}

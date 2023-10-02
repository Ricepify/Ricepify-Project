package com.Ricepify.Service;


import com.Ricepify.Models.Recipe;
import com.Ricepify.clint.RecipeClint;
import org.springframework.stereotype.Service;
import com.Ricepify.bo.MealBO;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
public class MealsService {
    private final RecipeClint recipeClint ;

    public MealsService(RecipeClint recipeClint) {
        this.recipeClint = recipeClint;
    }



    public List<MealBO> getRandomMeals(int numberOfMeals) throws IOException {
        List<MealBO> mealsList = new ArrayList<>();

        for (int i = 0; i < numberOfMeals; i++) {
            MealBO mealBO = recipeClint.getRandomMealInfo();

            mealsList.add(mealBO);
        }

        return mealsList;
    }

    public List<MealBO> getAllCategory() throws IOException {
        List<MealBO> mealsList = new ArrayList<>();


        MealBO mealBO = recipeClint.getAllCategoryInfo();

        mealsList.add(mealBO);


        return mealsList;
    }
    public List<MealBO> getAllAreas() throws IOException {
        List<MealBO> mealsList = new ArrayList<>();


        MealBO mealBO = recipeClint.getAllAreasInfo();

        mealsList.add(mealBO);


        return mealsList;
    }

    public Recipe convertForDataBaseFromAPI(MealBO randomMeal){
        Recipe recipe=new Recipe();

        recipe.setRecipeTitle(randomMeal.getMealName());
        recipe.setRecipeImage(randomMeal.getImage());
        recipe.setRecipeDescription(randomMeal.getInstructions());
        recipe.setRecipeCategory(randomMeal.getCategory());
        recipe.setRecipeArea(randomMeal.getArea());
        recipe.setRecipeMode("FromAPI");

        return recipe;
    }




}
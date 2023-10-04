package com.Ricepify.Service.impl;


import com.Ricepify.Service.MealService;
import com.Ricepify.clint.RecipeClint;
import org.springframework.stereotype.Service;
import com.Ricepify.bo.MealBO;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
public class MealServiceImpl implements MealService {
    private final RecipeClint recipeClint ;

    public MealServiceImpl(RecipeClint recipeClint) {
        this.recipeClint = recipeClint;
    }

    @Override
    public List<MealBO> getRandomMeals(int numberOfMeals) throws IOException {
        List<MealBO> mealsList = new ArrayList<>();

        for (int i = 0; i < numberOfMeals; i++) {
            MealBO mealBO = recipeClint.getRandomMealInfo();

            mealsList.add(mealBO);
        }

        return mealsList;
    }

    @Override
    public List<MealBO> getAllCategory() throws IOException {
        List<MealBO> mealsList = new ArrayList<>();

        MealBO mealBO = recipeClint.getAllCategoryInfo();

        mealsList.add(mealBO);


        return mealsList;
    }

    @Override
    public List<MealBO> getAllAreas() throws IOException {
        List<MealBO> mealsList = new ArrayList<>();


        MealBO mealBO = recipeClint.getAllAreasInfo();

        mealsList.add(mealBO);


        return mealsList;
    }

}
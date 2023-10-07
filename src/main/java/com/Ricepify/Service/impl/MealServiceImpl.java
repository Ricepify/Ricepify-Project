package com.Ricepify.Service.impl;


import com.Ricepify.Models.RecipeFavoriteEntity;
import com.Ricepify.Models.SiteUserEntity;
import com.Ricepify.Repositories.RecipeFavoriteRepository;
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

    private RecipeFavoriteRepository recipeFavoriteRepository;

    public MealServiceImpl(RecipeClint recipeClint, RecipeFavoriteRepository recipeFavoriteRepository) {
        this.recipeClint = recipeClint;
        this.recipeFavoriteRepository = recipeFavoriteRepository;
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
    @Override
    public  MealBO findMealById(String id ,List<MealBO> randomMealsList){

        MealBO meal = randomMealsList.stream()
                .filter(randomMeal -> randomMeal.getId().equals(id))
                .findFirst()
                .orElse(null);

        if (meal != null) {
            System.out.println(meal.getMealName());
        }
        return meal;
    }


    @Override
    public void addFromAPIToFavUserRecipesInDB(SiteUserEntity siteUserEntity ,String id){

        RecipeFavoriteEntity recipeFavoriteEntity =new RecipeFavoriteEntity();
        recipeFavoriteEntity.setSiteUserEntity(siteUserEntity);
        recipeFavoriteEntity.setMealId(Long.valueOf(id));
        recipeFavoriteRepository.save(recipeFavoriteEntity);
    }
}
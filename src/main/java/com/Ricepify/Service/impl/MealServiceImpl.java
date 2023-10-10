package com.Ricepify.Service.impl;


import com.Ricepify.Models.ExternalRecipeFavoriteEntity;
import com.Ricepify.Models.InternalRecipeFavoriteEntity;
import com.Ricepify.Models.RecipeEntity;
import com.Ricepify.Models.SiteUserEntity;
import com.Ricepify.Repositories.RecipeFavoriteFromAPIRepository;
import com.Ricepify.Repositories.RecipeFavoriteFromUsersRepository;
import com.Ricepify.Repositories.RecipeRepository;
import com.Ricepify.Service.MealService;
import com.Ricepify.bo.MealBO;
import com.Ricepify.clint.RecipeClint;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.UncheckedIOException;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Service
public class MealServiceImpl implements MealService {
    private  RecipeClint recipeClint;

    private RecipeFavoriteFromAPIRepository recipeFavoriteFromAPIRepository;
    private RecipeFavoriteFromUsersRepository recipeFavoriteFromUsersRepository;
    private RecipeRepository recipeRepository;

    public MealServiceImpl(RecipeClint recipeClint, RecipeFavoriteFromAPIRepository recipeFavoriteFromAPIRepository, RecipeFavoriteFromUsersRepository recipeFavoriteFromUsersRepository, RecipeRepository recipeRepository) {
        this.recipeClint = recipeClint;
        this.recipeFavoriteFromAPIRepository = recipeFavoriteFromAPIRepository;
        this.recipeFavoriteFromUsersRepository = recipeFavoriteFromUsersRepository;
        this.recipeRepository = recipeRepository;
    }

    public MealBO getMealFromDB(String id){
        RecipeEntity meal=new RecipeEntity();
        meal=recipeRepository.getById(Long.valueOf(id));
        MealBO mealBO=new MealBO();

        mealBO.setMealName(meal.getRecipeTitle());
        mealBO.setArea(meal.getRecipeArea());
        mealBO.setCategory(meal.getRecipeCategory());
        mealBO.setInstructions(meal.getRecipeDescription());
        mealBO.setVideo(meal.getRecipeVideo());
        mealBO.setImage(meal.getRecipeImage());
        mealBO.setId(id);

        return mealBO;
    }
    @Override
    public List<MealBO> getRandomMeals(int numberOfMeals) throws IOException {
        Map<Integer, MealBO> uniqueMealMap = new HashMap<>();

        while (uniqueMealMap.size() < numberOfMeals) {
            try {
                MealBO randomMeal = recipeClint.getRandomMealInfo();
                uniqueMealMap.put(Integer.parseInt(randomMeal.getId()), randomMeal);
            } catch (IOException e) {
                throw new UncheckedIOException(e);
            }
        }


        List<MealBO> uniqueMeals = new ArrayList<>(uniqueMealMap.values());

        return uniqueMeals;
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
    public MealBO findMealById(String id, List<MealBO> randomMealsList) {

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
    public MealBO getMealById(int id) throws IOException {
        return recipeClint.getMealInfoById(id);
    }


    @Override
    public void addFromAPIToFavUserRecipesInDB(SiteUserEntity siteUserEntity, String id) {

        ExternalRecipeFavoriteEntity externalRecipeFavoriteEntity = new ExternalRecipeFavoriteEntity();
        externalRecipeFavoriteEntity.setSiteUserEntity(siteUserEntity);
        externalRecipeFavoriteEntity.setMealId(Long.valueOf(id));
        recipeFavoriteFromAPIRepository.save(externalRecipeFavoriteEntity);
    }
    @Override
    public void addFromAUserToFavUserRecipesInDB(SiteUserEntity siteUserEntity , String id){
        InternalRecipeFavoriteEntity internalRecipeFavoriteEntity = new InternalRecipeFavoriteEntity();
        internalRecipeFavoriteEntity.setSiteUserEntity(siteUserEntity);
        internalRecipeFavoriteEntity.setMealId(Long.valueOf(id));

        recipeFavoriteFromUsersRepository.save(internalRecipeFavoriteEntity);

    }
    @Override
    public void removeFromAPIFavUserRecipesInDB(SiteUserEntity siteUserEntity, String id) {


            // Delete the found entity
            recipeFavoriteFromAPIRepository.deleteBySiteUserAndMealId(siteUserEntity.getId(),Long.valueOf(id));

    }

    @Override
    public void removeFromUsersFavUserRecipesInDB(SiteUserEntity siteUserEntity, String id) {


        // Delete the found entity
        recipeFavoriteFromUsersRepository.deleteBySiteUserAndMealId(siteUserEntity.getId(),Long.valueOf(id));

    }
        public MealBO APImealInfo(String id) throws IOException {
        return recipeClint.getMealInfoById(Integer.parseInt(id));
        }
}
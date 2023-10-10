package com.Ricepify.Service.impl;


import com.Ricepify.Models.ExternalRecipeFavoriteEntity;
import com.Ricepify.Models.InternalRecipeFavoriteEntity;
import com.Ricepify.Models.SiteUserEntity;
import com.Ricepify.Repositories.RecipeFavoriteFromAPIRepository;
import com.Ricepify.Repositories.RecipeFavoriteFromUsersRepository;
import com.Ricepify.Service.MealService;
import com.Ricepify.bo.MealBO;
import com.Ricepify.clint.RecipeClint;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.UncheckedIOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Service
public class MealServiceImpl implements MealService {
    private final RecipeClint recipeClint;

    private RecipeFavoriteFromAPIRepository recipeFavoriteFromAPIRepository;
    private RecipeFavoriteFromUsersRepository recipeFavoriteFromUsersRepository;


    public MealServiceImpl(RecipeClint recipeClint, RecipeFavoriteFromAPIRepository recipeFavoriteFromAPIRepository, RecipeFavoriteFromUsersRepository recipeFavoriteFromUsersRepository) {
        this.recipeClint = recipeClint;
        this.recipeFavoriteFromAPIRepository = recipeFavoriteFromAPIRepository;
        this.recipeFavoriteFromUsersRepository = recipeFavoriteFromUsersRepository;
    }

    @Override
    public List<MealBO> getRandomMeals(int numberOfMeals) throws IOException {
        return IntStream.range(0, numberOfMeals)
                .mapToObj(i -> {
                    try {
                        return recipeClint.getRandomMealInfo();
                    } catch (IOException e) {
                        throw new UncheckedIOException(e);
                    }
                })
                .collect(Collectors.toList());
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
    public void addFromAPIToFavUserRecipesInDB(SiteUserEntity siteUserEntity, String id) {

        ExternalRecipeFavoriteEntity externalRecipeFavoriteEntity = new ExternalRecipeFavoriteEntity();
        externalRecipeFavoriteEntity.setSiteUserEntity(siteUserEntity);
        externalRecipeFavoriteEntity.setMealId(Long.valueOf(id));
        recipeFavoriteFromAPIRepository.save(externalRecipeFavoriteEntity);
    }

    public void addFromAUserToFavUserRecipesInDB(SiteUserEntity siteUserEntity , String id){
        InternalRecipeFavoriteEntity internalRecipeFavoriteEntity = new InternalRecipeFavoriteEntity();
        internalRecipeFavoriteEntity.setSiteUserEntity(siteUserEntity);
        internalRecipeFavoriteEntity.setMealId(Long.valueOf(id));

        recipeFavoriteFromUsersRepository.save(internalRecipeFavoriteEntity);

    }
}
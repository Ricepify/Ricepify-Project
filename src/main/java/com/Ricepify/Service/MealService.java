package com.Ricepify.Service;

import com.Ricepify.Models.SiteUserEntity;
import com.Ricepify.bo.MealBO;
import java.io.IOException;
import java.util.List;

public interface MealService {

    List<MealBO> getRandomMeals(int numberOfMeals) throws IOException;

    List<MealBO> getAllCategory() throws IOException;

    List<MealBO> getAllAreas() throws IOException;
    MealBO findMealById(String id ,List<MealBO> randomMealsList);
    public void addFromAPIToFavUserRecipesInDB(SiteUserEntity siteUserEntity , String id);
}

package com.Ricepify.Service;

import com.Ricepify.Models.RecipeEntity;
import com.Ricepify.Models.SiteUserEntity;

import java.security.Principal;
import java.util.List;


public interface RecipeService {

    void saveRecipe(Principal p,
                    String recipeTitle,
                    String recipeImage,
                    String recipeDescription,
                    String recipeCategory,
                    String recipeArea,
                    String recipeMode,
                    String recipeVideo);

    RecipeEntity getRecipeById(Long recipeId);

    List<RecipeEntity> getSortedFeedPostsByTimeStampOrder(SiteUserEntity followingUser);

    List<RecipeEntity> findRecipeBySiteUserEntity(SiteUserEntity siteUser);
}

package com.Ricepify.Service;

import com.Ricepify.Models.RecipeEntity;
import com.Ricepify.Models.SiteUserEntity;

import java.security.Principal;
import java.util.List;


public interface RecipeService {

    void saveRecipe(RecipeEntity recipeEntity, Principal principal);

    RecipeEntity getRecipeById(Long recipeId);

    List<RecipeEntity> getSortedFeedPostsByTimeStampOrder(SiteUserEntity followingUser);

    List<RecipeEntity> findRecipeBySiteUserEntity(SiteUserEntity siteUser);
}

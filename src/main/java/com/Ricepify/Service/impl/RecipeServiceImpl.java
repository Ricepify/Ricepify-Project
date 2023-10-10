package com.Ricepify.Service.impl;

import com.Ricepify.Models.RecipeEntity;
import com.Ricepify.Models.SiteUserEntity;
import com.Ricepify.Repositories.RecipeRepository;
import com.Ricepify.Repositories.SiteUserRepository;
import com.Ricepify.Service.RecipeService;
import org.springframework.stereotype.Service;
import java.security.Principal;
import java.util.List;
import java.util.Optional;

@Service
public class RecipeServiceImpl implements RecipeService {
    private final SiteUserRepository siteUserRepository;
    private final RecipeRepository recipeRepository;

    public RecipeServiceImpl(SiteUserRepository siteUserRepository, RecipeRepository recipeRepository) {
        this.siteUserRepository = siteUserRepository;
        this.recipeRepository = recipeRepository;
    }

    @Override
    public void saveRecipe(Principal p, String recipeTitle, String recipeImage, String recipeDescription, String recipeCategory, String recipeArea, String recipeMode, String recipeVideo) {

        if (p != null) {
            String username = p.getName();
            SiteUserEntity siteUserEntity = siteUserRepository.findByUsername(username);

            RecipeEntity recipeEntity = new RecipeEntity();
            recipeEntity.setRecipeTitle(recipeTitle);
            recipeEntity.setRecipeImage(recipeImage);
            recipeEntity.setRecipeDescription(recipeDescription);
            recipeEntity.setRecipeCategory(recipeCategory);
            recipeEntity.setRecipeArea(recipeArea);
            recipeEntity.setRecipeMode(recipeMode);
            recipeEntity.setRecipeVideo(recipeVideo);
            recipeEntity.setSiteUserEntity(siteUserEntity);

            recipeRepository.save(recipeEntity);
        }
    }

    @Override
    public RecipeEntity getRecipeById(Long recipeId) {
        Optional<RecipeEntity> recipeEntity = recipeRepository.findById(recipeId);
        return recipeEntity.orElse(null);
    }

    @Override
    public List<RecipeEntity> getSortedFeedPostsByTimeStampOrder(SiteUserEntity followingUser) {
       return recipeRepository.findBySiteUserEntityOrderByCreatedAtDesc(followingUser);
    }

    @Override
    public List<RecipeEntity> findRecipeBySiteUserEntity(SiteUserEntity siteUser) {
        return recipeRepository.findBySiteUserEntity(siteUser);
    }
}

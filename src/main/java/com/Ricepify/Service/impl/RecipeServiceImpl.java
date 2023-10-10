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
    public void saveRecipe(RecipeEntity recipeEntity, Principal principal) {

        if (principal != null) {
            String username = principal.getName();
            SiteUserEntity siteUserEntity = siteUserRepository.findByUsername(username);

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

package com.Ricepify.Service.impl;

import com.Ricepify.Models.RecipeComment;
import com.Ricepify.Models.RecipeEntity;
import com.Ricepify.Models.SiteUserEntity;
import com.Ricepify.Repositories.RecipeCommentRepository;
import com.Ricepify.Service.RecipeCommentService;
import com.Ricepify.Service.RecipeService;
import com.Ricepify.Service.SiteUserService;
import org.springframework.stereotype.Service;

@Service
public class RecipeCommentServiceImpl implements RecipeCommentService {

    private final SiteUserService siteUserService;

    private final RecipeService recipeService;

    private final RecipeCommentRepository recipeCommentRepository;


    public RecipeCommentServiceImpl(SiteUserService siteUserService, RecipeService recipeService, RecipeCommentRepository recipeCommentRepository) {
        this.siteUserService = siteUserService;
        this.recipeService = recipeService;
        this.recipeCommentRepository = recipeCommentRepository;
    }

    @Override
    public void addComment(String comment, Long recipeId, String username) throws Exception {
        if (comment.isEmpty()) {
            throw new Exception("Null Comment");
        }

        if (recipeId == null) {
            throw new Exception("Null Recipe ID");
        }

        if (username.isEmpty()) {
            throw new Exception("Null Username");
        }

        SiteUserEntity siteUserEntity = siteUserService.getUserByUsername(username);
        RecipeEntity recipeEntity = recipeService.getRecipeById(recipeId);

        RecipeComment recipeComment = new RecipeComment();
        recipeComment.setComment(comment);
        recipeComment.setRecipeEntity(recipeEntity);
        recipeComment.setSiteUserEntity(siteUserEntity);

        recipeCommentRepository.save(recipeComment);
    }
}

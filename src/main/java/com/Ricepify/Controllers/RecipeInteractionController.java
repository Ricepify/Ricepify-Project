package com.Ricepify.Controllers;

import com.Ricepify.Models.RecipeComment;
import com.Ricepify.Models.RecipeEntity;
import com.Ricepify.Models.SiteUserEntity;
import com.Ricepify.Repositories.RecipeCommentRepository;
import com.Ricepify.Repositories.RecipeFavoriteFromAPIRepository;
import com.Ricepify.Repositories.RecipeRepository;
import com.Ricepify.Repositories.SiteUserRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.view.RedirectView;

import java.security.Principal;

@Controller
public class RecipeInteractionController {
    private final SiteUserRepository siteUserRepository;
    private final RecipeRepository recipeRepository;
    private final RecipeFavoriteFromAPIRepository recipeFavoriteFromAPIRepository;
    private final RecipeCommentRepository recipeCommentRepository;

    public RecipeInteractionController(SiteUserRepository siteUserRepository, RecipeRepository recipeRepository, RecipeFavoriteFromAPIRepository recipeFavoriteFromAPIRepository, RecipeCommentRepository recipeCommentRepository) {
        this.siteUserRepository = siteUserRepository;
        this.recipeRepository = recipeRepository;
        this.recipeFavoriteFromAPIRepository = recipeFavoriteFromAPIRepository;
        this.recipeCommentRepository = recipeCommentRepository;
    }



    @PostMapping("/saveComment")
    public RedirectView savePost(Principal p , String comment ,@ModelAttribute("recipeEntity") RecipeEntity recipeEntity){
        Long recipeId = recipeEntity.getRecipeId();
        if (p != null) {
            String username = p.getName();
            SiteUserEntity siteUserEntity = siteUserRepository.findByUsername(username);
             recipeEntity = recipeRepository.findById(recipeId).orElseThrow();

            RecipeComment recipeComment = new RecipeComment();
            recipeComment.setComment(comment);
            recipeComment.setRecipeEntity(recipeEntity);
            recipeComment.setSiteUserEntity(siteUserEntity);

            recipeCommentRepository.save(recipeComment);
        }
        return new RedirectView("/recipeDetails/" + recipeId);
    }
}

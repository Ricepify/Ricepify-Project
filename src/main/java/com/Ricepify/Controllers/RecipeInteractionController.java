package com.Ricepify.Controllers;

import com.Ricepify.Repositories.RecipeCommentRepository;
import com.Ricepify.Repositories.RecipeFavoriteRepository;
import com.Ricepify.Repositories.RecipeRepository;
import com.Ricepify.Repositories.SiteUserRepository;
import org.springframework.stereotype.Controller;

@Controller
public class RecipeInteractionController {
    private final SiteUserRepository siteUserRepository;
    private final RecipeRepository recipeRepository;
    private final RecipeFavoriteRepository recipeFavoriteRepository;
    private final RecipeCommentRepository recipeCommentRepository;

    public RecipeInteractionController(SiteUserRepository siteUserRepository, RecipeRepository recipeRepository, RecipeFavoriteRepository recipeFavoriteRepository, RecipeCommentRepository recipeCommentRepository) {
        this.siteUserRepository = siteUserRepository;
        this.recipeRepository = recipeRepository;
        this.recipeFavoriteRepository = recipeFavoriteRepository;
        this.recipeCommentRepository = recipeCommentRepository;
    }


//  ZAID  FIXME NEED SOME REFACTOR
//    @PostMapping("/saveComment")
//    public RedirectView savePost(Principal p , String comment){
//        if (p != null) {
//            String username = p.getName();
//            SiteUserEntity siteUserEntity = siteUserRepository.findByUsername(username);
//
//            RecipeInteractionEntity recipeInteractionEntity = new RecipeInteractionEntity();
//
//            recipeInteractionEntity.setComment(comment);
//            recipeInteractionEntity.setSiteUser(siteUserEntity);
//
//            recipeInteractionRepository.save(recipeInteractionEntity);
//        }
//        return new RedirectView("/my-profile");
//    }
}

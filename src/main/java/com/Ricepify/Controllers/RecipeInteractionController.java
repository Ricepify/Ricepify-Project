package com.Ricepify.Controllers;

import com.Ricepify.Models.RecipeComment;
import com.Ricepify.Models.RecipeEntity;
import com.Ricepify.Models.SiteUserEntity;
import com.Ricepify.Repositories.RecipeCommentRepository;
import com.Ricepify.Repositories.RecipeFavoriteFromAPIRepository;
import com.Ricepify.Repositories.RecipeRepository;
import com.Ricepify.Repositories.SiteUserRepository;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
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
    public RedirectView savePost(Principal p, String comment, @ModelAttribute("recipeEntity") RecipeEntity recipeEntity) {
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
    //TODO ZAID
//    @GetMapping("/deleteComment")
//    public RedirectView deleteComment(Principal p, Model m,
//                                      @RequestParam("commentId") Long commentId,
//                                      @RequestParam("siteUserId") Long siteUserId,
//                                      RedirectAttributes redirectAttribute) {
//        // Check if the currently logged-in user is the owner of the comment
//        if (p != null && p.getName().equals(siteUserId.toString())) {
//            // Delete the comment based on the commentId
//            recipeCommentRepository.deleteById(commentId);
//        } else {
//            redirectAttribute.addFlashAttribute("errorMessage", "You can't delete other users' comments.");
//        }
//
//        // Redirect back to the recipe details page
//        return new RedirectView("/recipeDetails/" + commentId);
//    }

}
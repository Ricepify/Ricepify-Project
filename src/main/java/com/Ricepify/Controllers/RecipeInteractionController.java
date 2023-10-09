package com.Ricepify.Controllers;

import com.Ricepify.Models.RecipeComment;
import com.Ricepify.Models.SiteUserEntity;
import com.Ricepify.Repositories.RecipeCommentRepository;
import com.Ricepify.Repositories.RecipeRepository;
import com.Ricepify.Repositories.SiteUserRepository;
import com.Ricepify.Service.RecipeCommentService;
import com.Ricepify.Service.RecipeService;
import com.Ricepify.Service.SiteUserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

import java.security.Principal;
import java.util.Optional;

@Controller
public class RecipeInteractionController {
    private final SiteUserRepository siteUserRepository;
    private final RecipeRepository recipeRepository;
    private final RecipeCommentRepository recipeCommentRepository;
    private final SiteUserService siteUserService;
    private final RecipeService recipeService;
    private final RecipeCommentService recipeCommentService;

    public RecipeInteractionController(SiteUserRepository siteUserRepository, RecipeRepository recipeRepository, RecipeCommentRepository recipeCommentRepository, SiteUserService siteUserService, RecipeService recipeService, RecipeCommentService recipeCommentService) {
        this.siteUserRepository = siteUserRepository;
        this.recipeRepository = recipeRepository;

        this.recipeCommentRepository = recipeCommentRepository;
        this.siteUserService = siteUserService;
        this.recipeService = recipeService;
        this.recipeCommentService = recipeCommentService;
    }


    @PostMapping("/addComment")
    public RedirectView addComment(Principal p, String comment,Long recipeId) throws Exception {

        if (p != null) {
            recipeCommentService.addComment(comment,recipeId, p.getName());
        }
        return new RedirectView("/recipeDetails/" + recipeId);
    }

    @PostMapping("/deleteComment")
    public RedirectView deleteComment(
            @RequestParam("id") Long commentId,
            @RequestParam("siteUserId") Long siteUserId,
            Principal principal,
            RedirectAttributes redirectAttributes ,
            Model model) {

        if (principal != null) {
            String username = principal.getName();

            Optional<RecipeComment> optionalComment = recipeCommentRepository.findById(commentId);
            SiteUserEntity siteUserEntity = siteUserRepository.findByUsername(principal.getName());

            if (optionalComment.isPresent()) {
                RecipeComment existingComment = optionalComment.get();

                if (siteUserEntity.getId().equals(siteUserId)) {
                    Long recipeId = existingComment.getRecipeEntity().getRecipeId();

                    recipeCommentRepository.deleteById(existingComment.getId());

                    return new RedirectView("/recipeDetails/" + recipeId);
                } else {
                    redirectAttributes.addFlashAttribute("errorMessage", "You can't delete other users' comments.");
                }
            }
            model.addAttribute("principal", principal);

        }
        return new RedirectView("/recipeDetails/{recipeId}");
    }

}
package com.Ricepify.Controllers;

import com.Ricepify.Models.RecipeInteraction;
import com.Ricepify.Models.SiteUser;
import com.Ricepify.Repositories.RecipeInteractionRepository;
import com.Ricepify.Repositories.RecipeRepository;
import com.Ricepify.Repositories.SiteUserRepository;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.view.RedirectView;

import java.security.Principal;

@Controller
public class RecipeInteractionController {
    private final SiteUserRepository siteUserRepository;
    private final RecipeRepository recipeRepository;

    private final RecipeInteractionRepository recipeInteractionRepository;

    public RecipeInteractionController(SiteUserRepository siteUserRepository, RecipeRepository recipeRepository, RecipeInteractionRepository recipeInteractionRepository) {
        this.siteUserRepository = siteUserRepository;
        this.recipeRepository = recipeRepository;
        this.recipeInteractionRepository = recipeInteractionRepository;
    }


//  ZAID  FIXME NEED SOME REFACTOR
    @PostMapping("/saveComment")
    public RedirectView savePost(Principal p , String comment){
        if (p != null) {
            String username = p.getName();
            SiteUser siteUser = siteUserRepository.findByUsername(username);

            RecipeInteraction recipeInteraction = new RecipeInteraction();

            recipeInteraction.setComment(comment);
            recipeInteraction.setSiteUser(siteUser);

            recipeInteractionRepository.save(recipeInteraction);
        }
        return new RedirectView("/my-profile");
    }
}

package com.Ricepify.Controllers;

import com.Ricepify.Models.RecipeInteraction;
import com.Ricepify.Models.SiteUser;
import com.Ricepify.Repositories.RecipeInteractionRepository;
import com.Ricepify.Repositories.RecipeRepository;
import com.Ricepify.Repositories.SiteUserRepository;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;

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


    @PostMapping("/savePost")
    public String savePost(Principal p , String comment){
        if (p != null) {
            String username = p.getName();
            SiteUser siteUser = siteUserRepository.findByUsername(username);

            Post posts = new Post();

            posts.setBody(body);
            posts.setCreatedAt(createdAt);
            posts.setApplicationUser(applicationUser);

            postRepository.save(posts);
        }
        return new RedirectView("/my-profile");
    }
}

package com.Ricepify.RicepifyProject.controller;

import com.Ricepify.RicepifyProject.Models.Recipe;
import com.Ricepify.RicepifyProject.Models.SiteUser;
import com.Ricepify.RicepifyProject.Repositories.RecipeRepository;
import com.Ricepify.RicepifyProject.Repositories.SiteUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.view.RedirectView;

import java.security.Principal;

@RestController
public class RicepieController {

    private final SiteUserRepository siteUserRepository;
    private final RecipeRepository recipeRepository;

    @Autowired
    public RicepieController(SiteUserRepository siteUserRepository, RecipeRepository recipeRepository, SiteUserRepository siteUserRepository1, RecipeRepository recipeRepository1) {
        this.siteUserRepository = siteUserRepository1;
        this.recipeRepository = recipeRepository1;
    }

    @GetMapping("/addNew_recipe")
    public String addNewRecipe() {
        return "new-recipe";
    }

    @PostMapping("/save_recipe")
    public RedirectView saveRecipe(Principal p,
                                    String recipeTitle,
                                    String recipeImage,
                                    String recipeDescription,
                                    String recipeCategory,
                                    String recipeArea,
                                    String recipeMode) {

        if (p != null){
            String username = p.getName();
            SiteUser siteUser = siteUserRepository.findByUsername(username);

            Recipe recipe = new Recipe();
            recipe.setRecipeTitle(recipeTitle);
            recipe.setRecipeImage(recipeImage);
            recipe.setRecipeDescription(recipeDescription);
            recipe.setRecipeCategory(recipeCategory);
            recipe.setRecipeArea(recipeArea);
            recipe.setRecipeMode(recipeMode);
            recipe.setSiteUser(siteUser);

            recipeRepository.save(recipe);

        }
        return null ;
    }


}

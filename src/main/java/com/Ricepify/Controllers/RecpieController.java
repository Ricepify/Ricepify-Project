package com.Ricepify.Controllers;

import com.Ricepify.Models.Recipe;
import com.Ricepify.Models.SiteUser;
import com.Ricepify.Repositories.RecipeRepository;
import com.Ricepify.Repositories.SiteUserRepository;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.view.RedirectView;

import java.security.Principal;

@Controller
public class RecpieController {
    private final SiteUserRepository siteUserRepository;
    private final RecipeRepository recipeRepository;

    public RecpieController(SiteUserRepository siteUserRepository, RecipeRepository recipeRepository) {
        this.siteUserRepository = siteUserRepository;
        this.recipeRepository = recipeRepository;
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


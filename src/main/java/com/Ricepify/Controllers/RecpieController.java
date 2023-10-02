package com.Ricepify.Controllers;

import com.Ricepify.Models.Recipe;
import com.Ricepify.Models.RecipeInteraction;
import com.Ricepify.Models.SiteUser;
import com.Ricepify.Repositories.RecipeInteractionRepository;
import com.Ricepify.Repositories.RecipeRepository;
import com.Ricepify.Repositories.SiteUserRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.view.RedirectView;

import java.security.Principal;
import java.util.List;
import java.util.Optional;

@Controller
public class RecpieController {
    private final SiteUserRepository siteUserRepository;
    private final RecipeRepository recipeRepository;

    private final RecipeInteractionRepository recipeInteractionRepository;

    public RecpieController(SiteUserRepository siteUserRepository, RecipeRepository recipeRepository, RecipeInteractionRepository recipeInteractionRepository) {
        this.siteUserRepository = siteUserRepository;
        this.recipeRepository = recipeRepository;
        this.recipeInteractionRepository = recipeInteractionRepository;
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

        if (p != null) {
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
        return null;
    }

    @GetMapping("/viewDetails/{id}")
    public String viewRecipeDetails(Principal p, Model model, @PathVariable Long id) {
        if (p != null) {
            Optional<Recipe> recipe = recipeRepository.findById(id);
            List<RecipeInteraction> usersComments = recipeInteractionRepository.findCommentById(id);
            model.addAttribute("recipe", recipe.get());
            model.addAttribute("usersComments", usersComments);
        }
        return "recipe-details";
    }
}


package com.Ricepify.Controllers;

import com.Ricepify.Models.RecipeComment;
import com.Ricepify.Models.RecipeEntity;
import com.Ricepify.Models.SiteUserEntity;
import com.Ricepify.Service.RecipeService;
import com.Ricepify.Service.SiteUserService;
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
    private final RecipeService recipeService;
    private final SiteUserService siteUserService;

    public RecpieController(RecipeService recipeService, SiteUserService siteUserService) {
        this.recipeService = recipeService;
        this.siteUserService = siteUserService;
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
                                   String recipeMode,
                                   String recipeVideo) {

        recipeService.saveRecipe(p , recipeTitle , recipeImage , recipeDescription , recipeCategory, recipeArea, recipeMode , recipeVideo);

        return new RedirectView("/myProfile");
    }

    @GetMapping("/recipeDetails/{id}")
    public String viewRecipeDetails(Principal p, Model model, @PathVariable Long id) {
        if (p != null) {
            String username = p.getName();
            SiteUserEntity commentByUser = siteUserService.getUserByUsername(username);
            RecipeEntity recipe = recipeService.getRecipeById(id);

            if (recipe != null) {
                List<RecipeComment> recipeComments = recipe.getRecipeComments();
                model.addAttribute("recipeEntity", recipe);
                model.addAttribute("usersComments", recipeComments);
                model.addAttribute("loggedInUserID", commentByUser.getId());
            }
        }
        return "recipe-details";
    }
}
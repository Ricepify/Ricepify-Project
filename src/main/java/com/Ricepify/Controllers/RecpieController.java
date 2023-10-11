package com.Ricepify.Controllers;

import com.Ricepify.Models.RecipeComment;
import com.Ricepify.Models.RecipeEntity;
import com.Ricepify.Models.RecipeEntityBuilder;
import com.Ricepify.Models.SiteUserEntity;
import com.Ricepify.Repositories.RecipeRepository;
import com.Ricepify.Service.RecipeService;
import com.Ricepify.Service.SiteUserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.view.RedirectView;

import java.security.Principal;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Controller
public class RecpieController {
    private final SiteUserService siteUserService;

    private final RecipeService recipeService;

    private final RecipeRepository recipeRepository;

    public RecpieController(SiteUserService siteUserService, RecipeService recipeService, RecipeRepository recipeRepository) {
        this.siteUserService = siteUserService;
        this.recipeService = recipeService;
        this.recipeRepository = recipeRepository;
    }

    @GetMapping("/addNew_recipe")
    public String addNewRecipe() {
        return "new-recipe";
    }

    @PostMapping("/save_recipe")
    public RedirectView saveRecipe(Principal principal, String recipeTitle, String recipeImage, String recipeDescription, String recipeCategory, String recipeArea, String recipeMode, String recipeVideo) {
        if (principal != null) {
            String username = principal.getName();
            SiteUserEntity siteUserEntity = siteUserService.getUserByUsername(username);
            LocalDate createdAt = LocalDate.now();
            RecipeEntity recipeEntity = new RecipeEntityBuilder()
                    .setRecipeTitle(recipeTitle)
                    .setRecipeImage(recipeImage)
                    .setRecipeDescription(recipeDescription)
                    .setRecipeCategory(recipeCategory)
                    .setRecipeArea(recipeArea)
                    .setRecipeMode("user")
                    .setRecipeVideo(recipeVideo)
                    .setSiteUserEntity(siteUserEntity)
                    .setCreatedAt(createdAt)
                    .build();

            recipeService.saveRecipe(recipeEntity,principal);
        }
        return new RedirectView("/myProfile");
    }

    @GetMapping("/recipeDetails/{id}")
    public String viewRecipeDetails(Principal p, Model model, @PathVariable Long id) {
        if (p != null) {
            String username = p.getName();
            SiteUserEntity commentByUser = siteUserService.getUserByUsername(username);
            Optional<RecipeEntity> recipe = recipeRepository.findById(id);
            if (recipe.isPresent()) {
                List<RecipeComment> recipeComments = recipe.get().getRecipeComments();
                recipe.ifPresent(recipeEntity -> model.addAttribute("recipeEntity", recipeEntity));
                model.addAttribute("usersComments", recipeComments);
                model.addAttribute("loggedInUserID", commentByUser.getId());
            }
        }
        return "recipe-details";
    }
}
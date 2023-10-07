package com.Ricepify.Controllers;

import com.Ricepify.Models.RecipeComment;
import com.Ricepify.Models.RecipeEntity;
import com.Ricepify.Models.SiteUserEntity;
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
                                   String recipeMode,
                                   String recipeVideo) {

        if (p != null) {
            String username = p.getName();
            SiteUserEntity siteUserEntity = siteUserRepository.findByUsername(username);

            RecipeEntity recipeEntity = new RecipeEntity();
            recipeEntity.setRecipeTitle(recipeTitle);
            recipeEntity.setRecipeImage(recipeImage);
            recipeEntity.setRecipeDescription(recipeDescription);
            recipeEntity.setRecipeCategory(recipeCategory);
            recipeEntity.setRecipeArea(recipeArea);
            recipeEntity.setRecipeMode(recipeMode);
            recipeEntity.setRecipeVideo(recipeVideo);
            recipeEntity.setSiteUserEntity(siteUserEntity);


            recipeRepository.save(recipeEntity);

        }
        return new RedirectView("/myProfile");
    }


    @GetMapping("/recipeDetails/{id}")
    public String viewRecipeDetails(Principal p, Model model, @PathVariable Long id) {
        if (p != null) {
            String username = p.getName();
            SiteUserEntity commentByUser = siteUserRepository.findByUsername(username);
            Optional<RecipeEntity> recipe = recipeRepository.findById(id);
            if (recipe.isPresent()) {
                List<RecipeComment> recipeComments = recipe.get().getRecipeComments();
                recipe.ifPresent(recipeEntity -> model.addAttribute("recipeEntity", recipeEntity));
                model.addAttribute("usersComments", recipeComments);
            }
        }
        return "recipe-details";
    }
}
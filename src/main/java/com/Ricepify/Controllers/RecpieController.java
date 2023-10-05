package com.Ricepify.Controllers;

import com.Ricepify.Models.RecipeEntity;
import com.Ricepify.Models.SiteUserEntity;
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
        return new RedirectView("/myprofile");
    }

//    ZAID TODO TESTING
//    @GetMapping("/viewDetails/{id}")
//    public String viewRecipeDetails(Principal p, Model model, @PathVariable Long id) {
//        if (p != null) {
//            Optional<Recipe> recipe = recipeRepository.findById(id);
//            List<RecipeInteraction> interactions = recipeInteractionRepository.findByRecipeId(id);
//            List<String> usersComments = interactions.stream().map(RecipeInteraction::getComment).collect(Collectors.toList());
//            model.addAttribute("recipe", recipe.get());
//            model.addAttribute("usersComments", usersComments);
//        }
//        return "recipe-details";
//    }
}


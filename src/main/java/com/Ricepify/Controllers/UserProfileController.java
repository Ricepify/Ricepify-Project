package com.Ricepify.Controllers;

import com.Ricepify.Models.RecipeEntity;
import com.Ricepify.Models.SiteUserEntity;
import com.Ricepify.Repositories.RecipeRepository;
import com.Ricepify.Repositories.SiteUserRepository;
import com.Ricepify.bo.MealBO;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

import java.security.Principal;
import java.util.List;

@Controller
public class UserProfileController {


   private final SiteUserRepository siteUserRepository;
    private final RecipeRepository recipeRepository;

    public UserProfileController(SiteUserRepository siteUserRepository, RecipeRepository recipeRepository) {
        this.siteUserRepository = siteUserRepository;
        this.recipeRepository = recipeRepository;
    }

    @GetMapping("/aboutus")
    public String getaboutus(){
        return "/aboutus/Aboutus.html";
    }

    @GetMapping("/myProfile")
    public String getUserProfile(Model model, Principal p) {
        if (p != null) {
            String username = p.getName();
            SiteUserEntity siteUserEntity = siteUserRepository.findByUsername(username);
            List<RecipeEntity> recipeEntities = siteUserEntity.getRecipeEntities();
            model.addAttribute("user", siteUserEntity); // Add this line
            model.addAttribute("recipies" , recipeEntities);

            model.addAttribute("user", siteUserEntity);

        }
        return "user-info";
    }
//    @GetMapping("/mealDetail")
//    public String mealDetail(@RequestParam("id") String id, Model model) {
//        MealBO meal = null;
//
//        for (MealBO randomMeal : randomMealsList) {
//            if (randomMeal.getId().equals(id)) {
//                meal = randomMeal;
//                System.out.println(meal.getMealName());
//                break;
//            }
//        }
//
//        model.addAttribute("meal", meal);
//
//        return "mealDetail";
//    }
    @PutMapping("/myProfile")
    public RedirectView editUserInfo(Principal p, Model m, String username, String firstName, String lastName, String email, String image,String bio, RedirectAttributes redir) {
        System.out.println("Received username: " + username);
        if ((p != null) && (p.getName().equals(username))) {
            SiteUserEntity siteUserEntity = siteUserRepository.findByUsername(username);
            System.out.println("Found user: " + siteUserEntity);
            m.addAttribute("id", siteUserEntity.getId());
            siteUserEntity.setUsername(username);
            siteUserEntity.setFirstName(firstName);
            siteUserEntity.setLastName(lastName);
            siteUserEntity.setEmail(email);
            siteUserEntity.setImage(image);
            siteUserEntity.setBio(bio);
            siteUserRepository.save(siteUserEntity);
            System.out.println("User saved successfully");
        } else {
            redir.addFlashAttribute("errorMessage", "You are not authorized to modify another user's information.");
        }
        return new RedirectView("/myProfile");
    }

}

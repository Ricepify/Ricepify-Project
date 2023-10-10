package com.Ricepify.Controllers;

import com.Ricepify.Models.SiteUserEntity;
import com.Ricepify.Repositories.RecipeFavoriteFromAPIRepository;
import com.Ricepify.Repositories.RecipeFavoriteFromUsersRepository;
import com.Ricepify.Repositories.RecipeRepository;
import com.Ricepify.Repositories.SiteUserRepository;
import com.Ricepify.Service.MealService;
import com.Ricepify.bo.MealBO;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.io.IOException;
import java.security.Principal;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Controller
public class favoriteController {


    private final MealService mealService;


    private final SiteUserRepository siteUserRepository;
//
////    public favoriteController(MealService mealService, List<MealBO> externalMealsList, List<MealBO> internalMealsList, SiteUserRepository siteUserRepository) {
////        this.mealService = mealService;
////        this.externalMealsList = externalMealsList;
////        this.internalMealsList = internalMealsList;
////        this.siteUserRepository = siteUserRepository;
////    }
//
@GetMapping("/favorite")
public String addToFavoritesExt(Principal p, Model model) {
    List<MealBO> externalMealsList = new ArrayList<>();
    List<MealBO> internalMealsList = new ArrayList<>();

    if (p != null) {
        String username = p.getName();
        SiteUserEntity siteUserEntity = siteUserRepository.findByUsername(username);

        List<Long> exMealsId = recipeFavoriteFromAPIRepository.findAllMealIdsByUserId(siteUserEntity.getId());
        List<Long> inMealsId = recipeFavoriteFromUsersRepository.findAllMealIdsByUserId(siteUserEntity.getId());

        internalMealsList = inMealsId.stream()
                .map(id -> mealService.getMealFromDB(String.valueOf(id)))
                .collect(Collectors.toList());

        externalMealsList = exMealsId.stream()
                .map(id -> {
                    try {
                        return mealService.getMealById(id.intValue());
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                })
                .collect(Collectors.toList());
    }

    model.addAttribute("internalMeals", internalMealsList);
    model.addAttribute("externalMeals", externalMealsList);
    return "favorite";
}

    @GetMapping("/favExMealDetail")
    public String mealDetail1(@RequestParam("id") String id, Model model) throws IOException {

        System.out.println(mealService.APImealInfo(id).getMealName());

        model.addAttribute("meal",mealService.APImealInfo(id));

        return "favMealDetails";
    }


    @GetMapping("/favIntMealDetail")
    public String mealDetail2(@RequestParam("id") String id, Model model) throws IOException {

        System.out.println(mealService.getMealFromDB(id).getMealName());
        System.out.println("*****************************************************");
        model.addAttribute("meal",mealService.getMealFromDB(id));

        return "favMealDetails";
    }
    @PostMapping("/removeExFavorite")
    public String removeFavorite1(@RequestParam("mealId") Long mealId, Principal p) {
        if (p != null) {
            String username = p.getName();
            SiteUserEntity siteUserEntity = siteUserRepository.findByUsername(username);

            // Call the remove method from mealService
            mealService.removeFromAPIFavUserRecipesInDB(siteUserEntity,Long.toString( mealId));
        }

        // Redirect back to the homepage or a suitable page after removal
        return "redirect:/favorite";
    }

    @PostMapping("/removeIntFavorite")
    public String removeFavorite2(@RequestParam("mealId") Long mealId, Principal p) {
        if (p != null) {
            String username = p.getName();
            SiteUserEntity siteUserEntity = siteUserRepository.findByUsername(username);

            // Call the remove method from mealService
            mealService.removeFromUsersFavUserRecipesInDB(siteUserEntity,Long.toString( mealId));
        }

        // Redirect back to the homepage or a suitable page after removal
        return "redirect:/favorite";
    }


    //    //Testing
    private final RecipeFavoriteFromAPIRepository recipeFavoriteFromAPIRepository;
    private final RecipeFavoriteFromUsersRepository recipeFavoriteFromUsersRepository;

    public favoriteController(MealService mealService, SiteUserRepository siteUserRepository, RecipeFavoriteFromAPIRepository recipeFavoriteFromAPIRepository, RecipeFavoriteFromUsersRepository recipeFavoriteFromUsersRepository) {
        this.mealService = mealService;
        this.siteUserRepository = siteUserRepository;
        this.recipeFavoriteFromAPIRepository = recipeFavoriteFromAPIRepository;
        this.recipeFavoriteFromUsersRepository = recipeFavoriteFromUsersRepository;
    }
}

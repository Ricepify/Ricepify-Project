    package com.Ricepify.Controllers;

    import com.Ricepify.Models.RecipeEntity;
    import com.Ricepify.Models.SiteUserEntity;
    import com.Ricepify.Models.SiteUserEntityBuilder;
    import com.Ricepify.Repositories.RecipeRepository;
    import com.Ricepify.Repositories.SiteUserRepository;
    import com.Ricepify.Service.MealService;
    import com.Ricepify.Service.SiteUserService;
    import org.springframework.stereotype.Controller;
    import org.springframework.ui.Model;
    import org.springframework.web.bind.annotation.GetMapping;
    import org.springframework.web.bind.annotation.PostMapping;
    import org.springframework.web.bind.annotation.PutMapping;
    import org.springframework.web.bind.annotation.RequestParam;
    import org.springframework.web.servlet.mvc.support.RedirectAttributes;
    import org.springframework.web.servlet.view.RedirectView;

    import java.security.Principal;
    import java.util.List;

    @Controller
    public class UserProfileController {
        private final SiteUserService siteUserService;
        private final MealService mealService;

        public UserProfileController(MealService mealService, SiteUserRepository siteUserRepository, RecipeRepository recipeRepository, SiteUserService siteUserService, MealService mealService1) {
            this.siteUserService = siteUserService;
            this.mealService = mealService1;
        }

        @GetMapping("/aboutus")
        public String getaboutus() {
            return "/aboutus/Aboutus.html";
        }

        @GetMapping("/myProfile")
        public String getUserProfile(Model model, Principal p) {
            if (p != null) {
                String username = p.getName();
                SiteUserEntity siteUserEntity = siteUserService.getUserByUsername(username);
                List<RecipeEntity> recipeEntities = siteUserEntity.getRecipeEntities();

                model.addAttribute("user", siteUserEntity);
                model.addAttribute("recipies", recipeEntities);
                Integer followersCount = siteUserService.getFollowersCount(siteUserEntity);
                Integer followingCount = siteUserService.getFollowingUsers(siteUserEntity);
                siteUserEntity.setFollowersCount(followersCount);
                siteUserEntity.setFollowingCount(followingCount);
            }
            return "user-info";
        }



        @PutMapping("/myProfile")
        public RedirectView editUserInfo(Principal p, Model m, String username, String firstName, String lastName, String email, String image, String bio, RedirectAttributes redir) {
            SiteUserEntity siteUser = new SiteUserEntity();
            siteUser.setFirstName(firstName);
            siteUser.setLastName(lastName);
            siteUser.setUsername(username);
            siteUser.setEmail(email);
            siteUser.setImage(image);
            siteUser.setBio(bio);

            siteUserService.editUserInfo(p, m, siteUser, redir);
            return new RedirectView("/myProfile");
        }

        @PostMapping("/addToFavoritesInternal")
        public String addToFavoritesInt(@RequestParam("id") String id, Principal p) {
            if (p != null) {
                String username = p.getName();
                SiteUserEntity siteUserEntity = siteUserService.getUserByUsername(username);

                mealService.addFromAUserToFavUserRecipesInDB(siteUserEntity, id);

            }
            return "redirect:/myProfile";
        }

    }
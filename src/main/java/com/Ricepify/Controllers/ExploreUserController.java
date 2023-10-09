//TODOFARAH.

package com.Ricepify.Controllers;

import com.Ricepify.Models.RecipeEntity;
import com.Ricepify.Models.SiteUserEntity;
import com.Ricepify.Repositories.RecipeRepository;
import com.Ricepify.Repositories.SiteUserRepository;
import com.Ricepify.Service.RecipeService;
import com.Ricepify.Service.SiteUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.view.RedirectView;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Controller
public class ExploreUserController {
    @Autowired
    SiteUserRepository siteUserRepository;
    @Autowired
    RecipeRepository recipeRepository;

    private final SiteUserService siteUserService;
    private final RecipeService recipeService;

    public ExploreUserController(SiteUserService siteUserService, RecipeService recipeService) {
        this.siteUserService = siteUserService;
        this.recipeService = recipeService;
    }

    @GetMapping("/findUsers")
    public String getRandomUsers(Principal p, Model model) {
        if (p != null) {
            String username = p.getName();
            SiteUserEntity loggedInUser = siteUserService.getUserByUsername(username);
            List<SiteUserEntity> randomUsers = siteUserService.getRandomUsers(loggedInUser.getId(), 7);
            model.addAttribute("users", randomUsers);
            Set<SiteUserEntity> followingSet = loggedInUser.getFollowing();
            List<RecipeEntity> feedPosts = new ArrayList<>();
            for (SiteUserEntity followingUser : followingSet) {
                feedPosts.addAll(recipeService.getSortedFeedPostsByTimeStampOrder(followingUser));
            }
            model.addAttribute("feedPosts", feedPosts);
            model.addAttribute("username", username);
        }
        return "findUser";
    }


    @GetMapping("/search")
    public String searchUsers(@RequestParam String query, Model model) {
        List<SiteUserEntity> searchResults = siteUserService.getUsersByFirstName(query);
        model.addAttribute("users", searchResults);
        return "findUser";
    }

    @GetMapping("/userinfo/{id}")
    public String getUserInfo(Principal p, @PathVariable Long id, Model model) {
        SiteUserEntity user = siteUserService.getUserById(id);
        SiteUserEntity currentUser = siteUserService.getUserByUsername(p.getName());


        if (user != null) {
            model.addAttribute("user", user);

            boolean isFollowed = siteUserService.isUserFollowedBy(currentUser , user);
            model.addAttribute("isFollowed", isFollowed);

            Integer followersCount = siteUserService.getFollowersCount(user);
            Integer followingCount = siteUserService.getFollowingUsers(user);
            user.setFollowersCount(followersCount);
            user.setFollowingCount(followingCount);

            List<RecipeEntity> recipes = recipeService.findRecipeBySiteUserEntity(user);
            model.addAttribute("recipes", recipes);

            return "otherProfileinfo";
        } else {
            return "redirect:/";
        }
    }

    @GetMapping("/userinfo")
    public String getUserInfoPage(Principal p, Model model) {
        if (p != null) {
            String username = p.getName();
            SiteUserEntity siteUserModel = siteUserService.getUserByUsername(username);
            model.addAttribute("user", siteUserModel);
        }
        return "findUser";
    }

    @PostMapping("/follow/{id}")
    public RedirectView followUser(Principal p, @PathVariable Long id) {
      siteUserService.followUser(p , id);
        return new RedirectView("/userinfo/" + id);
    }
}

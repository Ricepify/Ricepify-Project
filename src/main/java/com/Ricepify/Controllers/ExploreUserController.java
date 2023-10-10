////TODOFARAH.
//
//package com.Ricepify.Controllers;
//
//import com.Ricepify.Models.RecipeEntity;
//import com.Ricepify.Models.SiteUserEntity;
//import com.Ricepify.Repositories.RecipeRepository;
//import com.Ricepify.Repositories.SiteUserRepository;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PathVariable;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestParam;
//import org.springframework.web.servlet.view.RedirectView;
//import java.security.Principal;
//import java.util.ArrayList;
//import java.util.List;
//import java.util.Set;
//
//@Controller
//public class ExploreUserController {
//    @Autowired
//    SiteUserRepository siteUserRepository;
//    @Autowired
//    RecipeRepository recipeRepository;
//
//    @GetMapping("/findUsers")
//    public String getRandomUsers(Principal p, Model model) {
//        if (p != null) {
//            String username = p.getName();
//            SiteUserEntity loggedInUser = siteUserRepository.findByUsername(username);
//            List<SiteUserEntity> randomUsers = siteUserRepository.findRandomUsersExceptUser(loggedInUser.getId(), 7);
//            model.addAttribute("users", randomUsers);
//            Set<SiteUserEntity> followingSet = loggedInUser.getFollowing();
//            List<RecipeEntity> feedPosts = new ArrayList<>();
//            for (SiteUserEntity followingUser : followingSet) {
//                feedPosts.addAll(recipeRepository.findBySiteUserEntityOrderByCreatedAtDesc(followingUser));
//            }
//
//            model.addAttribute("feedPosts", feedPosts);
//            model.addAttribute("username", username);
//        }
//        return "findUser";
//    }
//
//
//    @GetMapping("/search")
//    public String searchUsers(@RequestParam String query, Model model) {
//        List<SiteUserEntity> searchResults = siteUserRepository.findByFirstNameContainingIgnoreCase(query);
//        model.addAttribute("users", searchResults);
//        return "findUser";
//    }
//@GetMapping("/userinfo/{id}")
//public String getUserInfo(Principal p, @PathVariable Long id, Model model) {
//    SiteUserEntity user = siteUserRepository.findById(id).orElse(null);
//    SiteUserEntity currentUser = siteUserRepository.findByUsername(p.getName());
//    boolean isFollowed = currentUser.getFollowing().contains(user);
//
//    if (user != null) {
//        model.addAttribute("user", user);
//        model.addAttribute("isFollowed", isFollowed);
//        Integer followersCount = user.getFollowers().size();
//        Integer followingCount = user.getFollowing().size();
//        user.setFollowersCount(followersCount);
//        user.setFollowingCount(followingCount);
//        List<RecipeEntity> recipes = recipeRepository.findBySiteUserEntity(user);
//        model.addAttribute("recipes", recipes);
//
//        return "otherProfileinfo";
//    } else {
//        return "redirect:/";
//    }
//}
//
//
//    @GetMapping("/userinfo")
//    public String getUserInfoPage(Principal p, Model model) {
//        if (p != null) {
//            String username = p.getName();
//            SiteUserEntity siteUserModel = siteUserRepository.findByUsername(username);
//            model.addAttribute("user", siteUserModel);
//        }
//        return "findUser";
//    }
//
//    @PostMapping("/follow/{id}")
//    public RedirectView followUser(Principal p, @PathVariable Long id) {
//        SiteUserEntity currentUser = siteUserRepository.findByUsername(p.getName());
//        SiteUserEntity userToFollow = siteUserRepository.findById(id).orElse(null);
//        if (userToFollow != null) {
//            if (currentUser.getFollowing().contains(userToFollow)) {
//                currentUser.getFollowing().remove(userToFollow);
//            } else {
//                currentUser.getFollowing().add(userToFollow);
//            }
//            siteUserRepository.save(currentUser);
//        }
//        return new RedirectView("/userinfo/" + id);
//    }
//}

//
//package com.Ricepify.Controllers;
//
//import com.Ricepify.Models.RecipeEntity;
//import com.Ricepify.Models.SiteUserEntity;
//import com.Ricepify.Repositories.RecipeRepository;
//import com.Ricepify.Repositories.SiteUserRepository;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
//import org.springframework.web.bind.annotation.*;
//import org.springframework.web.servlet.view.RedirectView;
//
//import java.security.Principal;
//import java.util.ArrayList;
//import java.util.List;
//import java.util.Set;
//
//@Controller
//public class ExploreUserController {
//    @Autowired
//    SiteUserRepository siteUserRepository;
//
//    @Autowired
//    RecipeRepository recipeRepository;
//
//    @GetMapping("/findUsers")
//    public String getRandomUsers(Principal p, Model model) {
//        try {
//            if (p != null) {
//                String username = p.getName();
//                SiteUserEntity loggedInUser = siteUserRepository.findByUsername(username);
//                List<SiteUserEntity> randomUsers = siteUserRepository.findRandomUsersExceptUser(loggedInUser.getId(), 7);
//                model.addAttribute("users", randomUsers);
//                Set<SiteUserEntity> followingSet = loggedInUser.getFollowing();
//                List<RecipeEntity> feedPosts = new ArrayList<>();
//                for (SiteUserEntity followingUser : followingSet) {
//                    feedPosts.addAll(recipeRepository.findBySiteUserEntityOrderByCreatedAtDesc(followingUser));
//                }
//                model.addAttribute("feedPosts", feedPosts);
//                model.addAttribute("username", username);
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return "findUser";
//    }
//
//    @GetMapping("/search")
//    public String searchUsers(@RequestParam String query, Model model) {
//        try {
//            List<SiteUserEntity> searchResults = siteUserRepository.findByFirstNameContainingIgnoreCase(query);
//            model.addAttribute("users", searchResults);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return "findUser";
//    }
//
//    @GetMapping("/userinfo/{id}")
//    public String getUserInfo(Principal p, @PathVariable Long id, Model model) {
//        try {
//            SiteUserEntity user = siteUserRepository.findById(id).orElse(null);
//            SiteUserEntity currentUser = siteUserRepository.findByUsername(p.getName());
//            boolean isFollowed = currentUser.getFollowing().contains(user);
//
//            if (user != null) {
//                model.addAttribute("user", user);
//                model.addAttribute("isFollowed", isFollowed);
//                Integer followersCount = user.getFollowers().size();
//                Integer followingCount = user.getFollowing().size();
//                user.setFollowersCount(followersCount);
//                user.setFollowingCount(followingCount);
//
//                List<RecipeEntity> recipes = recipeRepository.findBySiteUserEntity(user);
//                model.addAttribute("recipes", recipes);
//
//                return "otherProfileinfo";
//            } else {
//                return "redirect:/";
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//            return "redirect:/";
//        }
//    }
//
//    @GetMapping("/userinfo")
//    public String getUserInfoPage(Principal p, Model model) {
//        try {
//            if (p != null) {
//                String username = p.getName();
//                SiteUserEntity siteUserModel = siteUserRepository.findByUsername(username);
//                model.addAttribute("user", siteUserModel);
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return "findUser";
//    }
//
//    @PostMapping("/follow/{id}")
//    public RedirectView followUser(Principal p, @PathVariable Long id) {
//        try {
//            SiteUserEntity currentUser = siteUserRepository.findByUsername(p.getName());
//            SiteUserEntity userToFollow = siteUserRepository.findById(id).orElse(null);
//            if (userToFollow != null) {
//                if (currentUser.getFollowing().contains(userToFollow)) {
//                    currentUser.getFollowing().remove(userToFollow);
//                } else {
//                    currentUser.getFollowing().add(userToFollow);
//                }
//                siteUserRepository.save(currentUser);
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return new RedirectView("/userinfo/" + id);
//    }
//}
//
//TODOFARAH.

package com.Ricepify.Controllers;

import com.Ricepify.Models.RecipeEntity;
import com.Ricepify.Models.SiteUserEntity;
import com.Ricepify.Repositories.RecipeRepository;
import com.Ricepify.Repositories.SiteUserRepository;
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

    @GetMapping("/findUsers")
    public String getRandomUsers(Principal p, Model model) {
        if (p != null) {
            String username = p.getName();
            SiteUserEntity loggedInUser = siteUserRepository.findByUsername(username);
            List<SiteUserEntity> randomUsers = siteUserRepository.findRandomUsersExceptUser(loggedInUser.getId(), 7);
            model.addAttribute("users", randomUsers);
            Set<SiteUserEntity> followingSet = loggedInUser.getFollowing();
            List<RecipeEntity> feedPosts = new ArrayList<>();
            for (SiteUserEntity followingUser : followingSet) {
                feedPosts.addAll(recipeRepository.findBySiteUserEntityOrderByCreatedAtDesc(followingUser));
            }

            model.addAttribute("feedPosts", feedPosts);
            model.addAttribute("username", username);
        }
        return "findUser";
    }


    @GetMapping("/search")
    public String searchUsers(@RequestParam String query, Model model) {
        List<SiteUserEntity> searchResults = siteUserRepository.findByFirstNameContainingIgnoreCase(query);
        model.addAttribute("users", searchResults);
        return "findUser";
    }
    @GetMapping("/userinfo/{id}")
    public String getUserInfo(Principal p, @PathVariable Long id, Model model) {
        SiteUserEntity user = siteUserRepository.findById(id).orElse(null);
        SiteUserEntity currentUser = siteUserRepository.findByUsername(p.getName());
        boolean isFollowed = currentUser.getFollowing().contains(user);

        if (user != null) {
            model.addAttribute("user", user);
            model.addAttribute("isFollowed", isFollowed);
            Integer followersCount = user.getFollowers().size();
            Integer followingCount = user.getFollowing().size();
            user.setFollowersCount(followersCount);
            user.setFollowingCount(followingCount);

            List<RecipeEntity> recipes = recipeRepository.findBySiteUserEntity(user);
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
            SiteUserEntity siteUserModel = siteUserRepository.findByUsername(username);
            model.addAttribute("user", siteUserModel);
        }
        return "findUser";
    }

    @PostMapping("/follow/{id}")
    public RedirectView followUser(Principal p, @PathVariable Long id) {
        SiteUserEntity currentUser = siteUserRepository.findByUsername(p.getName());
        SiteUserEntity userToFollow = siteUserRepository.findById(id).orElse(null);
        if (userToFollow != null) {
            if (currentUser.getFollowing().contains(userToFollow)) {
                currentUser.getFollowing().remove(userToFollow);
            } else {
                currentUser.getFollowing().add(userToFollow);
            }
            siteUserRepository.save(currentUser);
        }
        return new RedirectView("/userinfo/" + id);
    }
}
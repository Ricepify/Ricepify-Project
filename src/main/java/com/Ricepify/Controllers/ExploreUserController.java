//TODOFARAH.

package com.Ricepify.Controllers;
import com.Ricepify.Models.SiteUserEntity;
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
import java.util.List;

@Controller
public class ExploreUserController {
    @Autowired
    SiteUserRepository siteUserRepository;
        @GetMapping("/findUsers")
    public String getRandomUsers(Principal p, Model model) {
        if (p != null) {
            String username = p.getName();
            SiteUserEntity loggedInUser = siteUserRepository.findByUsername(username);
            List<SiteUserEntity> randomUsers = siteUserRepository.findRandomUsersExceptUser(loggedInUser.getId(), 5);
            model.addAttribute("users", randomUsers);
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
        System.out.println(isFollowed);
        if (user != null) {
            model.addAttribute("user", user);
            model.addAttribute("isFollowed", isFollowed);
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

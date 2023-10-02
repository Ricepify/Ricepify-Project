package com.Ricepify.Controllers;

import com.Ricepify.Models.SiteUser;
import com.Ricepify.Repositories.SiteUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

import java.security.Principal;

@Controller
public class UserProfileController {

    @Autowired
    SiteUserRepository siteUserRepository;

    @GetMapping("/myprofile")
    public String getUserProfile(Model model, Principal p) {
        if (p != null) {
            String username = p.getName();
            SiteUser siteUser = siteUserRepository.findByUsername(username);
            model.addAttribute("user", siteUser); // Add this line
        }
        return "user-info";
    }



    @PutMapping("/myprofile")
    public RedirectView editUserInfo(Principal p, Model m, String username, String firstName, String lastName, String email, String password, RedirectAttributes redir) {
        System.out.println("Received username: " + username);
        if ((p != null) && (p.getName().equals(username))) {
            SiteUser siteUser = siteUserRepository.findByUsername(username);
            System.out.println("Found user: " + siteUser);
            m.addAttribute("id", siteUser.getId());
            siteUser.setUsername(username);
            siteUser.setFirstName(firstName);
            siteUser.setLastName(lastName);
            siteUser.setEmail(email);
            siteUserRepository.save(siteUser);
            System.out.println("User saved successfully");
        } else {
            redir.addFlashAttribute("errorMessage", "You are not authorized to modify another user's information.");
        }
        return new RedirectView("/myprofile");
    }

}

package com.Ricepify.Controllers;

import com.Ricepify.Models.SiteUserEntity;
import com.Ricepify.Repositories.SiteUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.view.RedirectView;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
public class SiteUserController {

    @Autowired
    SiteUserRepository siteUserRepository;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Autowired
    private HttpServletRequest request;

    @GetMapping("/login")
    public String getLoginPage() {
        return "login";
    }

    @PostMapping("/login")
    public RedirectView loggedInUser(HttpServletRequest request, String username, String password){
        SiteUserEntity siteUserEntity = siteUserRepository.findByUsername(username);
        if((siteUserEntity == null)
                || !(BCrypt.checkpw(password, siteUserEntity.getPassword())))
        {
            return new RedirectView("/login");
        }
        HttpSession httpSession= request.getSession();
        httpSession.setAttribute("username", username);
        return new RedirectView("/securedHome");
    }

    @GetMapping("/signup")
    public String getSignupPage() {
        return "signup";
    }

    @PostMapping("/signup")
    public RedirectView createUser(String username, String firstName, String lastName, String email, String password,String bio, String image) {
        if (!password.matches("^(?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[!@#$%^&*(),.?\":{}|<>]).{8,}$")) {
            return new RedirectView("/signup?error=invalidPassword");
        }
        SiteUserEntity siteUserEntity = new SiteUserEntity();
        siteUserEntity.setUsername(username);
        String encPass = passwordEncoder.encode(password);
        siteUserEntity.setPassword(encPass);
        siteUserEntity.setFirstName(firstName);
        siteUserEntity.setLastName(lastName);
        siteUserEntity.setEmail(email);
        siteUserEntity.setBio(bio);
        if (siteUserEntity.getBio() == null || siteUserEntity.getBio().isEmpty()) {
            siteUserEntity.setBio("Update Your Bio ...");
        }
        if (siteUserEntity.getImage() == null || siteUserEntity.getImage().isEmpty()) {
            siteUserEntity.setImage("https://upload.wikimedia.org/wikipedia/commons/thumb/b/b5/Windows_10_Default_Profile_Picture.svg/2048px-Windows_10_Default_Profile_Picture.svg.png");
        }
        siteUserEntity.setFollowersCount(0);
        siteUserEntity.setFollowingCount(0);
        System.out.println("Bio: " + siteUserEntity.getBio());
        System.out.println("Image: " + siteUserEntity.getImage());

        siteUserRepository.save(siteUserEntity);
        authWithServRequest(username, password);
        return new RedirectView("/login");
    }

    public void authWithServRequest(String username, String password) {
        try {
            request.login(username, password);
        } catch (ServletException e) {
            e.printStackTrace();
        }
    }

}
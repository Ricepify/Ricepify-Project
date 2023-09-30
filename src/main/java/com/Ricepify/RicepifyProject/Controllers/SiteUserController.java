package com.Ricepify.RicepifyProject.Controllers;

import com.Ricepify.RicepifyProject.Models.SiteUser;
import com.Ricepify.RicepifyProject.Repositories.SiteUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.view.RedirectView;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import java.util.Date;

@Controller
public class SiteUserController {

    @Autowired
    SiteUserRepository siteUserRepository;
    @Autowired
    PasswordEncoder passwordEncoder;
    @Autowired
    private HttpServletRequest request;
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private UserDetailsService userDetailsService;
    
    @GetMapping("/signup")
    public String getSignupPage() {
        return "signup.html";
    }

    @PostMapping("/signup")
    public RedirectView createUser(String userName, String firstName, String lastName, String email, String password) {
        SiteUser siteUser = new SiteUser();
        siteUser.setUserName(userName);

        String encPass=passwordEncoder.encode(password);
        siteUser.setPassword(encPass);
        siteUser.setFirstName(firstName);
        siteUser.setLastName(lastName);
        siteUser.setEmail(email);

        siteUserRepository.save(siteUser);
        authWithServRequest(userName, password);
        return new RedirectView("/");
    }
    public void authWithServRequest(String username, String password) {
        try {
            request.login(username, password);
        } catch (ServletException e) {
            e.printStackTrace();
        }
    }
}

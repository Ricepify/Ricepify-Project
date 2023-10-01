package com.Ricepify.Controllers;

import com.Ricepify.Models.SiteUser;
import com.Ricepify.Repositories.SiteUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.view.RedirectView;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

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

    @GetMapping("/signup")
    public String getSignupPage() {
        return "signup";
    }

    @PostMapping("/signup")
    public RedirectView createUser(String username, String firstName, String lastName, String email, String password) {
        SiteUser siteUser = new SiteUser();
        siteUser.setUsername(username);

        String encPass = passwordEncoder.encode(password);
        siteUser.setPassword(encPass);
        siteUser.setFirstName(firstName);
        siteUser.setLastName(lastName);
        siteUser.setEmail(email);

        siteUserRepository.save(siteUser);
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


//
//    @Autowired
//    SiteUserRepository siteUserRepository;
//    @Autowired
//    PasswordEncoder passwordEncoder;
//    @Autowired
//    private HttpServletRequest request;
//    @Autowired
//    private AuthenticationManager authenticationManager;
//    @Autowired
//    private UserDetailsService userDetailsService;
//
//    @GetMapping("/login")
//    public String getLoginPage() {
//        return "login.html";
//    }
//    @GetMapping("/logout")
//    public String getLogoutPage() {
//        return "index.html";
//    }
//    @GetMapping("/signup")
//    public String getSignupPage() {
//        return "signup.html";
//    }
//    @PostMapping("/signup")
//    public RedirectView createUser(String username, String firstName, String lastName, String email, String password) {
//        SiteUser siteUser = new SiteUser();
//        siteUser.setUsername(username);
//
//        String encPass = passwordEncoder.encode(password);
//        siteUser.setPassword(encPass);
//        siteUser.setFirstName(firstName);
//        siteUser.setLastName(lastName);
//        siteUser.setEmail(email);
//
//        siteUserRepository.save(siteUser);
//        authWithServRequest(username, password);
//        return new RedirectView("/");
//    }
//    @GetMapping("/")
//    public String getHomePage(Principal p, Model m){
//
//        if(p != null){
//            String username = p.getName();
//            // SiteUser siteUser= SiteUserRepository.findByUsername(username);
//            SiteUser siteUser= (SiteUser) siteUserRepository.findByUsername(username);
//
//            m.addAttribute("username", username);
//        }
//
//        return "index.html";
//    }
//    public void authWithServRequest(String username, String password) {
//        try {
//            request.login(username, password);
//        } catch (ServletException e) {
//            e.printStackTrace();
//        }
}
//}
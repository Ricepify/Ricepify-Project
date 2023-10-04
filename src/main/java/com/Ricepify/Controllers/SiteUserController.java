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
    public RedirectView createUser(String username, String firstName, String lastName, String email, String password) {
        SiteUserEntity siteUserEntity = new SiteUserEntity();
        siteUserEntity.setUsername(username);

        String encPass = passwordEncoder.encode(password);
        siteUserEntity.setPassword(encPass);
        siteUserEntity.setFirstName(firstName);
        siteUserEntity.setLastName(lastName);
        siteUserEntity.setEmail(email);
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
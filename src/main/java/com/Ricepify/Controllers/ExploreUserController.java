//TODOFARAH.
//package com.Ricepify.Controllers;
//
//import com.Ricepify.Models.SiteUser;
//import com.Ricepify.Repositories.SiteUserRepository;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PathVariable;
//import org.springframework.web.servlet.view.RedirectView;
//
//import java.security.Principal;
//import java.util.List;
//
//@Controller
//public class ExploreUserController {
//    @Autowired
//    SiteUserRepository siteUserRepository;
//    @GetMapping("/findUsers")
//    public String getAllUsers(Principal p, Model model) {
//        if (p != null) {
//            String username = p.getName();
//            SiteUser loggedInUser = siteUserRepository.findByUsername(username);
//            List<SiteUser> users = siteUserRepository.findAllExceptUser(loggedInUser);
//            model.addAttribute("users", users);
//        }
//        return "findUser";
//    }
//
////    @GetMapping("/userinfo/{id}")
////    public String getUserInfo(@PathVariable Long id, Model model) {
////        SiteUserModel siteUserModel = siteUserRepository.findById(id).orElse(null);
////
////        if (siteUserModel != null) {
////            model.addAttribute("user", siteUserModel);
////            return "otherProfileinfo";
////        } else {
////            return "redirect:/";
////        }
////    }
//
//    @GetMapping("/userinfo")
//    public String getUserInfoPage(Principal p, Model model) {
//        if (p != null) {
//            String username = p.getName();
//            SiteUser siteUserModel = siteUserRepository.findByUsername(username);
//            model.addAttribute("user", siteUserModel);
//        }
//        return "findUser";
//    }
//
////    @GetMapping("/follow/{id}")
////    public String GetAllUser(Model model){
////
////        final String currentUser = SecurityContextHolder.getContext().getAuthentication().getName();
////        SiteUserModel appUser = siteUserRepository.findByUsername(currentUser);
////        // Remove the current user From the users List
////        List<SiteUserModel> allUser = siteUserRepository.findAll();
////        allUser.remove(appUser);
////        for (int i = 0; i < allUser.size(); i++) {
////            if (appUser.getFollowing().contains(allUser.get(i))) {
////                allUser.get(i).setFlag(false);
////            } else {
////                allUser.get(i).setFlag(true);
////            }
////
////        }
////        model.addAttribute("usersList",allUser);
////        model.addAttribute("username",currentUser);
//////        model.addAttribute("user", siteUserModel);
////
////        return"otherProfileinfo";
////}
//
////    @PostMapping("/follow/{id}")
////    public RedirectView followUser(Principal p, @PathVariable Long id) {
////        SiteUserModel currentUser = siteUserRepository.findByUsername(p.getName());
////        SiteUserModel userToFollow = siteUserRepository.findById(id).orElse(null);
////        if (userToFollow != null) {
////            if (currentUser.getFollowing().contains(userToFollow)) {
////                currentUser.getFollowing().remove(userToFollow);
////            } else {
////                currentUser.getFollowing().add(userToFollow);
////            }
////            siteUserRepository.save(currentUser);
////        }
////        return new RedirectView("/userinfo/" + id);
////    }
//
//    @GetMapping("/follow/{userId}")
//    public RedirectView followUser(Model m, Principal p, @PathVariable(value = "userId") Long userId){
//        if (p != null){
//            String username = p.getName();
//            SiteUser myApplicationUser = siteUserRepository.findByUsername(username);
//            SiteUser followedApplicationUser = siteUserRepository.findById(userId).orElseThrow();
//            myApplicationUser.getFollowers().add(followedApplicationUser);
//            siteUserRepository.save(myApplicationUser);
//        }
//        return new RedirectView("/userinfo/" + userId);
//    }
//
//    @GetMapping("/userinfo/{userId}")
//    public String getUserProfile(Model m , Principal p , @PathVariable(value = "userId") Long userId){
//        if (p != null){
//            String username = p.getName();
//            SiteUser myApplicationUser = siteUserRepository.findByUsername(username);
//            SiteUser applicationUser = siteUserRepository.findById(userId).orElseThrow();
//
//            boolean isFollowed = myApplicationUser.getFollowers().stream().anyMatch(user -> user.getId() == userId) ;
//
////            SetPost posts = applicationUser.getPosts();
//            m.addAttribute("applicationUser" , applicationUser);
////            m.addAttribute("posts" , posts);
//            m.addAttribute("isFollowed" , isFollowed);
//        }
//        return "otherProfileinfo";
//    }
//
//
//
//}

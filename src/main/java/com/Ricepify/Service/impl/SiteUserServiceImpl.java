package com.Ricepify.Service.impl;

import com.Ricepify.Models.SiteUserEntity;
import com.Ricepify.Repositories.SiteUserRepository;
import com.Ricepify.Service.SiteUserService;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.security.Principal;
import java.util.List;

@Service
public class SiteUserServiceImpl implements SiteUserService {

    private final SiteUserRepository siteUserRepository;

    public SiteUserServiceImpl(SiteUserRepository siteUserRepository) {
        this.siteUserRepository = siteUserRepository;
    }

    @Override
    public SiteUserEntity getUserById(Long id) {
        if (siteUserRepository.findById(id).isPresent()) {
            return siteUserRepository.findById(id).get();
        }else {
            throw new RuntimeException("user not found");
        }

    }

    @Override
    public SiteUserEntity getUserByUsername(String username) {

        return siteUserRepository.findByUsername(username);
    }

    @Override
    public void editUserInfo(Principal p, Model m, String username, String firstName, String lastName, String email, String image, String bio, RedirectAttributes redir) {
        System.out.println("Received username: " + username);
        if ((p != null) && (p.getName().equals(username))) {
            SiteUserEntity siteUserEntity = siteUserRepository.findByUsername(username);
            System.out.println("Found user: " + siteUserEntity);
            m.addAttribute("id", siteUserEntity.getId());
            siteUserEntity.setUsername(username);
            siteUserEntity.setFirstName(firstName);
            siteUserEntity.setLastName(lastName);
            siteUserEntity.setEmail(email);
            siteUserEntity.setImage(image);
            siteUserEntity.setBio(bio);
            siteUserRepository.save(siteUserEntity);
            System.out.println("User saved successfully");
        } else {
            redir.addFlashAttribute("errorMessage", "You are not authorized to modify another user's information.");
        }
    }

    @Override
    public List<SiteUserEntity> getRandomUsers(Long loggedInUser , int limit) {
        return siteUserRepository.findRandomUsersExceptUser(loggedInUser , limit);
    }

    @Override
    public List<SiteUserEntity> getUsersByFirstName(String query) {
        return siteUserRepository.findByFirstNameContainingIgnoreCase(query);
    }

    @Override
    public boolean isUserFollowedBy(SiteUserEntity follower, SiteUserEntity targetUser) {
        return follower.getFollowing().contains(targetUser);
    }

    @Override
    public int getFollowersCount(SiteUserEntity followersUsers) {
        return followersUsers.getFollowers().size();
    }

    @Override
    public int getFollowingUsers(SiteUserEntity followingUsers) {
        return followingUsers.getFollowing().size();
    }

    @Override
    public void followUser(Principal p, Long id) {
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
    }

}
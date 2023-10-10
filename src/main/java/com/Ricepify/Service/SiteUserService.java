package com.Ricepify.Service;

import com.Ricepify.Models.SiteUserEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.security.Principal;
import java.util.List;

public interface SiteUserService {

    SiteUserEntity getUserById(Long id);

    SiteUserEntity getUserByUsername(String username);
    void editUserInfo(Principal p,
                      Model m,
                      String username,
                      String firstName,
                      String lastName,
                      String email,
                      String image,
                      String bio,
                      RedirectAttributes redir);
    List<SiteUserEntity> getRandomUsers(Long loggedInUser , int limit);

    List<SiteUserEntity> getUsersByFirstName(String query);

    boolean isUserFollowedBy(SiteUserEntity follower, SiteUserEntity targetUser);

    int getFollowersCount(SiteUserEntity followersUsers);

    int getFollowingUsers(SiteUserEntity followingUsers);

    void followUser(Principal p, @PathVariable Long id);

}

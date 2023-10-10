package com.Ricepify.Models;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.List;
import java.util.Set;

public class SiteUserEntityBuilder {
    private String username;
    private String password;
    private String firstName;
    private String lastName;
    private String email;
    private String image;
    private String bio;
    private Integer followersCount;
    private Integer followingCount;
    private List<RecipeEntity> recipeEntities;
    private List<RecipeComment> userRecipeComments;
    private Set<SiteUserEntity> following;
    private Set<SiteUserEntity> followers;

    private BCryptPasswordEncoder passwordEncoder;

    public SiteUserEntityBuilder(BCryptPasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    public SiteUserEntityBuilder() {
    }

    public SiteUserEntityBuilder setUsername(String username) {
        this.username = username;
        return this;
    }

    public SiteUserEntityBuilder setPassword(String password) {
        this.password = password;
        return this;
    }

    public SiteUserEntityBuilder setFirstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    public SiteUserEntityBuilder setLastName(String lastName) {
        this.lastName = lastName;
        return this;
    }

    public SiteUserEntityBuilder setEmail(String email) {
        this.email = email;
        return this;
    }

    public SiteUserEntityBuilder setImage(String image) {
        this.image = image;
        return this;
    }

    public SiteUserEntityBuilder setBio(String bio) {
        this.bio = bio;
        return this;
    }

    public SiteUserEntityBuilder setFollowersCount(Integer followersCount) {
        this.followersCount = followersCount;
        return this;
    }

    public SiteUserEntityBuilder setFollowingCount(Integer followingCount) {
        this.followingCount = followingCount;
        return this;
    }

    public SiteUserEntityBuilder setRecipeEntities(List<RecipeEntity> recipeEntities) {
        this.recipeEntities = recipeEntities;
        return this;
    }

    public SiteUserEntityBuilder setUserRecipeComments(List<RecipeComment> userRecipeComments) {
        this.userRecipeComments = userRecipeComments;
        return this;
    }

    public SiteUserEntityBuilder setFollowing(Set<SiteUserEntity> following) {
        this.following = following;
        return this;
    }

    public SiteUserEntityBuilder setFollowers(Set<SiteUserEntity> followers) {
        this.followers = followers;
        return this;
    }

    public SiteUserEntity build() {
        SiteUserEntity user = new SiteUserEntity();
        user.setUsername(this.username);
        user.setPassword(passwordEncoder.encode(this.password));
        user.setFirstName(this.firstName);
        user.setLastName(this.lastName);
        user.setEmail(this.email);
        user.setImage(this.image);
        user.setBio(this.bio);
        user.setFollowersCount(this.followersCount);
        user.setFollowingCount(this.followingCount);
        user.setRecipeEntities(this.recipeEntities);
        user.setUserRecipeComments(this.userRecipeComments);
        user.setFollowing(this.following);
        user.setFollowers(this.followers);
        return user;
    }
}

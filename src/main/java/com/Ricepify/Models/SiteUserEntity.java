package com.Ricepify.Models;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
//@Table(name = "SiteUser")
public class SiteUserEntity  implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String username;
    private String password;
    private String firstName;
    private String lastName;
    private String email;
    private String image;
    private String bio;
    @OneToMany(mappedBy="siteUserEntity")
    private List<RecipeEntity> recipeEntities;

    @OneToMany(mappedBy = "siteUserEntity")
    private List<RecipeComment> userRecipeComments;

    @OneToMany(mappedBy="siteUserEntity")
    private List<RecipeFavoriteEntity> recipeFavoriteEntity;

    @ManyToMany
    @JoinTable(
            name = "user_following",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "following_id")
    )
    private Set<SiteUserEntity> following;
    @ManyToMany(mappedBy = "following")
    private Set<SiteUserEntity> followers = new HashSet<>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<RecipeEntity> getRecipeEntities() {
        return recipeEntities;
    }

    public void setRecipeEntities(List<RecipeEntity> recipeEntities) {
        this.recipeEntities = recipeEntities;
    }

    public List<RecipeFavoriteEntity> getRecipeFavoriteEntity() {
        return recipeFavoriteEntity;
    }

    public void setRecipeFavoriteEntity(List<RecipeFavoriteEntity> recipeFavoriteEntity) {
        this.recipeFavoriteEntity = recipeFavoriteEntity;
    }

    public List<RecipeComment> getUserRecipeComments() {
        return userRecipeComments;
    }

    public void setUserRecipeComments(List<RecipeComment> userRecipeComments) {
        this.userRecipeComments = userRecipeComments;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    public Set<SiteUserEntity> getFollowing() {
        return following;
    }

    public void setFollowing(Set<SiteUserEntity> following) {
        this.following = following;
    }

    public Set<SiteUserEntity> getFollowers() {
        return followers;
    }

    public void setFollowers(Set<SiteUserEntity> followers) {
        this.followers = followers;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getBio() {
        return bio;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }
}

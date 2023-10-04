package com.Ricepify.Models;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.Collection;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "SiteUser")
public class SiteUserEntity  implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String username;
    private String password;
    private String firstName;
    private String lastName;
    private String email;
    @OneToMany(mappedBy="siteUserEntity")
    private List<RecipeEntity> recipeEntities;

    @OneToMany(mappedBy = "siteUserEntity")
    private List<RecipeComment> userRecipeComments;

    @OneToMany(mappedBy="siteUserEntity")
    private List<RecipeFavoriteEntity> recipeFavoriteEntity;

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
        return null;
    }

    @Override
    public String getUsername() {
        return null;
    }

    @Override
    public boolean isAccountNonExpired() {
        return false;
    }

    @Override
    public boolean isAccountNonLocked() {
        return false;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return false;
    }

    @Override
    public boolean isEnabled() {
        return false;
    }

}

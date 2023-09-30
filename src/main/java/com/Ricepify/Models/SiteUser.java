package com.Ricepify.RicepifyProject.Models;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.Collection;
import java.util.List;

@Entity
public class SiteUser  implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String username;
    private String FirstName;
    private String LastName;
    private String Email;
    private String Password;
    @OneToMany(mappedBy = "siteUser", cascade = CascadeType.ALL)
    private List<Recipe> recipe;
    public List<Recipe>getRecipe()
    {
        return recipe;
    }

    @OneToMany(mappedBy = "siteUser", cascade = CascadeType.ALL)
    private List<RecipeInteraction> recipeInteractions;
    public List<RecipeInteraction> getRecipeInteractions() {
        return recipeInteractions;
    }
    public SiteUser() {
    }

    public SiteUser(String username, String firstName, String lastName, String email, String password) {
        this.username = username;
        this.FirstName = firstName;
        this.LastName = lastName;
        this.Email = email;
        this.Password = password;
    }
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    public String getPassword() {
        return Password;
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

    public Long getId() {
        return id;
    }


    @Override
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getFirstName() {
        return FirstName;
    }

    public void setFirstName(String firstName) {
        FirstName = firstName;
    }

    public String getLastName() {
        return LastName;
    }

    public void setLastName(String lastName) {
        LastName = lastName;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }


    public void setPassword(String password) {
        Password = password;
    }
}

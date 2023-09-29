package com.Ricepify.RicepifyProject.Models;

import javax.persistence.*;
import java.util.List;

@Entity
public class SiteUser {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String UserName;
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

    public SiteUser(String userName, String firstName, String lastName, String email, String password) {
        UserName = userName;
        FirstName = firstName;
        LastName = lastName;
        Email = email;
        Password = password;
    }

    public Long getId() {
        return id;
    }

    public String getUserName() {
        return UserName;
    }

    public void setUserName(String userName) {
        UserName = userName;
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

    public String getPassword() {
        return Password;
    }

    public void setPassword(String password) {
        Password = password;
    }
}

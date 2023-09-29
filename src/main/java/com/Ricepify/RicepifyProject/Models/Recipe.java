package com.Ricepify.RicepifyProject.Models;

import javax.persistence.*;
import java.util.List;

@Entity
public class Recipe {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long RecipeId;
    private String RecipeTitle;
    private String RecipeImage;
    private String RecipeDescription;
    private String RecipeCategory;
    private String RecipeArea;
    @ManyToOne
    private SiteUser siteUser;

    @OneToMany(mappedBy = "recipe", cascade = CascadeType.ALL)
    private List<RecipeInteraction> recipeInteractions;
    public List<RecipeInteraction> getRecipeInteractions() {
        return recipeInteractions;
    }

    public Recipe() {
    }

    public Recipe(String recipeTitle, String recipeImage, String recipeDescription, String recipeCategory, String recipeArea, SiteUser siteUser) {
        RecipeTitle = recipeTitle;
        RecipeImage = recipeImage;
        RecipeDescription = recipeDescription;
        RecipeCategory = recipeCategory;
        RecipeArea = recipeArea;
        this.siteUser = siteUser;
    }

    public Long getRecipeId() {
        return RecipeId;
    }

    public String getRecipeTitle() {
        return RecipeTitle;
    }

    public void setRecipeTitle(String recipeTitle) {
        RecipeTitle = recipeTitle;
    }

    public String getRecipeImage() {
        return RecipeImage;
    }

    public void setRecipeImage(String recipeImage) {
        RecipeImage = recipeImage;
    }

    public String getRecipeDescription() {
        return RecipeDescription;
    }

    public void setRecipeDescription(String recipeDescription) {
        RecipeDescription = recipeDescription;
    }

    public String getRecipeCategory() {
        return RecipeCategory;
    }

    public void setRecipeCategory(String recipeCategory) {
        RecipeCategory = recipeCategory;
    }

    public String getRecipeArea() {
        return RecipeArea;
    }

    public void setRecipeArea(String recipeArea) {
        RecipeArea = recipeArea;
    }

    public SiteUser getSiteUser() {
        return siteUser;
    }

    public void setSiteUser(SiteUser siteUser) {
        this.siteUser = siteUser;
    }
}

package com.Ricepify.Models;

import javax.persistence.*;
import java.util.List;

@Entity
public class Recipe {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long RecipeId;
    private String recipeTitle;
    private String recipeImage;
    private String recipeDescription;
    private String recipeCategory;
    private String recipeArea;
    private String recipeMode;

    @ManyToOne
    private SiteUser siteUser;
    @OneToMany(mappedBy = "recipe", cascade = CascadeType.ALL)
    private List<RecipeInteraction> recipeInteractions;

    public Recipe(String recipeTitle, String recipeImage, String recipeDescription, String recipeCategory, String recipeArea, String recipeMode, SiteUser siteUser, List<RecipeInteraction> recipeInteractions) {
        this.recipeTitle = recipeTitle;
        this.recipeImage = recipeImage;
        this.recipeDescription = recipeDescription;
        this.recipeCategory = recipeCategory;
        this.recipeArea = recipeArea;
        this.recipeMode = recipeMode;
        this.siteUser = siteUser;
        this.recipeInteractions = recipeInteractions;
    }

    public Long getRecipeId() {
        return RecipeId;
    }

    public void setRecipeId(Long recipeId) {
        RecipeId = recipeId;
    }

    public String getRecipeTitle() {
        return recipeTitle;
    }

    public void setRecipeTitle(String recipeTitle) {
        this.recipeTitle = recipeTitle;
    }

    public String getRecipeImage() {
        return recipeImage;
    }

    public void setRecipeImage(String recipeImage) {
        this.recipeImage = recipeImage;
    }

    public String getRecipeDescription() {
        return recipeDescription;
    }

    public void setRecipeDescription(String recipeDescription) {
        this.recipeDescription = recipeDescription;
    }

    public String getRecipeCategory() {
        return recipeCategory;
    }

    public void setRecipeCategory(String recipeCategory) {
        this.recipeCategory = recipeCategory;
    }

    public String getRecipeArea() {
        return recipeArea;
    }

    public void setRecipeArea(String recipeArea) {
        this.recipeArea = recipeArea;
    }

    public String getRecipeMode() {
        return recipeMode;
    }

    public void setRecipeMode(String recipeMode) {
        this.recipeMode = recipeMode;
    }

    public SiteUser getSiteUser() {
        return siteUser;
    }

    public void setSiteUser(SiteUser siteUser) {
        this.siteUser = siteUser;
    }

    public List<RecipeInteraction> getRecipeInteractions() {
        return recipeInteractions;
    }

    public void setRecipeInteractions(List<RecipeInteraction> recipeInteractions) {
        this.recipeInteractions = recipeInteractions;
    }
}

package com.Ricepify.Models;

import java.time.LocalDate;

public class RecipeEntityBuilder {
    private String recipeTitle;
    private String recipeImage;
    private String recipeVideo;
    private String recipeDescription;
    private String recipeCategory;
    private String recipeArea;
    private String recipeMode;
    private LocalDate createdAt;
    private SiteUserEntity siteUserEntity;

    public RecipeEntityBuilder() {}

    public RecipeEntityBuilder setRecipeTitle(String recipeTitle) {
        this.recipeTitle = recipeTitle;
        return this;
    }

    public RecipeEntityBuilder setRecipeImage(String recipeImage) {
        this.recipeImage = recipeImage;
        return this;
    }

    public RecipeEntityBuilder setRecipeVideo(String recipeVideo) {
        this.recipeVideo = recipeVideo;
        return this;
    }

    public RecipeEntityBuilder setRecipeDescription(String recipeDescription) {
        this.recipeDescription = recipeDescription;
        return this;
    }

    public RecipeEntityBuilder setRecipeCategory(String recipeCategory) {
        this.recipeCategory = recipeCategory;
        return this;
    }

    public RecipeEntityBuilder setRecipeArea(String recipeArea) {
        this.recipeArea = recipeArea;
        return this;
    }

    public RecipeEntityBuilder setRecipeMode(String recipeMode) {
        this.recipeMode = recipeMode;
        return this;
    }

    public RecipeEntityBuilder setCreatedAt(LocalDate createdAt) {
        this.createdAt = createdAt;
        return this;
    }

    public RecipeEntityBuilder setSiteUserEntity(SiteUserEntity siteUserEntity) {
        this.siteUserEntity = siteUserEntity;
        return this;
    }

    public RecipeEntity build() {
        RecipeEntity recipeEntity = new RecipeEntity();
        recipeEntity.setRecipeTitle(this.recipeTitle);
        recipeEntity.setRecipeImage(this.recipeImage);
        recipeEntity.setRecipeVideo(this.recipeVideo);
        recipeEntity.setRecipeDescription(this.recipeDescription);
        recipeEntity.setRecipeCategory(this.recipeCategory);
        recipeEntity.setRecipeArea(this.recipeArea);
        recipeEntity.setRecipeMode(this.recipeMode);
        recipeEntity.setCreatedAt(this.createdAt);
        recipeEntity.setSiteUserEntity(this.siteUserEntity);
        return recipeEntity;
    }
}

package com.Ricepify.Models;

import javax.persistence.*;
import java.util.List;

@Entity
public class RecipeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long recipeId;
    private String recipeTitle;
    private String recipeImage;
    private String recipeVideo;
    private String recipeDescription;
    private String recipeCategory;
    private String recipeArea;
    private String recipeMode;

    @ManyToOne
    @JoinColumn(name="site_user_id", nullable=false)
    private SiteUserEntity siteUserEntity;

    @OneToOne(mappedBy = "recipeEntity")
    private RecipeFavoriteEntity recipeFavoriteEntity;

    @OneToMany(mappedBy="recipeEntity")
    private List<RecipeComment> recipeComments;

    public Long getRecipeId() {
        return recipeId;
    }

    public void setRecipeId(Long recipeId) {
        this.recipeId = recipeId;
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

    public String getRecipeVideo() {
        return recipeVideo;
    }

    public void setRecipeVideo(String recipeVideo) {
        this.recipeVideo = recipeVideo;
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

    public SiteUserEntity getSiteUserEntity() {
        return siteUserEntity;
    }

    public void setSiteUserEntity(SiteUserEntity siteUserEntity) {
        this.siteUserEntity = siteUserEntity;
    }

    public RecipeFavoriteEntity getRecipeFavoriteEntity() {
        return recipeFavoriteEntity;
    }

    public void setRecipeFavoriteEntity(RecipeFavoriteEntity recipeFavoriteEntity) {
        this.recipeFavoriteEntity = recipeFavoriteEntity;
    }

    public List<RecipeComment> getRecipeComments() {
        return recipeComments;
    }

    public void setRecipeComments(List<RecipeComment> recipeComments) {
        this.recipeComments = recipeComments;
    }

    @Override
    public String toString() {
        return "RecipeEntity{" +
                "recipeId=" + recipeId +
                ", recipeTitle='" + recipeTitle + '\'' +
                ", recipeImage='" + recipeImage + '\'' +
                ", recipeVideo='" + recipeVideo + '\'' +
                ", recipeDescription='" + recipeDescription + '\'' +
                ", recipeCategory='" + recipeCategory + '\'' +
                ", recipeArea='" + recipeArea + '\'' +
                ", recipeMode='" + recipeMode + '\'' +
                ", siteUserEntity=" + siteUserEntity +
                ", recipeFavoriteEntity=" + recipeFavoriteEntity +
                ", recipeComments=" + recipeComments +
                '}';
    }
}

package com.Ricepify.RicepifyProject.Models;

import javax.persistence.*;

@Entity
public class RecipeInteraction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long RecipeInteractionId;
    private String Comment;
    private int Rate;

    @ManyToOne
    private Recipe recipe;

    @ManyToOne
    private SiteUser siteUser;

    public RecipeInteraction() {
    }

    public RecipeInteraction(String comment, int rate, Recipe recipe) {
        Comment = comment;
        Rate = rate;
        this.recipe = recipe;
    }

    public Long getRecipeInteractionId() {
        return RecipeInteractionId;
    }

    public String getComment() {
        return Comment;
    }

    public void setComment(String comment) {
        Comment = comment;
    }

    public int getRate() {
        return Rate;
    }

    public void setRate(int rate) {
        Rate = rate;
    }

    public Recipe getRecipe() {
        return recipe;
    }

    public void setRecipe(Recipe recipe) {
        this.recipe = recipe;
    }
    public SiteUser getSiteUser() {
        return siteUser;
    }

    public void setSiteUser(SiteUser siteUser) {
        this.siteUser = siteUser;
    }
}

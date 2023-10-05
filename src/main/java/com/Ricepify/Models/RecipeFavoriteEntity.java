package com.Ricepify.Models;

import javax.persistence.*;

@Entity
//@Table(name = "recipe_favorites")
public class RecipeFavoriteEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name="site_user_id", nullable=false)
    private SiteUserEntity siteUserEntity;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "recipe_fav_id", referencedColumnName = "recipeId")
    private RecipeEntity recipeEntity;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public SiteUserEntity getSiteUserEntity() {
        return siteUserEntity;
    }

    public void setSiteUserEntity(SiteUserEntity siteUserEntity) {
        this.siteUserEntity = siteUserEntity;
    }

    public RecipeEntity getRecipeEntity() {
        return recipeEntity;
    }

    public void setRecipeEntity(RecipeEntity recipeEntity) {
        this.recipeEntity = recipeEntity;
    }

    @Override
    public String toString() {
        return "RecipeFavoriteEntity{" +
                "id=" + id +
                ", siteUserEntity=" + siteUserEntity +
                ", recipeEntity=" + recipeEntity +
                '}';
    }
}

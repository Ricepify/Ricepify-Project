package com.Ricepify.Models;

import javax.persistence.*;

@Entity
//@Table(name = "recipe_favorites")
public class RecipeFavoriteEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long mealId;
    @ManyToOne
    @JoinColumn(name="site_user_id", nullable=false)
    private SiteUserEntity siteUserEntity;

    public RecipeFavoriteEntity() {
    }

    public void setMealId(Long mealId) {
        this.mealId = mealId;
    }

    public void setSiteUserEntity(SiteUserEntity siteUserEntity) {
        this.siteUserEntity = siteUserEntity;
    }

    public Long getMealId() {
        return mealId;
    }

    public SiteUserEntity getSiteUserEntity() {
        return siteUserEntity;
    }
}

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


}


package com.Ricepify.Models;

import lombok.*;
import javax.persistence.*;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ExternalRecipeFavoriteEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long mealId;

    @ManyToOne
    @JoinColumn(name="site_user_id", nullable=false)
    private SiteUserEntity siteUserEntity;

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
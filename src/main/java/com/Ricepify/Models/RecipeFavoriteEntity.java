package com.Ricepify.Models;

import lombok.*;
import javax.persistence.*;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
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
}

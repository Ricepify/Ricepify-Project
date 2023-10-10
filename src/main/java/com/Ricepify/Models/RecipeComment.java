package com.Ricepify.Models;

import lombok.*;
import javax.persistence.*;

@Entity
@Table(name = "recipe_comment")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class RecipeComment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String comment;

    @ManyToOne
    @JoinColumn(name="site_user_id", nullable=false)
    private SiteUserEntity siteUserEntity;

    @ManyToOne
    @JoinColumn(name="recipe_id", nullable=false)
    private RecipeEntity recipeEntity;
}

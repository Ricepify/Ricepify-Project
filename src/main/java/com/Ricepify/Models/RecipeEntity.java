package com.Ricepify.Models;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Entity
@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
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
    private LocalDate createdAt;

    @ManyToOne
    @JoinColumn(name="site_user_id", nullable=false)
    private SiteUserEntity siteUserEntity;

    @OneToMany(mappedBy="recipeEntity")
    private List<RecipeComment> recipeComments;
}

package com.Ricepify.Repositories;

import com.Ricepify.Models.RecipeInteraction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RecipeInteractionRepository extends JpaRepository<RecipeInteraction,Long> {
    //  ZAID  FIXME NEED SOME REFACTOR
    List<RecipeInteraction> findByRecipeId(Long recipeId);

}

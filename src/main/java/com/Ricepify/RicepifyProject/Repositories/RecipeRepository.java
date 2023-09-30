package com.Ricepify.RicepifyProject.Repositories;

import com.Ricepify.RicepifyProject.Models.Recipe;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RecipeRepository extends JpaRepository<Recipe,Long> {
}

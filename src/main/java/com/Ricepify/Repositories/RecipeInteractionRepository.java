package com.Ricepify.RicepifyProject.Repositories;

import com.Ricepify.RicepifyProject.Models.RecipeInteraction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RecipeInteractionRepository extends JpaRepository<RecipeInteraction,Long> {
}

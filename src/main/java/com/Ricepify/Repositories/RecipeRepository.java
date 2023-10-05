package com.Ricepify.Repositories;

import com.Ricepify.Models.RecipeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RecipeRepository extends JpaRepository<RecipeEntity,Long> {

}

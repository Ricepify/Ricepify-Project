package com.Ricepify.Repositories;

import com.Ricepify.Models.RecipeEntity;
import com.Ricepify.Models.SiteUserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;

@Repository
public interface RecipeRepository extends JpaRepository<RecipeEntity,Long> {
    List<RecipeEntity> findBySiteUserEntityOrderByCreatedAtDesc(SiteUserEntity user);


    RecipeEntity getById(Long id);

    List<RecipeEntity> findBySiteUserEntity(SiteUserEntity siteUserEntity);
}
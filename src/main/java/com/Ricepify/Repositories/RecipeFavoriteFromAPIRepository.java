package com.Ricepify.Repositories;

import com.Ricepify.Models.ExternalRecipeFavoriteEntity;
import com.Ricepify.Models.SiteUserEntity;
import com.Ricepify.Models.ExternalRecipeFavoriteEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Repository
public interface RecipeFavoriteFromAPIRepository extends JpaRepository<ExternalRecipeFavoriteEntity, Long> {

    @Query("SELECT ef.mealId FROM ExternalRecipeFavoriteEntity ef WHERE ef.siteUserEntity.id = :userId")
    List<Long> findAllMealIdsByUserId(Long userId);

    @Transactional
    @Modifying
    @Query("DELETE FROM ExternalRecipeFavoriteEntity e WHERE e.siteUserEntity.id = :siteUserId AND e.mealId = :mealId")
    void deleteBySiteUserAndMealId(Long siteUserId, Long mealId);
}

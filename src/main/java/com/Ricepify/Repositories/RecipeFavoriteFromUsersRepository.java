package com.Ricepify.Repositories;

import com.Ricepify.Models.InternalRecipeFavoriteEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface RecipeFavoriteFromUsersRepository extends JpaRepository<InternalRecipeFavoriteEntity, Long> {


    @Query("SELECT ef.mealId FROM InternalRecipeFavoriteEntity ef WHERE ef.siteUserEntity.id = :userId")
    List<Long> findAllMealIdsByUserId(Long userId);
    @Transactional
    @Modifying
    @Query("DELETE FROM InternalRecipeFavoriteEntity e WHERE e.siteUserEntity.id = :siteUserId AND e.mealId = :mealId")
    void deleteBySiteUserAndMealId(Long siteUserId, Long mealId);
}

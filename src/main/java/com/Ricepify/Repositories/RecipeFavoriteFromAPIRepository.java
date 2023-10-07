package com.Ricepify.Repositories;

import com.Ricepify.Models.ExternalRecipeFavoriteEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RecipeFavoriteFromAPIRepository extends JpaRepository<ExternalRecipeFavoriteEntity, Long> {


}

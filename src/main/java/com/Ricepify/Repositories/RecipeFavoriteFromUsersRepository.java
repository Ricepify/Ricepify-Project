package com.Ricepify.Repositories;

import com.Ricepify.Models.ExternalRecipeFavoriteEntity;
import com.Ricepify.Models.InternalRecipeFavoriteEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
@Repository
public interface RecipeFavoriteFromUsersRepository extends JpaRepository<InternalRecipeFavoriteEntity, Long> {

}


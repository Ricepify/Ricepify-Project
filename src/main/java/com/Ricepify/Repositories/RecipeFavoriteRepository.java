package com.Ricepify.Repositories;

import com.Ricepify.Models.RecipeFavoriteEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RecipeFavoriteRepository extends JpaRepository<RecipeFavoriteEntity , Long> {
}

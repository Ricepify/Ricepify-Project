package com.Ricepify.Repositories;

import com.Ricepify.Models.SiteUserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SiteUserRepository extends JpaRepository<SiteUserEntity,Long> {
       SiteUserEntity findByUsername(String username);
}

package com.Ricepify.Repositories;

import com.Ricepify.Models.SiteUserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.List;
@Repository
public interface SiteUserRepository extends JpaRepository<SiteUserEntity,Long> {
       SiteUserEntity findByUsername(String username);
//       @Query("SELECT u FROM SiteUserEntity u WHERE u != :loggedInUser")
//       List<SiteUserEntity> findAllExceptUser(@Param("loggedInUser") SiteUserEntity loggedInUser);
       List<SiteUserEntity> findByFirstNameContainingIgnoreCase(String firstName);
       @Query(value = "SELECT * FROM site_user_entity WHERE id <> :userId ORDER BY random() LIMIT :limit", nativeQuery = true)
       List<SiteUserEntity> findRandomUsersExceptUser(@Param("userId") Long userId, @Param("limit") int limit);
}

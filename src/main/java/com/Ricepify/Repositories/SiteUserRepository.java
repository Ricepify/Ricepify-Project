package com.Ricepify.RicepifyProject.Repositories;

import com.Ricepify.RicepifyProject.Models.SiteUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Repository;

@Repository
public interface SiteUserRepository extends JpaRepository<SiteUser,Long> {
       public UserDetails findByUsername(String username) ;
//    public static SiteUser findByUsername(String username) {
//        return null;
//    }

}

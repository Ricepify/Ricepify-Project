package com.Ricepify.RicepifyProject.Repositories;

import com.Ricepify.RicepifyProject.Models.SiteUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SiteUserRepository extends JpaRepository<SiteUser,Long> {
}

package com.Ricepify.Repositories;

import com.Ricepify.Models.Subscrip;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SubscripRepo extends JpaRepository<Subscrip,Long> {

//    List<String> findAllemail();
//    Subscrip findAllEmails(String email);
//    List<String> allEmails = userRepository.findAllEmails();
    List<Subscrip> findAllByOrderByEmailAsc();
}

package com.Ricepify.Controllers;

import com.Ricepify.Models.Subscrip;
import com.Ricepify.Repositories.SubscripRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.view.RedirectView;


//import org.springframework.mail.javamail.JavaMailSender;
//import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import java.util.List;

@Controller
public class SubController {
    @Autowired
    SubscripRepo subscripRepo;

    @GetMapping("/sub")
    public String getSub(){
        Subscrip a = new Subscrip();
        List<Subscrip> ad= subscripRepo.findAllByOrderByEmailAsc();
        System.out.println(ad.size());
        return "/sub.html";
    }

    @PostMapping("/sub")
    public RedirectView createUsr(String email){
        Subscrip a = new Subscrip();
        a.setEmail(email);
        subscripRepo.save(a);
        return new RedirectView("/");

    }
//    @GetMapping("/get")
//    public void getemails()
//    {
//        Subscrip a = new Subscrip();
//        System.out.println(a.getEmail());
//    }



}

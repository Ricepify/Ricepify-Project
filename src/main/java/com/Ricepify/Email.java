package com.Ricepify;

import com.Ricepify.Models.RecipeEntity;
import com.Ricepify.Models.Subscrip;
import com.Ricepify.Repositories.RecipeRepository;
import com.Ricepify.Repositories.SubscripRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import java.util.*;
import java.util.stream.Collectors;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

@Service

public class Email {
    @Autowired
    private JavaMailSender javaMailSender;

    @Autowired
    private RecipeRepository recipeRepository;
   @Autowired
   private SubscripRepo subscripRepo;
    private static final String fromEmail = "recipes660@gmail.com";
    //recipes660@gmail.com
    public   List<String> toEmails  = new ArrayList<>();

    
  

//    Subscrip a = new Subscrip();

    public  void p(){
    List<Subscrip> ad= subscripRepo.findAllByOrderByEmailAsc();
    System.out.println("ahmad");
        toEmails=(ad.stream().map(Subscrip::getEmail).collect(Collectors.toList()));
        toEmails.stream().forEach(System.out::println);
//    for(int i=0;i<ad.size();++i){
////        System.out.println(ad.get(i).getEmail());
//        toEmails.add(ad.get(i).getEmail());
//    }
    }


//    @Scheduled(cron = "0 0 12 * * ?") // Schedule to run at 12 PM every day
//    @Scheduled(cron = "*/20 * * * * *")
    @Scheduled(cron = "0 0 12 * * ?") // Schedule to run at 12 PM every day
    public void sendEmail() throws MessagingException {

        p();
//        x();
        List <RecipeEntity> v = recipeRepository.findAll();
        RecipeEntity c = v.get(0);
        System.out.println(c.getRecipeTitle());
        String o=c.getRecipeTitle();
        String d=c.getRecipeDescription();
//        System.out.println(toEmails.get(0));
        MimeMessage message = javaMailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, true);

        // Set the sender email address
        helper.setFrom(fromEmail);

        // Set the recipient email addresses
        helper.setTo(toEmails.toArray(new String[0]));

        // Set the email subject and content
        helper.setSubject("Your Daily Recipe");
        helper.setText("Title :- "+o+"\n"+"Description:- "+"\n"+d);

        // Send the email
        javaMailSender.send(message);
    }


}

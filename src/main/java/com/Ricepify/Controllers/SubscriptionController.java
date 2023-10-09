package com.Ricepify.Controllers;

import com.Ricepify.Models.Subscrip;
import com.Ricepify.Repositories.SubscripRepo;
import com.Ricepify.Service.SubsicriptionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.view.RedirectView;

import java.util.List;

@Controller
public class SubscriptionController {
    private final SubsicriptionService subsicriptionService;

    public SubscriptionController(SubsicriptionService subsicriptionService) {
        this.subsicriptionService = subsicriptionService;
    }

    @GetMapping("/subscription")
    public String getSub() {
        List<Subscrip> subscriptionList = subsicriptionService.getsubscriptionList();
        return "/sub.html";
    }

    @PostMapping("/subscription")
    public RedirectView createUsr(String email) {
        subsicriptionService.createUserSubscription(email);
        return new RedirectView("/");

    }
}

package com.Ricepify.Service.impl;

import com.Ricepify.Models.Subscrip;
import com.Ricepify.Repositories.SubscripRepo;
import com.Ricepify.Service.SubsicriptionService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SubscriptionServiceImpl implements SubsicriptionService {
    private final SubscripRepo subscripRepo;



    public SubscriptionServiceImpl(SubscripRepo subscripRepo) {
        this.subscripRepo = subscripRepo;
    }

    @Override
    public List<Subscrip> getsubscriptionList() {
        return subscripRepo.findAllByOrderByEmailAsc();
    }

    @Override
    public void createUserSubscription(String email) {
        Subscrip a = new Subscrip();
        a.setEmail(email);
        subscripRepo.save(a);
    }
}

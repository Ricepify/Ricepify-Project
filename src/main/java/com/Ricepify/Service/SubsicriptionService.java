package com.Ricepify.Service;


import com.Ricepify.Models.Subscrip;

import java.util.List;

public interface SubsicriptionService {

List<Subscrip> getsubscriptionList();
void createUserSubscription(String email);

}

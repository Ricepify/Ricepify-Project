package com.Ricepify.config;

import com.Ricepify.Models.SiteUser;
import com.Ricepify.Repositories.SiteUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    @Autowired
    SiteUserRepository siteUserRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        SiteUser userApp = siteUserRepository.findByUsername(username);

        if (userApp == null) {
            System.out.println("User not found " + username);
            throw new UsernameNotFoundException("user" + username + " was not found in the db");
        }
        System.out.println("Found User: " + userApp.getUsername());
        return userApp;
    }
}

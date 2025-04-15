package com.springsecurity.springsecurity.service;

import com.springsecurity.springsecurity.model.User;
import com.springsecurity.springsecurity.model.UserPrincipal;
import com.springsecurity.springsecurity.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class MyUserDetailsService implements UserDetailsService {
    @Autowired
    private UserRepo userRepo;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepo.findByUsername(username);
        if (user == null) {
            System.out.println("Bad Credentials");
        }
        return new UserPrincipal(user);
    }
    public User register(User user) {
        return userRepo.save(user);
    }

}

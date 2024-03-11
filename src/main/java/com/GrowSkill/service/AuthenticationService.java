package com.GrowSkill.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import com.GrowSkill.jwt.JwtAuthRequest;
import com.GrowSkill.jwt.JwtHelper;

import com.GrowSkill.repository.CustomerRepository;

@Service
public class AuthenticationService {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtHelper jwtHelper;

    @Autowired
    private CustomerRepository customerRepository;

    public String authenticateUser(JwtAuthRequest jwtAuthRequest) {

        Authentication authentication = authenticationManager.authenticate(
            new UsernamePasswordAuthenticationToken(
                jwtAuthRequest.getEmail(),
                jwtAuthRequest.getPassword()
            )
        );
        
//        System.out.println("CRAZY");
//        System.out.println(authentication.getPrincipal());

        SecurityContextHolder.getContext().setAuthentication(authentication);


        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        return jwtHelper.generateToken(userDetails);
    }
}

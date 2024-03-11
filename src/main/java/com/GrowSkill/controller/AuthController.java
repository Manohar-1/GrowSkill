package com.GrowSkill.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.GrowSkill.dto.CustomerDto;
import com.GrowSkill.entity.Customer;
import com.GrowSkill.jwt.JwtAuthRequest;
import com.GrowSkill.jwt.JwtAuthResponse;
import com.GrowSkill.repository.CustomerRepository;
import com.GrowSkill.service.AuthenticationService;
import com.GrowSkill.service.CustomerService;

@RestController
@RequestMapping("/api/auth")
public class AuthController {
    
    @Autowired
    private CustomerService customerService; 
    
    @Autowired
    private CustomerRepository customerRepository;
    
    @Autowired
    private AuthenticationService authenticationService;

    @PostMapping("/login")
    public ResponseEntity<JwtAuthResponse> authenticateUser(@RequestBody JwtAuthRequest jwtAuthRequest) {
        String token = authenticationService.authenticateUser(jwtAuthRequest);
        return ResponseEntity.ok(new JwtAuthResponse(token));
    }
    
    @PostMapping("/register")
    public ResponseEntity<String> registerCustomer(@RequestBody CustomerDto customerDto) {
        Customer customer = customerService.registerCustomer(customerDto);
        return ResponseEntity.ok("Customer registered successfully");
    }
    
    @GetMapping("/verify")
    public ResponseEntity<String> verifyEmail(@RequestParam("token") String token) {
       
        Customer customer = customerRepository.findByVerificationToken(token);
        if (customer == null) {         
            return ResponseEntity.badRequest().body("Invalid verification token");
        }
        
        customer.setVerified(true);
        customer.setVerificationToken(null); 
        customerRepository.save(customer);
        return ResponseEntity.ok("Email address verified successfully");
    }
}

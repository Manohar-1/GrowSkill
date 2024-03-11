package com.GrowSkill.service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.annotation.Lazy;
import org.springframework.context.event.EventListener;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.GrowSkill.EmailSenderService;
import com.GrowSkill.dto.CustomerDto;
import com.GrowSkill.entity.Customer;
import com.GrowSkill.repository.CustomerRepository;

import java.util.List;
import java.util.UUID;

@Lazy
@Service
public class CustomerService implements UserDetailsService{

    @Autowired
    private CustomerRepository customerRepository;
    
    @Autowired
    private PasswordEncoder passwordEncoder;
    
    @Autowired
    private EmailSenderService emailSenderService;
    
    public Customer registerCustomer(CustomerDto customerDto) {
        Customer existingCustomer = customerRepository.findByEmail(customerDto.getEmail());
        if (existingCustomer != null) {
            throw new RuntimeException("Email already exists");
        }
        
        Customer customer = new Customer();
        customer.setEmail(customerDto.getEmail());
        customer.setPassword(passwordEncoder.encode(customerDto.getPassword()));
        String verificationToken = UUID.randomUUID().toString();
        
        customer.setVerificationToken(verificationToken);
        
        Customer savedCustomer = customerRepository.save(customer);

        
        sendVerificationEmail(savedCustomer);

        return savedCustomer;
    }


    public Customer createCustomer(Customer customer) {
        return customerRepository.save(customer);
    }

    public Customer getCustomerById(Long customerId) {
        return customerRepository.findById(customerId).orElse(null);
    }

    public List<Customer> getAllCustomers() {
        return customerRepository.findAll();
    }

    public Customer updateCustomer(Long customerId, Customer customer) {
        Customer existingCustomer = getCustomerById(customerId);
        if (existingCustomer != null) {
            customer.setCustomerId(customerId);
            return customerRepository.save(customer);
        }
        return null;
    }

    public void deleteCustomer(Long customerId) {
        customerRepository.deleteById(customerId);
    }
    
    
    private void sendVerificationEmail(Customer customer) {
        
        String verificationLink = "http://localhost:8088/api/auth/verify?token=" + customer.getVerificationToken();
        emailSenderService.sendEmail(customer.getEmail(),"Verify Your Email Address","Please click the following link to verify your email address: " + verificationLink);
    }


	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Customer customer = customerRepository.findByEmail(username); 
		return customer;
	}
}

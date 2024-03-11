package com.GrowSkill.entity;
import lombok.Data;
import jakarta.persistence.*;

import java.util.Collection;
import java.util.List;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

@Entity
@Data
public class Customer implements UserDetails{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long customerId;
    
    private List<GrantedAuthority> authorities; 
    
    @NotNull
    private String name;
    
    @Email
    @NotNull
    private String email;
    
    @NotNull
    private String password;
    private String paymentInformation;
    
    @OneToMany(mappedBy = "customer")
    private List<Certificate> certificates;
    
    @ManyToMany
    @JoinTable(name = "customer_courses",
               joinColumns = @JoinColumn(name = "customer_id"),
               inverseJoinColumns = @JoinColumn(name = "course_id"))
    private List<Course> courses;
    
    private String verificationToken; 
    private boolean verified;
    
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return authorities;
	}
	@Override
	public String getUsername() {
		return email;
	}
	@Override
	public boolean isAccountNonExpired() {
		return true;
	}
	@Override
	public boolean isAccountNonLocked() {
		return true;
	}
	@Override
	public boolean isCredentialsNonExpired() {
		
		return true;
	}
	@Override
	public boolean isEnabled() {
		return true;
	}
}

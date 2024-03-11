package com.GrowSkill.dto;

import javax.validation.constraints.Email;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.Data;
import lombok.Setter;

@Data
@Setter
public class CustomerDto { 
	
	
	
	@Email
	@NotNull
	private String email;  
	
	@NotNull
	private String password;
}

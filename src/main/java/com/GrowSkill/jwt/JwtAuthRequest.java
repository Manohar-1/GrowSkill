package com.GrowSkill.jwt;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
public class JwtAuthRequest {

	private String email;
	private String password;
	
	
}

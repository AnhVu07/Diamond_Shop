package com.anhvu.model;

import javax.validation.constraints.Email;

import org.hibernate.validator.constraints.Length;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class AuthRequest {
	
	@Email @Length(min = 5, max = 50)
	private String username;
	
	@Length(min = 5, max = 10)
	private String password;

	
}

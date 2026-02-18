package com.shirdi.darshan.dtos;

import java.time.LocalDateTime;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
//user id ,name, email , role , message
public class AuthResp {
	private Long id;	
	private String firstName;	
	private String lastName;	
	private String email;	
	private String role;
	private String token;
	private String message;
	
}

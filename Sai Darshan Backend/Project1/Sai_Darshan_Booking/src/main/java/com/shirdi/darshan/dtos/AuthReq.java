package com.shirdi.darshan.dtos;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class AuthReq {
	private String email;
	private String password;
}

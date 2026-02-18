package com.shirdi.darshan.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.shirdi.darshan.dtos.AuthReq;
import com.shirdi.darshan.dtos.UserDTO;
import com.shirdi.darshan.entities.User;
import com.shirdi.darshan.service.UserService;

import lombok.RequiredArgsConstructor;

@RestController 
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {
	private final UserService userService;
	
	@PostMapping("/signup")
	public ResponseEntity<?> addNewUser(@RequestBody UserDTO user){
		System.out.println("in add new user" + user);
		try {
			return ResponseEntity.status(HttpStatus.CREATED)
					.body(userService.addUser(user));
		}catch (Exception e) {
			System.out.println(e);
			return ResponseEntity.status(HttpStatus.CONFLICT) //SC 409
					.body(e.getMessage());
		}
		
	}
	
	@PostMapping("/login")
	public ResponseEntity<?> userSignIn(@RequestBody AuthReq request) {
		System.out.println("in user sign in "+request);
		try {
			return ResponseEntity.ok(
					userService.authenticate(request));
		} catch (RuntimeException e) {
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED)//SC 401
					.body( e.getMessage());
		}
	}
	
	@GetMapping("/{userId}")
	public ResponseEntity<?>getUserDetails(@PathVariable Long userId){
		try {
			return ResponseEntity.ok(userService.getUserDetails(userId));
		} catch (RuntimeException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND)
					.body(e.getMessage());
		}
	}
	
	@PostMapping("{userId}")
	public ResponseEntity<?>updateUserDetails(@PathVariable Long userId, @RequestBody UserDTO user){
		try {
			return ResponseEntity.ok(userService.updateDetails(userId,user));
		} catch (RuntimeException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND)
					.body(e.getMessage());
		}
	}
	
	
	
}

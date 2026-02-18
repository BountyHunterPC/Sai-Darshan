package com.shirdi.darshan.service;

import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.shirdi.darshan.dtos.ApiResponse;
import com.shirdi.darshan.dtos.AuthReq;
import com.shirdi.darshan.dtos.AuthResp;
import com.shirdi.darshan.dtos.UserDTO;
import com.shirdi.darshan.entities.User;
import com.shirdi.darshan.repository.UserRepository;
import com.shirdi.darshan.security.JwtService;

import lombok.RequiredArgsConstructor;



@Service
@Transactional
@RequiredArgsConstructor

public class UserServiceImpl implements UserService {
	private final UserRepository userRepository;
	private final ModelMapper modelMapper;
	private final PasswordEncoder passwordEncoder;
	private final JwtService jwtService;

	@Override
	public String addUser(UserDTO req) throws Exception {
		// TODO Auto-generated method stub
		if(userRepository.existsByEmail(req.getEmail())) {
			throw new Exception("user already exists");
		}
		User user = new User();
		user = modelMapper.map(req, User.class);
		user.setPassword(passwordEncoder.encode(req.getPassword())); // Hash password
		User persistentUser = userRepository.save(user);
		
		return "New User added with Id =" + persistentUser.getId() ;
	}

	@Override
	public AuthResp authenticate(AuthReq request) {
		System.out.println("DEBUG: Login attempt for: " + request.getEmail());
		
		User user = userRepository.findByEmail(request.getEmail())
				.orElseThrow(() -> new RuntimeException("Invalid credentials"));
		
		System.out.println("DEBUG: User found: " + user.getEmail() + ", Role: " + user.getRole());
		
		if(!passwordEncoder.matches(request.getPassword(), user.getPassword())) {
			System.out.println("DEBUG: Password mismatch!");
			throw new RuntimeException("Invalid credentials");
		}
		
		System.out.println("DEBUG: Password matched!");
		
		String token = jwtService.generateToken(user.getEmail(), user.getRole().toString());
		
		AuthResp dto = new AuthResp();
		dto.setId(user.getId());
		dto.setFirstName(user.getFirstName());
		dto.setLastName(user.getLastName());
		dto.setEmail(user.getEmail());
		String roleValue = user.getRole().name();
		System.out.println("DEBUG: Role from DB: " + user.getRole());
		System.out.println("DEBUG: Role .name(): " + roleValue);
		dto.setRole(roleValue);
		dto.setToken(token);
		dto.setMessage("Successfull login");
		
		System.out.println("DEBUG: Returning DTO with role: " + dto.getRole());
		return dto;
		
	}

	@Override
	public Optional<User> getUserDetails(Long userId) {
		Optional<User> user = userRepository.findById(userId);
		if(user.isEmpty()) {
			throw new RuntimeException("Invalid user id!!!");
		}
		return user;
	}

	@Override
	public ApiResponse updateDetails(Long userId,UserDTO user) {
		Optional<User> persistentUserOpt = getUserDetails(userId);
		User persistentUser = persistentUserOpt.get();
		modelMapper.map(user, persistentUser);
		userRepository.save(persistentUser);
		return new ApiResponse("Success","User details updated");
	}
	
}

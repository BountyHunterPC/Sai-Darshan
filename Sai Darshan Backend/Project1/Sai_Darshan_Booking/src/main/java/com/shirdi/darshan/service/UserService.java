package com.shirdi.darshan.service;

import java.util.Optional;

import com.shirdi.darshan.dtos.ApiResponse;
import com.shirdi.darshan.dtos.AuthReq;
import com.shirdi.darshan.dtos.AuthResp;
import com.shirdi.darshan.dtos.UserDTO;
import com.shirdi.darshan.entities.User;

public interface UserService {
	String addUser(UserDTO user) throws Exception;

	AuthResp authenticate(AuthReq request);

	Optional<User> getUserDetails(Long userId);

	ApiResponse updateDetails(Long userId, UserDTO user);
}

package com.shirdi.darshan.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.shirdi.darshan.entities.User;

public interface UserRepository extends JpaRepository<User, Long> {
	boolean existsByEmail(String email); 	//Check if user exists

	User findByEmailAndPassword(String email, String password);
	
	Optional<User> findByEmail(String email);

}

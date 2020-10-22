package com.example.service;

import java.util.List;
import java.util.Optional;

import com.example.model.User;

public interface UserService {
	
	User saveUser(User user);
	Optional<User> findByUsername(String username);
	public List<User> getAllUsers();
	

}

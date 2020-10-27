package com.ojas.service;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.ojas.model.User;
import com.ojas.response.UserResponse;

@Service
public interface UserService {
	public ResponseEntity<Object> saveUser(User user);

}

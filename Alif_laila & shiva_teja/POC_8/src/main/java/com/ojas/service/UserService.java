package com.ojas.service;

import org.springframework.http.ResponseEntity;

import com.ojas.model.User;

public interface UserService {
	
	public ResponseEntity<Object> saveUser(User user) throws Exception;

}

package com.ojas.SpringSecurity.service;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.ojas.SpringSecurity.model.User;



@Service
public interface UserService {
	
	public ResponseEntity<Object> saveUser(User userReq)throws Exception;
	

}

package com.ojas.SpringSecurity.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ojas.SpringSecurity.service.impl.UserServiceImpl;

@RestController
@RequestMapping("/user")
public class UserController {
	@Autowired
	private UserServiceImpl userServ;
	
	@Autowired
	BCryptPasswordEncoder passwordEncoder;
	
	@PostMapping("/createuser")
	public ResponseEntity<Object> saveUser(@RequestBody com.ojas.SpringSecurity.model.User userReq)throws Exception{
		String password = userReq.getPassword();
		System.out.println("Password in controller::"+password);
		String encode = passwordEncoder.encode(password);
		userReq.setPassword(encode);
		ResponseEntity<Object> saveUser = userServ.saveUser(userReq);
		return new ResponseEntity<Object>(saveUser,HttpStatus.OK);
		
	}

}

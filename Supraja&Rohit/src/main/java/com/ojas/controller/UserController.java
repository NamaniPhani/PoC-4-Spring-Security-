package com.ojas.controller;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ojas.model.User;
import com.ojas.response.UserResponse;
import com.ojas.service.UserServiceImpl;

@RestController
@RequestMapping("/user")
public class UserController {
	@Autowired
	private UserServiceImpl userService;
 
	@Autowired
	private BCryptPasswordEncoder passwordEncoder;

	static Logger log = Logger.getLogger(LoginController.class.getName());

	@PostMapping("/save")
	public ResponseEntity<Object> saveUser(@RequestBody User user) {

		log.debug("Incoming request user controller : " + user);
		ResponseEntity<Object> saveUser = userService.saveUser(user);
		return new ResponseEntity<>(saveUser, HttpStatus.OK);
	}
}

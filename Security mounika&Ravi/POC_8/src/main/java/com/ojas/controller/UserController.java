package com.ojas.controller;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ojas.model.User;
import com.ojas.serviceimpl.UserServiceImpl;

@RestController
@RequestMapping("/poc/user")
@CrossOrigin
public class UserController {

	@Autowired
	private UserServiceImpl userService;

	static Logger log = Logger.getLogger(UserController.class.getName());

	@PostMapping(path = "/createuser", produces = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
	public ResponseEntity<Object> saveUser(@RequestBody User userReq) {
		log.debug("Incoming request user controller : " + userReq);
		ResponseEntity<Object> saveUser = userService.saveUser(userReq);
		return new ResponseEntity<>(saveUser, HttpStatus.OK);
	}
}

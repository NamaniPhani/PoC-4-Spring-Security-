package com.poc.controller;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.poc.model.User;
import com.poc.repository.RoleRepository;
import com.poc.repository.UserRepository;

@RestController
@RequestMapping("/secure")
public class UserController {
	private final Logger logger = Logger.getLogger(this.getClass());
	
	@Autowired
	private UserRepository userRepository;

	@Autowired
	private RoleRepository roleRepository;

	@Autowired
	private BCryptPasswordEncoder passwordEncoder;

	@PostMapping(value = "/saveUser", produces = "application/json", consumes = "application/json")
	public String addUserByAdmin(@RequestBody User user) {
		logger.info("inside UserController addUserByAdmin() :"+user);
		String pwd = user.getPassword();
		String encryptedPwd = passwordEncoder.encode(pwd);
		user.setPassword(encryptedPwd);
		userRepository.save(user);
		return "user added successfully";
	}
}

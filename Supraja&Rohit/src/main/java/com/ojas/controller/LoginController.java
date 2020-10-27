package com.ojas.controller;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ojas.jwt.JwtTokenGenerator;
import com.ojas.model.Login;
import com.ojas.model.Role;
import com.ojas.model.User;
import com.ojas.repository.UserRepository;
import com.ojas.response.UserResponse;

@RestController
@RequestMapping("/user")
public class LoginController {
	static Logger log = Logger.getLogger(LoginController.class.getName());


	@Autowired
	private UserRepository userRepository;

	@Autowired
	AuthenticationManager authentication;
		@Autowired
	private AuthenticationManager authManager;

	@Autowired
	private JwtTokenGenerator jwtTokenGenerator;

	
	@PostMapping("/login")
	public ResponseEntity<Object> saveUser(@RequestBody Login login) {
		log.debug("Req enter into the save method"+login);
		try{
		String roleName=null;
		String userName = login.getUserName();
		User findByUsername = userRepository.findByUserName(userName);
		Set<Role> roles = findByUsername.getRoles();
		for (Role roleNames : roles) {
			roleName = roleNames.getRole();
		}
		System.out.println("Role Name is : " + roleName);
		Authentication authenticate = authManager
				.authenticate(new UsernamePasswordAuthenticationToken(userName, login.getPassword()));
		String createToken = jwtTokenGenerator.createToken(userName, roleName);
		Map<Object, Object> object = new HashMap<>();
		object.put("UserName", userName);
		object.put("Token", "Bearer " + createToken);
		object.put("Data", authenticate);

		return new ResponseEntity<>(object, HttpStatus.OK);

	} catch (UsernameNotFoundException un) {
		log.error("Exception caught !");
		UserResponse response = new UserResponse();
		response.setMessage("Invalid Credentials ! " + un.getLocalizedMessage());
		response.setStatusCode(409);
		return new ResponseEntity<>(response, HttpStatus.CONFLICT);
	} catch (Exception e) {
		log.error("Exception caught");
		UserResponse response = new UserResponse();
		response.setMessage("Invalid Credentials !");
		response.setStatusCode(401);
		return new ResponseEntity<>(response, HttpStatus.UNAUTHORIZED);
	}
}

}

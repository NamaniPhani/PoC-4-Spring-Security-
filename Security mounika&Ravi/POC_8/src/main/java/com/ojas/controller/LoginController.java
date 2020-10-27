package com.ojas.controller;

import java.util.Date;
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
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ojas.exception.Response;
import com.ojas.jwt.JwtTokenGenerator;
import com.ojas.model.Login;
import com.ojas.model.Role;
import com.ojas.model.User;
import com.ojas.repositories.UserRepository;

@RestController
@RequestMapping("/poc/user")
public class LoginController {
	static Logger log = Logger.getLogger(LoginController.class.getName());
	@Autowired
	private AuthenticationManager authManager;

	@Autowired
	private JwtTokenGenerator jwtTokenGenerator;

	@Autowired
	private UserRepository userRepo;

	@PostMapping("/login")
	public ResponseEntity<Object> userLogin(@RequestBody Login login) {
		log.debug("Incoming request login controller : " + login);
		try {
			String roleName = null;
			String userName = login.getUsername();
			User findByUsername = userRepo.findByUsername(userName);
			Set<Role> roles = findByUsername.getRoles();
			for (Role roleNames : roles) {
				roleName = roleNames.getName();
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

		} catch (Exception e) {
			log.error("Exception caught");
			Response response = new Response();
			response.setTimestamp(new Date());
			response.setMessage("Invalid Credentials !");
			response.setStatuscode("401");
			return new ResponseEntity<>(response, HttpStatus.UNAUTHORIZED);
		}
	}

}

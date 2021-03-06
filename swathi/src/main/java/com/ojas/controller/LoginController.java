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

import com.ojas.dao.UserRepository;
import com.ojas.jwt.JwtTokenGenerator;
import com.ojas.model.Login;
import com.ojas.model.Roles;
import com.ojas.model.User;
import com.ojas.response.Response;

@RequestMapping("/user")
@RestController
public class LoginController {
	static Logger log = Logger.getLogger(LoginController.class.getName());
	@Autowired
	private UserRepository userRepo;
	@Autowired
	private JwtTokenGenerator jwtTokenGenerator;
	@Autowired
	AuthenticationManager authentication;
	
	@PostMapping("/login")
	public ResponseEntity<Object>saveUser(@RequestBody Login login) {
		log.debug("Incoming request login controller : " + login);
		//Authentication authenticate = authentication.authenticate(new UsernamePasswordAuthenticationToken(login.getUsername(),login.getPassword() ));
		//return new ResponseEntity<Object>(authenticate,HttpStatus.OK);
		try {
			String roleName = null;
			String userName = login.getUsername();
			User findByUsername = userRepo.findByUsername(userName);
			Set<Roles> roles = findByUsername.getRoles();
			for (Roles roleNames : roles) {
				roleName = roleNames.getRoleName();
			}
			System.out.println("Role Name is : " + roleName);
			Authentication authenticate = authentication
					.authenticate(new UsernamePasswordAuthenticationToken(userName, login.getPassword()));
			String createToken = jwtTokenGenerator.createToken(userName, roleName);
			Map<Object, Object> object = new HashMap<>();
			object.put("UserName", userName);
			object.put("Token", "Bearer " + createToken);
			object.put("Data", authenticate);

			return new ResponseEntity<>(object, HttpStatus.OK);

		} catch (UsernameNotFoundException un) {
			log.error("Exception caught !");
			Response response = new Response();
			response.setStatusMessage("Invalid Credentials ! " + un.getLocalizedMessage());
			response.setStatucCode("409");
			return new ResponseEntity<>(response, HttpStatus.CONFLICT);
		} catch (Exception e) {
			log.error("Exception caught");
			Response response = new Response();
			response.setStatusMessage("Invalid Credentials !");
			response.setStatucCode("401");
			return new ResponseEntity<>(response, HttpStatus.UNAUTHORIZED);
		}
	}

}

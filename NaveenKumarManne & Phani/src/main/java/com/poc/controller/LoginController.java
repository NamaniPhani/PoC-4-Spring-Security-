package com.poc.controller;
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

import com.poc.jwt.JwtTokenGenerator;
import com.poc.model.Login;
import com.poc.model.Role;
import com.poc.model.User;
import com.poc.repository.UserRepository;

@RestController
@RequestMapping("/secure")
public class LoginController {
	private final Logger logger = Logger.getLogger(this.getClass());
	
	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private UserRepository userRepository;
	@Autowired
	private JwtTokenGenerator jwtTokenGenerator;
	
	@PostMapping(value = "/loginAuth", produces = "application/json", consumes = "application/json")
	
	public ResponseEntity<Object> userLogin(@RequestBody Login login) {
		logger.info("inside LoginController userLogin():"+login);
		try{	
		String roleName = null;
			String userName = login.getUsername();
			User findByUsername = userRepository.findByUsername(userName);
			Set<Role> roles = findByUsername.getRoles();
			for (Role roleNames : roles) {
				roleName = roleNames.getRole();
			}
			System.out.println("Role Name is : " + roleName);
			Authentication authenticate = authenticationManager
					.authenticate(new UsernamePasswordAuthenticationToken(userName, login.getPassword()));
			String createToken = jwtTokenGenerator.createToken(userName, roleName);
			Map<Object, Object> object = new HashMap<>();
			object.put("UserName", userName);
			object.put("Token", "Bearer " + createToken);
			object.put("Data", authenticate);

			return new ResponseEntity<>(object, HttpStatus.OK);

		} catch(Exception e){
			e.printStackTrace();
		}
		return new ResponseEntity<>(401, HttpStatus.UNAUTHORIZED);
	}
}

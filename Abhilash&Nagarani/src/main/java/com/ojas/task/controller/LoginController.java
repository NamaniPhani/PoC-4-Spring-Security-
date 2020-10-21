package com.ojas.task.controller;

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

import com.ojas.task.jwt.JwtTokenGenerator;
import com.ojas.task.model.CustomUser;
import com.ojas.task.model.Login;
import com.ojas.task.model.UserRoles;
import com.ojas.task.repository.UserRepo;

@RestController
@RequestMapping("/user")
public class LoginController {
	static Logger log = Logger.getLogger(LoginController.class.getName());
	@Autowired
	private AuthenticationManager authManager;

	@Autowired
	private JwtTokenGenerator jwtTokenGenerator;

	@Autowired
	private UserRepo userRepo;

	@PostMapping("/login")
	public ResponseEntity<Object> userLogin(@RequestBody Login login) {
		log.debug("Incoming request login controller : " + login);
		try {
			String roleName = null;
			String userName = login.getUsername();
			CustomUser findByUsername = userRepo.findByUserName(userName);
			Set<UserRoles> roles = findByUsername.getRoles();
			for (UserRoles roleNames : roles) {
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

			return new ResponseEntity<>("Invalid Credentials ! ", HttpStatus.CONFLICT);
		} catch (Exception e) {
			log.error("Exception caught");

			return new ResponseEntity<>("Invalid Credentials !", HttpStatus.UNAUTHORIZED);
		}
	}

}

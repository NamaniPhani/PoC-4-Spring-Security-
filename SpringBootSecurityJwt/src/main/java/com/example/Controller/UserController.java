package com.example.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.model.User;
import com.example.model.UserRequest;
import com.example.model.UserResponse;
import com.example.service.UserServiceImpl;
import com.example.util.JwtUtil;

@RestController
@RequestMapping("/user")
public class UserController {
	@Autowired
	UserServiceImpl userServiceImpl;
	@Autowired
	JwtUtil jwtUtil;
	@Autowired
	private AuthenticationManager authenticationManager;

	@PostMapping("/register")
	public ResponseEntity<String> saveUser(@RequestBody User user) {
		User user1 = userServiceImpl.saveUser(user);
		return ResponseEntity.ok("Registered user is:" + user1);
	}

	@PostMapping("/login")
	public ResponseEntity<UserResponse> generateToken(@RequestBody UserRequest userReq) {
		authenticationManager
				.authenticate(new UsernamePasswordAuthenticationToken(userReq.getUsername(), userReq.getPassword()));
		String token1 = jwtUtil.generateToken(userReq.getUsername());

		return ResponseEntity.ok(new UserResponse(token1, "token generate successfully"));

	}

	@GetMapping("/getall")
	public List<User> getUser() {
		return userServiceImpl.getAllUsers();
	}

	@GetMapping("/welcome")
	public ResponseEntity<String> accessUserData() {
		return ResponseEntity.ok("Hello user:" );
	}

}

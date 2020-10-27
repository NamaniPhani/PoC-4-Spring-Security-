package com.ojas.poc.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.util.HashSet;
import java.util.Set;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.ojas.exception.CustomException;
import com.ojas.exception.Response;
import com.ojas.model.Role;
import com.ojas.model.User;
import com.ojas.repositories.UserRepository;
import com.ojas.serviceimpl.UserServiceImpl;

public class UserServiceTest {
	
	@InjectMocks
	private UserServiceImpl userServiceImpl;

	@Mock
	private UserRepository userRepo;

	@Mock
	Response response;

	@Mock
	User user;
	@Mock
	private ResponseEntity<Object> entity;

	@Mock
	private CustomException cex;

	@Mock
	private BCryptPasswordEncoder pwdEncoder;

	@Autowired
	private MockMvc mockMvc;

	@BeforeEach
	public void init() {
		MockitoAnnotations.initMocks(this);
		mockMvc = MockMvcBuilders.standaloneSetup(userServiceImpl).build();
	}
	
	 
	@Test
	public void requestNullOrEmptyTest() throws Exception {
		User user = new User();
		user = null;
		when(userRepo.save(user)).thenThrow(CustomException.class);
		ResponseEntity<Object> saveUser = userServiceImpl.saveUser(user);
		assertEquals(saveUser.getStatusCode(), HttpStatus.UNPROCESSABLE_ENTITY);
	}
	
	
	@Test
	public void checkAllEmptyTest() throws Exception {
		User user = new User();
		user.setUsername("");
		when(userRepo.save(user)).thenThrow(CustomException.class);
		ResponseEntity<Object> saveUser = userServiceImpl.saveUser(user);
		assertEquals(saveUser.getStatusCode(), HttpStatus.UNPROCESSABLE_ENTITY);
	}
 
	
	@Test
	public void checkUserExits() throws Exception {
		User user = new User();
		user.setUsername("OJAS6");
		when(userRepo.findByUsername(user.getUsername())).thenReturn(user);
		ResponseEntity<Object> saveUser = userServiceImpl.saveUser(user); 
		when(userRepo.findByUsername(user.getUsername())).thenThrow(CustomException.class);
		assertEquals(saveUser.getStatusCode(), HttpStatus.CONFLICT);
	} 
	
	@Test
	public void checkAllNullTest() throws Exception {
		User user = new User();
		user.setUsername(null);
		when(userRepo.save(user)).thenThrow(CustomException.class);
		ResponseEntity<Object> saveUser = userServiceImpl.saveUser(user);
		assertEquals(saveUser.getStatusCode(), HttpStatus.UNPROCESSABLE_ENTITY);
	}
	
	@Test
	public void saveUserTest() throws Exception {
		User user = new User();
		Role role = new Role();
		Set<Role> rolelist = new HashSet<>();
		user.setUsername("Ojas");
		user.setPassword("ojas1525");
		role.setName("ADMIN");
		rolelist.add(role);
		user.setRoles(rolelist);
		when(userRepo.save(user)).thenReturn(user);
		ResponseEntity<Object> saveUser = userServiceImpl.saveUser(user);
		assertEquals(saveUser.getStatusCode(), HttpStatus.OK);
	}

}

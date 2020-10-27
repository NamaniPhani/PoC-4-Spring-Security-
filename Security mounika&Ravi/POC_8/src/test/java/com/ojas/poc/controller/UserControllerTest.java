package com.ojas.poc.controller;

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
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.ojas.controller.UserController;
import com.ojas.model.Role;
import com.ojas.model.User;
import com.ojas.serviceimpl.UserServiceImpl;

public class UserControllerTest {

	@InjectMocks
	private UserController userContoller;

	@Mock
	private UserServiceImpl userService;

	@Mock
	private ResponseEntity<Object> entity;

	@Autowired
	private MockMvc mockMvc;

	@BeforeEach
	public void init() {
		MockitoAnnotations.initMocks(this);
		mockMvc = MockMvcBuilders.standaloneSetup(userContoller).build();
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
		when(userService.saveUser(user)).thenReturn(entity);
		ResponseEntity<Object> saveUser = userContoller.saveUser(user);
		assertEquals(saveUser.getStatusCode(), HttpStatus.OK);
	}
}

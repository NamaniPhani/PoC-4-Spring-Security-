package com.ojas.task.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ojas.task.model.CustomUser;
import com.ojas.task.model.Response;
import com.ojas.task.service.UserService;

import io.swagger.annotations.Api;

@RestController
@Api
@RequestMapping("/auth/")
public class UserController {

	@Autowired
	UserService service;

	@GetMapping("/get")
	public void get() {
		System.out.println("welcome");
	}

	@PostMapping("/saveOrUpdate")
	@Valid
	public ResponseEntity<Response> save(@RequestBody CustomUser user) {
		Response customUser = null;

		customUser = service.save(user);
		if (customUser == null)
			return new ResponseEntity<>(HttpStatus.CONFLICT);
		else {
			return new ResponseEntity<>(customUser, HttpStatus.OK);
		}
	}

	@GetMapping("/getById/{id}")
	@Valid
	public ResponseEntity<Response> getById(@PathVariable Integer id) {
		Response customUser = null;

		customUser = service.findByRoleId(id);
		if (customUser.getObject() == null)
			return new ResponseEntity<>(HttpStatus.CONFLICT);
		else {
			return new ResponseEntity<>(customUser, HttpStatus.OK);
		}
	}

	@GetMapping("/getAll")
	@Valid
	public ResponseEntity<Response> getAll() {
		Response customUser = null;

		customUser = service.findAll();
		if (customUser.getObject() == null)
			return new ResponseEntity<>(HttpStatus.CONFLICT);
		else {
			return new ResponseEntity<>(customUser, HttpStatus.OK);
		}
	}

	@DeleteMapping("/deleteAll")
	@Valid
	public ResponseEntity<Response> deleteAll() {
		Response customUser = null;

		customUser = service.deleteAll();

		return new ResponseEntity<>(customUser, HttpStatus.OK);
	}

	@DeleteMapping("/delete/{id}")
	@Valid
	public ResponseEntity<Response> deleteById(@PathVariable Integer id) {
		Response customUser = null;

		customUser = service.deleteById(id);

		return new ResponseEntity<>(customUser, HttpStatus.OK);
	}

}

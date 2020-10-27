package com.ojas.SpringSecurity.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.ojas.SpringSecurity.dao.UserRepository;
import com.ojas.SpringSecurity.service.UserService;


@Service
public class UserServiceImpl implements UserService {
	@Autowired
	private UserRepository userRepo;

	@Override
	public ResponseEntity<Object> saveUser(com.ojas.SpringSecurity.model.User userReq) throws Exception {

		return new ResponseEntity<Object>(userRepo.save(userReq), HttpStatus.OK);
	}

}

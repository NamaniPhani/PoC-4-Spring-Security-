package com.ojas.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.ojas.dao.UserRepository;
import com.ojas.model.User;

@Service
public class UserServiceImpl implements UserService {
	@Autowired
	UserRepository userRepo;

	@Override
	public ResponseEntity<Object> saveUser(User userReq) throws Exception {

		return new ResponseEntity<Object>(userRepo.save(userReq), HttpStatus.OK);
	}

}

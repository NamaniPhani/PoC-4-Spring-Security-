package com.ojas.service;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.ojas.exception.CustomException;
import com.ojas.model.User;
import com.ojas.repository.UserRepository;
import com.ojas.response.UserResponse;

@Service
public class UserServiceImpl implements UserService {
	@Autowired
	private UserRepository userRepo;
	
	@Autowired
	private BCryptPasswordEncoder pwdEncoder;

	static Logger log = Logger.getLogger(UserServiceImpl.class.getName());

	@Override
	public ResponseEntity<Object> saveUser(User user) throws CustomException {
		log.debug("Incoming request user service : " + user);

		if (user == null) {
		log.error("Invalid request");
			return new ResponseEntity<>(new CustomException("INVALID_REQUEST"), HttpStatus.UNPROCESSABLE_ENTITY);
		}
		if (user.getUserName() == null || user.getUserName().isEmpty()) {
			log.error("Fields should not be null"); 
			return new ResponseEntity<>(new CustomException("INVALID_FIELDS"), HttpStatus.UNPROCESSABLE_ENTITY);
		}
		User findByUserName = userRepo.findByUserName(user.getUserName());
		if (findByUserName != null) {
			return new ResponseEntity<>( new CustomException("USER_EXIT"), HttpStatus.CONFLICT);
		}

		String encrpassword = pwdEncoder.encode(user.getPassword());
		user.setPassword(encrpassword);
		User save = userRepo.save(user);
		UserResponse response = new UserResponse();
		response.setStatusCode(200);
		response.setMessage("USER_SAVE");
		response.setdata(save);
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

}

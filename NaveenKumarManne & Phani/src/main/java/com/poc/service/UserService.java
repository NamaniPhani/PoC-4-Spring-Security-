package com.poc.service;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.poc.model.User;
import com.poc.repository.UserRepository;

@Service
public class UserService implements UserDetailsService {
	private final Logger logger = Logger.getLogger(this.getClass());

	@Autowired
	private UserRepository userRepository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		logger.info("inside loadUserByUsername(String username) :"+username );
		User user = userRepository.findByUsername(username);
		CustomUserDetails userDetails = null;
		if (user != null) {
			userDetails = new CustomUserDetails();
			userDetails.setUser(user);
		} else {
			logger.error("user not exist with name:"+username);
			throw new UsernameNotFoundException("user not exist with name:" + username);
		}
		return userDetails;

	}

}

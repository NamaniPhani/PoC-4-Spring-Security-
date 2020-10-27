package com.ojas.SpringSecurity.securityconfig;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.ojas.SpringSecurity.dao.UserRepository;
import com.ojas.SpringSecurity.model.User;

@Service
public class CustomUserDetailsService implements UserDetailsService{
	static Logger log = LoggerFactory.getLogger(CustomUserDetailsService.class.getName());
	@Autowired
	private UserRepository userRepo;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		log.debug("Incoming request custom user details service load method : " + username);
		User user = userRepo.findByUsername(username);
		CustomUserDetails userDetails = null;
		if (user != null) {

			userDetails = new CustomUserDetails();
			userDetails.setUser(user);

		} else {
			log.error("user not exits with name " + username);
			throw new UsernameNotFoundException("User not exists with name" + username);
			
		}
		return userDetails;
	}

}

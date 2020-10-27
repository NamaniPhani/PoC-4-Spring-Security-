package com.ojas.securityconfig;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.ojas.model.User;
import com.ojas.repository.UserRepository;

@Service
public class CustomUserDetailsService implements UserDetailsService {
	
	@Autowired
	private UserRepository userRepository;

	@Override
	public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
		System.out.println("username in load method::"+userName);
		User user = userRepository.findByUserName(userName);
		CustomUserDeatails customUserDeatails = null;
		if(user!=null){
			 customUserDeatails = new CustomUserDeatails();
			 customUserDeatails.setUser(user);
		}
		else{
				throw new UsernameNotFoundException("user not exist with the name"+userName);
			}
		
		return customUserDeatails;
	}

}

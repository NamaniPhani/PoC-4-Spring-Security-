
package com.ojas.task.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.ojas.task.model.CustomUser;
import com.ojas.task.model.CustomUserDetails;
import com.ojas.task.repository.UserRepo;
import com.ojas.task.repository.UserRolesRepo;

@Service
public class CustomUserDetailsService implements UserDetailsService {

	@Autowired
	UserRepo repo;

	@Autowired
	UserRolesRepo rolesRepo;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		System.out.println("from  usrname " + username);
		CustomUser user = repo.findByUserName(username);
		System.out.println("***************"+ user + "****************");
		if (user == null)
			throw new UsernameNotFoundException("User Not Found");
		else {
			return new CustomUserDetails(user);
		}
	}

}

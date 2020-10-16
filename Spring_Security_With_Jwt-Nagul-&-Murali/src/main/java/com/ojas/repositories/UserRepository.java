package com.ojas.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ojas.model.User;

public interface UserRepository  extends JpaRepository<User, Long>{
	
	 public User findByUsername(String username);

}

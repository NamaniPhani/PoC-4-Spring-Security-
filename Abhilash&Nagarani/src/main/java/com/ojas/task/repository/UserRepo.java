package com.ojas.task.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ojas.task.model.CustomUser;

public interface UserRepo extends JpaRepository<CustomUser, Integer> {

	public CustomUser findByUserName(String name);
	
	public Optional<CustomUser> findByUserId(Integer id);
}

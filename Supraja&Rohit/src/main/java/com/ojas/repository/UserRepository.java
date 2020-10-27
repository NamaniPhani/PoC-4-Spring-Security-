package com.ojas.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ojas.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
public User findByUserName(String userName);
}

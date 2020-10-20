package com.poc.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.poc.model.Role;
public interface RoleRepository extends JpaRepository<Role, Integer> {

}

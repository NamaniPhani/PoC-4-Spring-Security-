package com.ojas.task.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ojas.task.model.UserRoles;

public interface UserRolesRepo extends JpaRepository<UserRoles, Integer> {
//	@Query("select a.role from UserRoles a, CustomUser b where b.userName = ?1 and a.roleId = b.")
//	public List<UserRoles> findByUser(String name);
	
	public UserRoles findByRole(String role);
	
	public Optional<UserRoles> findByRoleId(Integer id);
	
}

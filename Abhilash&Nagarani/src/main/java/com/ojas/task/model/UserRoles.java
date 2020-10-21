package com.ojas.task.model;

import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class UserRoles {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer roleId;

//	@Pattern(regexp = "^[aA-zZ_ ]{1,30}$", message = "Provide Valid Role")
	private String role;

	public UserRoles() {
		super();
	}

	public UserRoles(Integer roleId, String role) {
		super();
		this.roleId = roleId;
		this.role = role;
	}

	public Integer getRoleId() {
		return roleId;
	}

	public void setRoleId(Integer roleId) {
		this.roleId = roleId;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	@Override
	public String toString() {
		return "UserRoles [roleId=" + roleId + ", role=" + role + "]";
	}

}

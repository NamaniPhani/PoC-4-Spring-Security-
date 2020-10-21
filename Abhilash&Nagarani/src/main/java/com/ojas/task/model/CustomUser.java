package com.ojas.task.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Entity
public class CustomUser {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer userId;

	@Pattern(regexp = "^[aA-zZ_ ]{1,30}$", message = "Provide Valid User Name")
	@NotBlank(message = "User Name Should Not Be Empty or Blank")
	private String userName;

	@Size(min = 6, message = "Password Should Contain Minimum 6 Characters")
	@NotBlank(message = "password Should Not Be Empty or Blank")
	private String password;

	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(joinColumns = { @JoinColumn(name = "userId") }, inverseJoinColumns = { @JoinColumn(name = "roleId") })
	private Set<UserRoles> roles = new HashSet<>();

	public CustomUser() {
		super();
	}

	public CustomUser(CustomUser user) {
		this.userId = user.getUserId();
		this.userName = user.getUserName();
		this.password = user.getPassword();
		this.roles = user.getRoles();
	}

	public CustomUser(Integer userId, String userName, String password, Set<UserRoles> roles) {
		super();
		this.userId = userId;
		this.userName = userName;
		this.password = password;
		this.roles = roles;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public Set<UserRoles> getRoles() {
		return roles;
	}

	public void setRoles(Set<UserRoles> roles) {
		this.roles = roles;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Override
	public String toString() {
		return "CustomUser [userId=" + userId + ", userName=" + userName + ", password=" + password + ", roles=" + roles
				+ "]";
	}

}

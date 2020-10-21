package com.ojas.task.service;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.ojas.task.model.CustomUser;
import com.ojas.task.model.Response;
import com.ojas.task.model.UserRoles;
import com.ojas.task.repository.UserRepo;
import com.ojas.task.repository.UserRolesRepo;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	UserRepo repo;

	@Autowired
	UserRolesRepo rolesRepo;

	@Autowired
	BCryptPasswordEncoder encoder;

	@Override
	public Response save(CustomUser user) {
		CustomUser customUser = null;
		Response response = null;
		try {
			user.setPassword(encoder.encode(user.getPassword()));
			Set<UserRoles> set = new HashSet<>();
			for (UserRoles userRoles : user.getRoles()) {
				Optional<UserRoles> findById = rolesRepo.findById(userRoles.getRoleId());
				if (findById.isPresent())
					set.add(findById.get());
			}
			user.setRoles(set);
			customUser = repo.save(user);
			response = new Response(customUser, "User Saved  Successfully", HttpStatus.OK);
		} catch (Exception e) {
			response = new Response(null, "Failed To Save The User", HttpStatus.BAD_REQUEST);

		}
		return response;
	}

	@Override
	public Response findByRoleId(Integer id) {
		Optional<CustomUser> user = null;
		Response response = null;
		try {
			user = repo.findByUserId(id);
			if (user.isPresent()) {
				response = new Response(user, "Found User With Id " + id, HttpStatus.OK);
			} else {
				response = new Response(null, " User Not Found With Id " + id, HttpStatus.BAD_REQUEST);
			}
		} catch (Exception e) {
			response = new Response(null, "User Not Found With Id  " + id, HttpStatus.CONFLICT);
		}
		return response;
	}

	@Override
	public Response findAll() {
		List<CustomUser> Users = null;
		Response response = null;
		try {
			Users = repo.findAll();
			if (Users.isEmpty()) {
				response = new Response(null, "No Records Found", HttpStatus.BAD_REQUEST);
			} else {
				response = new Response(Users, "Records Found", HttpStatus.OK);
			}
		} catch (Exception e) {
			response = new Response(null, "Exception Raised ", HttpStatus.CONFLICT);
		}
		return response;
	}

	@Override
	public Response deleteById(Integer id) {
		Response response = null;
		try {
			repo.deleteById(id);
			response = new Response(id, "Deleted Record With Id "+id, HttpStatus.OK);
		} catch (Exception e) {
			response = new Response(null, "Exception Raised ", HttpStatus.CONFLICT);
		}
		return response;
	}

	@Override
	public Response deleteAll() {
		Response response = null;
		try {
		repo.deleteAll();
		response = new Response(null, "Deleted All Records", HttpStatus.OK);
		}catch (Exception e) {
			response = new Response(null, "Failed To Delete The Records", HttpStatus.CONFLICT);
		}
		return response;
	}

}

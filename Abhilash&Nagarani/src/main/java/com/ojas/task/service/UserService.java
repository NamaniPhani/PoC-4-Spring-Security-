package com.ojas.task.service;

import com.ojas.task.model.CustomUser;
import com.ojas.task.model.Response;

public interface UserService {

	public Response save(CustomUser user);
	
	public Response findByRoleId(Integer id);
	
	public Response findAll();
	
	public Response deleteById(Integer id);
	
	public Response deleteAll();
}

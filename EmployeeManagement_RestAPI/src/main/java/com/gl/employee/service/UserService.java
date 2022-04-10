package com.gl.employee.service;

import org.springframework.security.core.userdetails.UserDetails;

import com.gl.employee.entity.User;

public interface UserService {

	public String save(User user);
	
	public String update(User user);
	
}

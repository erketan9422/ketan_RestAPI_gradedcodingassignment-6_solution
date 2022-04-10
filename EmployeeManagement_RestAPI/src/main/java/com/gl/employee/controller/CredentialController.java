package com.gl.employee.controller;

/**
 * Controller for Credential endpoints
 */

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gl.employee.entity.Role;
import com.gl.employee.entity.User;
import com.gl.employee.service.RoleService;
import com.gl.employee.service.UserService;

@RestController
@RequestMapping("/api/credential")
public class CredentialController {

	@Autowired
	UserService userService;
	
	@Autowired
	RoleService roleService;
	
	@PostMapping("/user/save")
	public String addNewUser(@RequestBody User user) {		
		return userService.save(user);
	}
	
	@PutMapping("/user/update")
	public String updateUser(@RequestBody User user) {
		return userService.update(user);
	}
	
	@PostMapping("/role/save")
	public String addNewRole(@RequestBody Role role) {		
		return roleService.save(role);
	}
}

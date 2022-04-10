package com.gl.employee.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gl.employee.entity.Role;
import com.gl.employee.repository.RoleRepository;
import com.gl.employee.service.RoleService;

@Service
public class RoleServiceImpl implements RoleService {

	@Autowired
	RoleRepository roleRepository;
	
	@Override
	public String save(Role role) {
		Role isRoleExist = roleRepository.findByName(role.getName());
		if (isRoleExist != null) {
			//throw new RuntimeException("Role already exists!");
			return "Role already exists!";
		}
		else {
			roleRepository.saveAndFlush(role);
			return "New role saved to DB";
		}
	}

}

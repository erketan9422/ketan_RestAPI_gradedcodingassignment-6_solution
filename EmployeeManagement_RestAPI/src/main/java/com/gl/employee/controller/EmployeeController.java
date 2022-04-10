package com.gl.employee.controller;

/**
 * Controller for Employee operations endpoints
 */

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.gl.employee.entity.Employee;
import com.gl.employee.entity.Role;
import com.gl.employee.entity.User;
import com.gl.employee.service.EmployeeService;
import com.gl.employee.service.RoleService;
import com.gl.employee.service.UserService;

@RestController
@RequestMapping("/api")
public class EmployeeController {

	@Autowired
	EmployeeService employeeService;
	
	@PostMapping("/employees/save")
	public String save(@RequestBody Employee employee) {
		return employeeService.save(employee); 
	}
	
	@PutMapping("/employees/update")
	public Employee update(@RequestBody Employee employee) {
		return employeeService.update(employee);
	}
	
	@GetMapping("/employees/{id}")
	public Employee getById(@PathVariable int id) {
		return employeeService.getById(id);
	}
	
	@DeleteMapping("/employees/{id}")
	public String deleteById(@PathVariable int id) {
		return employeeService.deleteById(id);
	}
	
	@GetMapping("/employees/search/{firstName}")
	public List<Employee> getByFirstName(@PathVariable String firstName){
		return employeeService.getByFirstName(firstName);
	}
	
	@GetMapping("/employees")
	public List<Employee> getAllEmployees(){
		return employeeService.getAllEmployees();
	}

	@GetMapping("/employees/sort")
	public List<Employee> getAllEmployeesSortFirstName(@RequestParam Direction order){
		return employeeService.getAllEmployeesSortFirstName(order);
	}
}

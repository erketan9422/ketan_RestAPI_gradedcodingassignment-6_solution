package com.gl.employee.service;

import java.util.List;

import org.springframework.data.domain.Sort.Direction;

import com.gl.employee.entity.Employee;

public interface EmployeeService {

	public String save(Employee employee);
	
	public Employee update(Employee employee);
	
	public Employee getById(int id);
	
	public String deleteById(int id);
	
	public List<Employee> getByFirstName(String name);
	
	public List<Employee> getAllEmployees();
	
	public List<Employee> getAllEmployeesSortFirstName(Direction direction);
	
}

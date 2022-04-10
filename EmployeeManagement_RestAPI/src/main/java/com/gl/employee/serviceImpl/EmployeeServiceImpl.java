package com.gl.employee.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.gl.employee.entity.Employee;
import com.gl.employee.repository.EmployeeRepository;
import com.gl.employee.service.EmployeeService;

@Service
public class EmployeeServiceImpl implements EmployeeService {
	
	@Autowired
	EmployeeRepository employeeRepository;

	@Override
	public String save(Employee employee) {
		employeeRepository.saveAndFlush(employee);
		return "Employee saved to DB.";
	}

	@Override
	public Employee update(Employee employee) {
		boolean empIsAvailable = employeeRepository.existsById(employee.getId()); 
		if (empIsAvailable) 
			employeeRepository.saveAndFlush(employee);
		else
			employee = new Employee();
		return employee;
	}

	@Override
	public Employee getById(int id) {
		return employeeRepository.findById(id).get();
	}

	@Override
	public String deleteById(int id) {
		boolean empIsAvailable = employeeRepository.existsById(id); 
		if (empIsAvailable) {
			employeeRepository.deleteById(id);
			return "Employee with id: " + id + ", is deleted!";
		}
		else {
			return "Employee with id: " + id + ", DOESN'T EXISTS!";
		}
	}

	@Override
	public List<Employee> getByFirstName(String name) {
		List<Employee> employees = employeeRepository.findByFirstNameContainsIgnoreCase(name);
		return employees;
	}

	@Override
	public List<Employee> getAllEmployees() {
		return gettingEmployeesOnDemand(false, Direction.ASC);
	}

	@Override
	public List<Employee> getAllEmployeesSortFirstName(Direction direction) {
		return gettingEmployeesOnDemand(true, direction);
	}
	
	// Retrieving all employee data - sorted and unsorted
	private List<Employee> gettingEmployeesOnDemand(boolean sortOnFirstName, Direction direction){
		List<Employee> employees;
		if (sortOnFirstName) {
			employees = employeeRepository.findAll(Sort.by(direction, "firstName"));
		}
		else {
			employees = employeeRepository.findAll();
		}
		return employees;
	}

}

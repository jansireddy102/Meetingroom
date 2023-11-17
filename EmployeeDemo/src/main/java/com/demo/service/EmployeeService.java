package com.demo.service;

import java.util.List;
import com.demo.entities.Employee;

public interface EmployeeService {
	List<Employee> getAllEmployees();

	void saveEmployee(Employee employee);

	Employee getEmployeeById(long id);

	Employee getEmployeeByEmail(String email);

	void deleteEmployeeById(long id);

}

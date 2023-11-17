package com.demo.service;

import java.util.List;
import com.demo.entities.Salary;

public interface SalaryService {

	List<Salary> getAllSalary();

	void saveSalary(Salary salary);

	Salary getSalaryById(long id);

	Salary getSalaryByEmail(String email);

	void deleteSalaryById(long id);

}

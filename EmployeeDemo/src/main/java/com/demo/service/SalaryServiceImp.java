package com.demo.service;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.demo.dao.SalaryRepository;
import com.demo.entities.Salary;

@Service
public class SalaryServiceImp implements SalaryService {

	@Autowired
	private SalaryRepository salaryRepository;

	@Override
	public List<Salary> getAllSalary() {
		return salaryRepository.findAll();
	}

	@Override
	public void saveSalary(Salary salary) {
		this.salaryRepository.save(salary);

	}

	@Override
	public Salary getSalaryById(long id) {
		Optional<Salary> optional = salaryRepository.findById(id);
		Salary salary = null;
		if (optional.isPresent()) {
			salary = optional.get();
		} else {
			throw new RuntimeException(" Salary not found for id :: " + id);
		}

		return salary;
	}

	@Override
	public void deleteSalaryById(long id) {
		this.salaryRepository.deleteById(id);

	}

	@Override
	public Salary getSalaryByEmail(String email) {

		Optional<Salary> optional = salaryRepository.findByEmail(email);
		Salary salary = null;
		if (optional.isPresent()) {
			salary = optional.get();
		} else {
			throw new RuntimeException(" Employee not found");
		}
		return salary;

	}
}

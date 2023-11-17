package com.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import com.demo.entities.Salary;
import com.demo.service.SalaryService;

@Controller
public class SalaryController {

	@Autowired
	private SalaryService salaryservice;

	@GetMapping("/salary")
	public String viewHomePage(Model model) {
		model.addAttribute("salarylist", salaryservice.getAllSalary());
		return "S_index";

	}

	@GetMapping("/showNewSalaryForm")
	public String showNewSalaryForm(Model model) {

		Salary salary = new Salary();
		model.addAttribute("salary", salary);
		return "new_salary";
	}

	@PostMapping("/saveSalary")
	public String saveSalary(@ModelAttribute("salary") Salary salary) {

		salaryservice.saveSalary(salary);
		return "redirect:/salary";
	}

	@GetMapping("/UpdateSalary/{id}")
	public String UpdateSalary(@PathVariable(value = "id") long id, Model model) {

		Salary salary = salaryservice.getSalaryById(id);

		model.addAttribute("salary", salary);
		return "update_salary";
	}

	@GetMapping("/deleteSalary/{id}")
	public String deleteSalary(@PathVariable(value = "id") long id) {

		this.salaryservice.deleteSalaryById(id);
		return "redirect:/salary";
	}

}

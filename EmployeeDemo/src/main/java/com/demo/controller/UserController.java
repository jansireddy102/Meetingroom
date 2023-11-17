package com.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import com.demo.entities.Attendence;
import com.demo.entities.Employee;
import com.demo.entities.Salary;
import com.demo.service.AttendenceService;
import com.demo.service.EmployeeService;
import com.demo.service.LearningService;
import com.demo.service.SalaryService;

@Controller
public class UserController {

	@Autowired
	private EmployeeService employeeService;

	@Autowired
	private LearningService learningService;

	@Autowired
	private SalaryService salaryservice;

	@Autowired
	private AttendenceService attendenceservice;

	@GetMapping("/View_user")
	public String displayDashboard(Model model) {
		return "View_user";
	}

	@GetMapping("/ViewDetailsByEmail")
	public String ViewDetailsByEmail(@RequestParam String email, Model model) {

		Employee employee = employeeService.getEmployeeByEmail(email);
		if (employee != null) {
			model.addAttribute("employee", employee);
		} else {
			model.addAttribute("recordNotFound", true);
		}
		return "View_user";
	}

	@GetMapping("/usercourses")
	public String ViewCourses(Model model) {
		model.addAttribute("listcourses", learningService.getAllLearning());
		return "L_User";
	}

	@GetMapping("/ViewByEmail")
	public String ViewSalaryByEmail(@RequestParam(required = false) String email, Model model) {

		if (email != null && !email.isEmpty()) {
			Salary salary = salaryservice.getSalaryByEmail(email);
			if (salary != null) {
				model.addAttribute("salary", salary);
			} else {
				model.addAttribute("recordNotFound", true);
			}
		}
		return "S_User";
	}
	

	@GetMapping("/viewattendence")
	public String viewHomePage(Model model) {
		model.addAttribute("attendencelist", attendenceservice.getAllAttendence());
		return "U_Attendence";
	}

	@GetMapping("/userattendence")
	public String showuserattendenceform(Model model) {
		Attendence attendence = new Attendence();
		model.addAttribute("attendence", attendence);
		return "U_NewAttendence";
	}

	@PostMapping("/saveUserAttendence")
	public String saveAttendence(@ModelAttribute("attendence") Attendence attendence) {

		attendenceservice.saveAttendence(attendence);
		return "redirect:/viewattendence";
	}

	@GetMapping("/UpdateUserAttendence/{id}")
	public String UpdateAttendence(@PathVariable(value = "id") long id, Model model) {
		Attendence attendence = attendenceservice.getAttendenceById(id);
		model.addAttribute("attendence", attendence);
		return "update_userattendence";
	}
}

package com.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.demo.entities.Attendence;
import com.demo.service.AttendenceService;

@Controller
public class AttendenceController {

	@Autowired
	private AttendenceService attendenceservice;

	@GetMapping("/attendence")
	public String viewHomePage(Model model) {
		model.addAttribute("attendencelist", attendenceservice.getAllAttendence());
		return "A_index";

	}

	@GetMapping("/showNewAttendenceForm")
	public String showNewAttendenceForm(Model model) {

		Attendence attendence = new Attendence();
		model.addAttribute("attendence", attendence);
		return "new_attendence";
	}

	@PostMapping("/saveAttendence")
	public String saveAttendence(@ModelAttribute("attendence") Attendence attendence) {

		attendenceservice.saveAttendence(attendence);
		return "redirect:/attendence";
	}

	@GetMapping("/UpdateAttendence/{id}")
	public String UpdateAttendence(@PathVariable(value = "id") long id, Model model) {

		Attendence attendence = attendenceservice.getAttendenceById(id);

		model.addAttribute("attendence", attendence);
		return "update_attendence";
	}

	@GetMapping("/deleteAttendence/{id}")
	public String deleteAttendence(@PathVariable(value = "id") long id) {

		this.attendenceservice.deleteAttendenceById(id);
		return "redirect:/attendence";
	}

}

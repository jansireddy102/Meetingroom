package com.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.demo.entities.Learning;
import com.demo.service.LearningService;

@Controller
public class LearningController {

	@Autowired
	private LearningService learningService;

	@GetMapping("/learning")
	public String viewHomePage(Model model) {
		model.addAttribute("listcourses", learningService.getAllLearning());
		return "L_index";

	}

	@GetMapping("/showNewCourseForm")
	public String showNewCourseForm(Model model) {

		Learning learning = new Learning();
		model.addAttribute("learning", learning);
		return "new_learning";
	}

	@PostMapping("/savecourse")
	public String saveLearning(@ModelAttribute("learning") Learning learning) {

		learningService.saveLearning(learning);
		return "redirect:/learning";
	}

	@GetMapping("/UpdateCourse/{id}")
	public String UpdateCourse(@PathVariable(value = "id") long id, Model model) {

		Learning learning = learningService.getLearningById(id);

		model.addAttribute("learning", learning);
		return "update_learning";
	}

	@GetMapping("/deleteCourse/{id}")
	public String deleteCourse(@PathVariable(value = "id") long id) {

		this.learningService.deleteLearningById(id);
		return "redirect:/learning";
	}

}

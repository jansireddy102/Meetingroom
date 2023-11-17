package com.demo.controller;

import org.springframework.web.bind.annotation.GetMapping;

public class ErrorController {

	@GetMapping("/error/NotFound")
	public String NotFoundError() {
		return "not_found";
	}

}

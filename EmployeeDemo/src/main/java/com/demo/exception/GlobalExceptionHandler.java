package com.demo.exception;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

	private static final long serialVersionUID = 1L;

	@ExceptionHandler(EmployeeNotFoundException.class)
	public String handleEmployeeNotFoundException() {
		return "redirect:/error/NotFound"; // Redirect to the custom error page
	}

}

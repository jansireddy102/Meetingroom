package com.demo.exception;

public class EmployeeNotFoundException extends RuntimeException {

	public EmployeeNotFoundException() {
		super("Employee not found");
	}

	public EmployeeNotFoundException(String message) {
		super(message);
	}

}

package com.demo.dto;

import jakarta.persistence.Column;
import jakarta.validation.constraints.Pattern;

public class UserRegistrationDTO {

	private String username;

	@Column(name = "Email")
	@Pattern(regexp = "^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$", message = "Invalid email format")
	private String email;

	private String password;

	private String role;

	public UserRegistrationDTO() {
		super();
	}

	public UserRegistrationDTO(String role) {
		super();
		this.role = role;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

}

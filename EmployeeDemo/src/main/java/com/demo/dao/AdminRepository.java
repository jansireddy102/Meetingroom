package com.demo.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.demo.entities.Admin;

public interface AdminRepository extends JpaRepository<Admin, Long> {
	Admin findByEmail(String email);
}
package com.demo.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.demo.entities.Learning;

@Repository
public interface LearningRepository extends JpaRepository<Learning, Long> {

}

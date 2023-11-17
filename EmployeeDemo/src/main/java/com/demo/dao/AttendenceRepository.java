package com.demo.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.demo.entities.Attendence;

@Repository
public interface AttendenceRepository extends JpaRepository<Attendence, Long> {

}

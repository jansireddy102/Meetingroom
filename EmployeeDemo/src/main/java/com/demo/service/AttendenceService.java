package com.demo.service;

import java.util.List;

import com.demo.entities.Attendence;

public interface AttendenceService {

	List<Attendence> getAllAttendence();

	void saveAttendence(Attendence attendence);

	Attendence getAttendenceById(long id);

	void deleteAttendenceById(long id);

}

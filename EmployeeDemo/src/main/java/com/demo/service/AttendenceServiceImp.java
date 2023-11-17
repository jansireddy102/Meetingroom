package com.demo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.demo.dao.AttendenceRepository;
import com.demo.entities.Attendence;

@Service
public class AttendenceServiceImp implements AttendenceService {

	@Autowired
	private AttendenceRepository attendenceRepository;

	@Override
	public List<Attendence> getAllAttendence() {
		return attendenceRepository.findAll();
	}

	@Override
	public void saveAttendence(Attendence attendence) {
		this.attendenceRepository.save(attendence);

	}

	@Override
	public Attendence getAttendenceById(long id) {
		Optional<Attendence> optional = attendenceRepository.findById(id);
		Attendence attendence = null;
		if (optional.isPresent()) {
			attendence = optional.get();
		} else {
			throw new RuntimeException(" Attendence not found for id :: " + id);
		}
		return attendence;
	}

	@Override
	public void deleteAttendenceById(long id) {
		this.attendenceRepository.deleteById(id);

	}

}

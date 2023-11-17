package com.demo.service;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.demo.dao.LearningRepository;
import com.demo.entities.Learning;

@Service
public class LearningServiceImp implements LearningService {

	@Autowired
	private LearningRepository learningrepository;

	@Override
	public List<Learning> getAllLearning() {

		return learningrepository.findAll();
	}

	@Override
	public void saveLearning(Learning learning) {
		this.learningrepository.save(learning);

	}

	@Override
	public Learning getLearningById(long id) {
		Optional<Learning> optional = learningrepository.findById(id);
		Learning learning = null;
		if (optional.isPresent()) {
			learning = optional.get();
		} else {
			throw new RuntimeException(" Course not found for id :: " + id);
		}
		return learning;
	}

	@Override
	public void deleteLearningById(long id) {
		this.learningrepository.deleteById(id);
	}

}

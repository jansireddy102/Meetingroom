package com.demo.service;

import java.util.List;

import com.demo.entities.Learning;

public interface LearningService {

	List<Learning> getAllLearning();

	void saveLearning(Learning learning);

	Learning getLearningById(long id);

	void deleteLearningById(long id);

}

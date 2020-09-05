package com.vietis.task.service;

import java.util.List;

import com.vietis.task.dto.ExerciseDTO;

public interface ExerciseService {
	
	List<ExerciseDTO> findAllByUser(Integer id);
	
}

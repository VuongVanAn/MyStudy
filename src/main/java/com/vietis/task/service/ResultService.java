package com.vietis.task.service;

import java.util.List;

import com.vietis.task.dto.ExerciseQuestDTO;
import com.vietis.task.dto.ResultDTO;

public interface ResultService {
	
	List<ResultDTO> findAll(Integer userId);

	ResultDTO saveResult(List<ExerciseQuestDTO> listQuest, Integer id);

}

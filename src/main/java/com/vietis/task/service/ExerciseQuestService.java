package com.vietis.task.service;

import java.util.List;

import com.vietis.task.dto.BankDTO;
import com.vietis.task.dto.ExerciseQuestDTO;

public interface ExerciseQuestService {
	
	List<ExerciseQuestDTO> findOne(Integer id);

	boolean generateData(List<BankDTO> bankList, Integer id, int amount);

}

package com.vietis.task.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vietis.task.converter.ResultConverter;
import com.vietis.task.dto.ExerciseQuestDTO;
import com.vietis.task.dto.ResultDTO;
import com.vietis.task.model.Exercise;
import com.vietis.task.model.Result;
import com.vietis.task.repository.ExerciseRepository;
import com.vietis.task.repository.ResultRepository;
import com.vietis.task.service.ResultService;

@Service
public class ResultServiceImpl implements ResultService {
	
	@Autowired
	private ResultRepository resultRepository;
	
	@Autowired
	private ExerciseRepository exerciseRepo;
	
	@Autowired
	private ResultConverter resultConverter;

	@Override
	public ResultDTO saveResult(List<ExerciseQuestDTO> listQuest, Integer id) {
		int count = 0;
		for (ExerciseQuestDTO item : listQuest) {
			if (item.getAnswerUser().equalsIgnoreCase(item.getCorrect())) {
				count++;
			}
		}
		ResultDTO dto = new ResultDTO();
		dto.setCreatedDate(new Date());
		dto.setScore(count);
		dto.setExerciseId(id);
		Result entity = resultRepository.save(resultConverter.toEntity(dto));
		return resultConverter.toDTO(entity);
	}

	@Override
	public List<ResultDTO> findAll(Integer userId) {
		List<Exercise> exerciseList = exerciseRepo.findByUserid(userId);
		List<ResultDTO> resultList = new ArrayList<ResultDTO>();
		for (Exercise entity : exerciseList) {
			List<ResultDTO> dtos = resultConverter.toDTOList(resultRepository.findByExerciseid(entity.getId()));
			for (ResultDTO dto : dtos) {
				dto.setTitle(entity.getTitle());
				resultList.add(dto);
			}
		}
		return resultList;
	}

}

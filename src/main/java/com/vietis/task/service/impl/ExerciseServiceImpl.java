package com.vietis.task.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vietis.task.converter.ExerciseConverter;
import com.vietis.task.dto.ExerciseDTO;
import com.vietis.task.model.Exercise;
import com.vietis.task.model.ExerciseQuest;
import com.vietis.task.model.Result;
import com.vietis.task.model.User;
import com.vietis.task.repository.ExerciseQuestRepository;
import com.vietis.task.repository.ExerciseRepository;
import com.vietis.task.repository.ResultRepository;
import com.vietis.task.repository.UserRepository;
import com.vietis.task.service.ExerciseService;
import com.vietis.task.service.GenericService;

@Service
public class ExerciseServiceImpl implements ExerciseService, GenericService<ExerciseDTO> {
	
	@Autowired
	private ExerciseRepository exerciseRepo;
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private ExerciseQuestRepository exerciseQuestRepo;
	
	@Autowired
	private ResultRepository resultRepo;
	
	@Autowired
	private ExerciseConverter exerciseConverter;
	
	@Override
	public List<ExerciseDTO> findAll() {
		List<Exercise> exerciseList = exerciseRepo.findAll();
		List<ExerciseDTO> dtos = new ArrayList<ExerciseDTO>();
		for (Exercise entity : exerciseList) {
			ExerciseDTO dto = exerciseConverter.toDTO(entity);
			User user = userRepository.findById(dto.getUserId()).get();
			dto.setFullName(user.getFullName());
			dtos.add(dto);
		}
		return dtos;
	}
	
	@Override
	public ExerciseDTO save(ExerciseDTO dto) {
		if (dto.getId() == null) {
			dto.setChecked(0);
			dto.setCreatedDate(new Date());
		} else {
			ExerciseDTO exercise = exerciseConverter.toDTO(exerciseRepo.findById(dto.getId()).get());
			dto.setCreatedDate(exercise.getCreatedDate());
			dto.setChecked(exercise.getChecked());
			dto.setUpdateDate(new Date());
		}
		Exercise exercise = exerciseRepo.save(exerciseConverter.toEntity(dto));
		return exerciseConverter.toDTO(exercise);
	}


	@Override
	public ExerciseDTO findOne(Integer id) {
		return exerciseConverter.toDTO(exerciseRepo.findById(id).get());
	}

	@Override
	public String delete(Integer id) {
		List<ExerciseQuest> exerciseQuestList = exerciseQuestRepo.findByExerciseid(id);
		exerciseQuestRepo.deleteAll(exerciseQuestList);
		
		List<Result> resultList = resultRepo.findByExerciseid(id);
		resultRepo.deleteAll(resultList);
		
		exerciseRepo.deleteById(id);
		return "";
	}

	@Override
	public List<ExerciseDTO> findAllByUser(Integer id) {
		return exerciseConverter.toDTOList(exerciseRepo.findByUserid(id));
	}

}

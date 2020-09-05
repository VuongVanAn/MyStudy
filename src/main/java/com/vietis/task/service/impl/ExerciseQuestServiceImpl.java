package com.vietis.task.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vietis.task.converter.ExerciseQuestConverter;
import com.vietis.task.dto.BankDTO;
import com.vietis.task.dto.ExerciseQuestDTO;
import com.vietis.task.model.Exercise;
import com.vietis.task.model.ExerciseQuest;
import com.vietis.task.repository.ExerciseQuestRepository;
import com.vietis.task.repository.ExerciseRepository;
import com.vietis.task.service.ExerciseQuestService;

@Service
public class ExerciseQuestServiceImpl implements ExerciseQuestService {
	
	@Autowired
	private ExerciseQuestRepository exerciseQuestRepo;
	
	@Autowired
	private ExerciseQuestConverter questConverter;
	
	@Autowired
	private ExerciseRepository exerciseRepo;
	
	@Override
	public List<ExerciseQuestDTO> findOne(Integer id) {
		List<ExerciseQuest> exerciseList = exerciseQuestRepo.findByExerciseid(id);
		List<ExerciseQuestDTO> dtos = new ArrayList<ExerciseQuestDTO>();
		int count = 1;
		for (ExerciseQuest entity : exerciseList) {
			ExerciseQuestDTO dto = questConverter.toDTO(entity);
			dto.setNum(count++);
			dtos.add(dto);
		}
		return dtos;
	}
	
	@Override
	public boolean generateData(List<BankDTO> bankList, Integer id, int amount) {
		Random rand = new Random();
		List<ExerciseQuestDTO> questList = new ArrayList<>();
		Exercise exercise = exerciseRepo.findById(id).get();
		
		for (int i = 0; i < amount; i++) {
			int index = rand.nextInt(bankList.size());
			BankDTO dtos = bankList.get(index);
			
			ExerciseQuestDTO dto = new ExerciseQuestDTO();
			dto.setCorrect(dtos.getCorrect());
			dto.setExerciseId(exercise.getId());
			dto.setOption1(dtos.getOption1());
			dto.setOption2(dtos.getOption2());
			dto.setOption3(dtos.getOption3());
			dto.setOption4(dtos.getOption4());
			dto.setQuestion(dtos.getQuestion());
			dto.setUserId(exercise.getUserid());
			
			questList.add(dto);
			bankList.remove(dtos);
		}
		exercise.setChecked(1);
		exerciseRepo.save(exercise);
		exerciseQuestRepo.saveAll(questConverter.toEntityList(questList));
		return true;
	}
	
}

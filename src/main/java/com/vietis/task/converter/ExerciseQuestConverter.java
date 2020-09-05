package com.vietis.task.converter;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.vietis.task.dto.ExerciseQuestDTO;
import com.vietis.task.model.ExerciseQuest;

@Component
public class ExerciseQuestConverter {
	
	@Autowired
	private ModelMapper modelMapper;
	
	public ExerciseQuestDTO toDTO(ExerciseQuest entity) {
		return modelMapper.map(entity, ExerciseQuestDTO.class);
	}
	
	public ExerciseQuest toEntity(ExerciseQuestDTO dto) {
		return modelMapper.map(dto, ExerciseQuest.class);
	}
	
	public List<ExerciseQuestDTO> toDTOList(List<ExerciseQuest> listEntity) {	
		return listEntity.stream().map(entity -> modelMapper.map(entity, ExerciseQuestDTO.class))
                .collect(Collectors.toList());
	}
	
	public List<ExerciseQuest> toEntityList(List<ExerciseQuestDTO> listDTOS) {	
		return listDTOS.stream().map(dto -> modelMapper.map(dto, ExerciseQuest.class))
                .collect(Collectors.toList());
	}

}

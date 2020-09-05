package com.vietis.task.converter;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.vietis.task.dto.ExerciseDTO;
import com.vietis.task.model.Exercise;

@Component
public class ExerciseConverter {
	
	@Autowired
	private ModelMapper modelMapper;
	
	public ExerciseDTO toDTO(Exercise entity) {
		return modelMapper.map(entity, ExerciseDTO.class);
	}
	
	public Exercise toEntity(ExerciseDTO dto) {	
		return modelMapper.map(dto, Exercise.class);
	}
	
	public List<ExerciseDTO> toDTOList(List<Exercise> listEntity) {		
		return listEntity.stream().map(entity -> modelMapper.map(entity, ExerciseDTO.class))
                .collect(Collectors.toList());
	}
	
	public List<Exercise> toEntityList(List<ExerciseDTO> listDTOS) {	
		return listDTOS.stream().map(dto -> modelMapper.map(dto, Exercise.class))
                .collect(Collectors.toList());
	}

}

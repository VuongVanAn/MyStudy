package com.vietis.task.converter;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.vietis.task.dto.ResultDTO;
import com.vietis.task.model.Result;

@Component
public class ResultConverter {
	
	@Autowired
	private ModelMapper modelMapper;
	
	public ResultDTO toDTO(Result entity) {
		return modelMapper.map(entity, ResultDTO.class);
	}
	
	public Result toEntity(ResultDTO dto) {
		return modelMapper.map(dto, Result.class);
	}
	
	public List<ResultDTO> toDTOList(List<Result> listEntity) {
		return listEntity.stream().map(entity -> modelMapper.map(entity, ResultDTO.class))
                .collect(Collectors.toList());
	}
	
	public List<Result> toEntityList(List<ResultDTO> listDTOS) {
		return listDTOS.stream().map(dto -> modelMapper.map(dto, Result.class))
                .collect(Collectors.toList());
	}

}

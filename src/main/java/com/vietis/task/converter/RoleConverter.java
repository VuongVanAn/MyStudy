package com.vietis.task.converter;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.vietis.task.dto.RoleDTO;
import com.vietis.task.model.Role;

@Component
public class RoleConverter {
	
	@Autowired
	private ModelMapper modelMapper;
	
	public RoleDTO toDto(Role entity) {
		/*ModelMapper modelMapper = new ModelMapper();
		RoleDTO dto = modelMapper.map(entity, RoleDTO.class);
		return dto;*/
		return modelMapper.map(entity, RoleDTO.class);
	}
	
	public Role toEntity(RoleDTO dto) {
		/*ModelMapper modelMapper = new ModelMapper();
		Role entity = modelMapper.map(dto, Role.class);
		return entity;*/
		return modelMapper.map(dto, Role.class);
	}
	
	public List<RoleDTO> toDTOList(List<Role> listEntity) {
		/*ModelMapper modelMapper = new ModelMapper();
		List<RoleDTO> dtos = listEntity.stream().map(entity -> modelMapper.map(entity, RoleDTO.class))
				                       .collect(Collectors.toList());
		return dtos;*/
		return listEntity.stream().map(entity -> modelMapper.map(entity, RoleDTO.class))
                .collect(Collectors.toList());
	}
	
	public List<Role> toEntityList(List<RoleDTO> listDTOS) {
		/*ModelMapper modelMapper = new ModelMapper();
		List<Role> entities = listDTOS.stream().map(dto -> modelMapper.map(dto, Role.class))
				                       .collect(Collectors.toList());
		return entities;*/
		return listDTOS.stream().map(dto -> modelMapper.map(dto, Role.class))
                .collect(Collectors.toList());
	}
	
}

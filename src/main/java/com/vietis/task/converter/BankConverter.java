package com.vietis.task.converter;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.vietis.task.dto.BankDTO;
import com.vietis.task.model.Bank;

@Component
public class BankConverter {
	
	@Autowired
	private ModelMapper modelMapper;
	
	public BankDTO toDTO(Bank entity) {
		return modelMapper.map(entity, BankDTO.class);
	}
	
	public Bank toEntity(BankDTO dto) {	
		return modelMapper.map(dto, Bank.class);
	}
	
	public List<BankDTO> toDTOList(List<Bank> listEntity) {		
		return listEntity.stream().map(entity -> modelMapper.map(entity, BankDTO.class))
                .collect(Collectors.toList());
	}
	
	public List<Bank> toEntityList(List<BankDTO> listDTOS) {	
		return listDTOS.stream().map(dto -> modelMapper.map(dto, Bank.class))
                .collect(Collectors.toList());
	}

}

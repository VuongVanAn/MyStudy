package com.vietis.task.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vietis.task.converter.RoleConverter;
import com.vietis.task.dto.RoleDTO;
import com.vietis.task.repository.RoleRepository;
import com.vietis.task.service.GenericService;

@Service
public class RoleServiceImpl implements GenericService<RoleDTO> {
	
	@Autowired
	private RoleRepository roleRepository;
	
	@Autowired
	private RoleConverter roleConverter;
	
	@Override
	public List<RoleDTO> findAll() {
		return roleConverter.toDTOList(roleRepository.findAll());
	}

	@Override
	public RoleDTO save(RoleDTO t) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public RoleDTO findOne(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String delete(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

}

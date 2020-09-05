package com.vietis.task.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.vietis.task.dto.UserDTO;

public interface UserService {
	
	Page<UserDTO> findAll(Pageable pageable);
	
	List<UserDTO> findByRoleId(Integer id);

}

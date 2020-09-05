package com.vietis.task.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.vietis.task.dto.UserDTO;
import com.vietis.task.service.GenericService;
import com.vietis.task.service.UserService;

@RestController
@RequestMapping("/api/user")
public class UserAPI implements Resource<UserDTO> {

	@Autowired
	private UserService userService;
	
	@Autowired
	private GenericService<UserDTO> service;

	@Override
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<List<UserDTO>> findAll() {
		return new ResponseEntity<>(service.findAll(), HttpStatus.OK);
	}
	
	@RequestMapping("/roles")
	@PreAuthorize("hasRole('TEACHER')")
	public ResponseEntity<List<UserDTO>> findByRoles() {
		return new ResponseEntity<>(userService.findByRoleId(2), HttpStatus.OK);
	}
	
	@Override
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<UserDTO> findById(Integer id) {
		UserDTO dto = service.findOne(id);
		if (dto == null) {
		}
		return new ResponseEntity<>(dto, HttpStatus.OK);
	}

	@Override
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<UserDTO> save(UserDTO dto) {
		if (dto.getId() != null) {
		}
		return new ResponseEntity<>(service.save(dto), HttpStatus.CREATED);
	}

	@Override
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<UserDTO> update(UserDTO dto) {
		if (dto.getId() == null) {
		}
		return new ResponseEntity<>(service.save(dto), HttpStatus.OK);
	}

	@Override
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<String> deleteById(Integer id) {
		UserDTO dto = service.findOne(id);
		if (dto == null) {		
		}
		return new ResponseEntity<>(service.delete(id), HttpStatus.OK);
	}

}

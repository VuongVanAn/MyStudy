package com.vietis.task.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.vietis.task.dto.ExerciseDTO;
import com.vietis.task.service.ExerciseService;
import com.vietis.task.service.GenericService;

@RestController
@RequestMapping("/api/exercise")
public class ExerciseAPI implements Resource<ExerciseDTO> {
	
	@Autowired
	private GenericService<ExerciseDTO> service;
	
	@Autowired
	private ExerciseService exerciseService;

	@Override
	@PreAuthorize("hasRole('TEACHER')")
	public ResponseEntity<List<ExerciseDTO>> findAll() {
		return new ResponseEntity<>(service.findAll(), HttpStatus.OK);
	}
	
	@PreAuthorize("hasRole('TEACHER')")
	@RequestMapping("/user/{id}")
	public ResponseEntity<List<ExerciseDTO>> findAllByUser(@PathVariable Integer id) {
		return new ResponseEntity<>(exerciseService.findAllByUser(id), HttpStatus.OK);
	}

	@Override
	@PreAuthorize("hasRole('TEACHER')")
	public ResponseEntity<ExerciseDTO> findById(Integer id) {
		return new ResponseEntity<>(service.findOne(id), HttpStatus.OK);
	}

	@Override
	@PreAuthorize("hasRole('TEACHER')")
	public ResponseEntity<ExerciseDTO> save(ExerciseDTO dto) {
		return new ResponseEntity<>(service.save(dto), HttpStatus.CREATED);
	}

	@Override
	@PreAuthorize("hasRole('TEACHER')")
	public ResponseEntity<ExerciseDTO> update(ExerciseDTO dto) {
		return new ResponseEntity<>(service.save(dto), HttpStatus.OK);
	}

	@Override
	@PreAuthorize("hasRole('TEACHER')")
	public ResponseEntity<String> deleteById(Integer id) {
		return new ResponseEntity<>(service.delete(id), HttpStatus.OK);
	}

}

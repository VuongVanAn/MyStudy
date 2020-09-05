package com.vietis.task.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.vietis.task.dto.ExerciseDTO;
import com.vietis.task.service.ExerciseService;
import com.vietis.task.service.GenericService;

@RestController
@RequestMapping("/api/client")
public class HomeAPI implements Resource<ExerciseDTO> {
	
	@Autowired
	private GenericService<ExerciseDTO> service;
	
	@Autowired
	private ExerciseService exerciseService;

	@Override
	public ResponseEntity<List<ExerciseDTO>> findAll() {
		return new ResponseEntity<>(service.findAll(), HttpStatus.OK);
	}
	
	@RequestMapping("/user/{id}")
	public ResponseEntity<List<ExerciseDTO>> findAllByUser(@PathVariable Integer id) {
		return new ResponseEntity<>(exerciseService.findAllByUser(id), HttpStatus.OK);
	}

	@Override
	public ResponseEntity<ExerciseDTO> findById(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ResponseEntity<ExerciseDTO> save(ExerciseDTO t) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ResponseEntity<ExerciseDTO> update(ExerciseDTO t) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ResponseEntity<String> deleteById(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

}

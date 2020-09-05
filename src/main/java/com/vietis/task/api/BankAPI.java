package com.vietis.task.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.vietis.task.dto.BankDTO;
import com.vietis.task.service.GenericService;

@RestController
@RequestMapping("/api/bank")
public class BankAPI implements Resource<BankDTO> {
	
	@Autowired
	private GenericService<BankDTO> service;

	@Override
	@PreAuthorize("hasRole('TEACHER')")
	public ResponseEntity<List<BankDTO>> findAll() {
		return new ResponseEntity<>(service.findAll(), HttpStatus.OK);
	}

	@Override
	public ResponseEntity<BankDTO> findById(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ResponseEntity<BankDTO> save(BankDTO t) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ResponseEntity<BankDTO> update(BankDTO t) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ResponseEntity<String> deleteById(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}
}

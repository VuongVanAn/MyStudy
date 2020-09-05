package com.vietis.task.service;

import org.springframework.web.multipart.MultipartFile;

public interface BankService {
	
	boolean saveData(MultipartFile file);
	
}

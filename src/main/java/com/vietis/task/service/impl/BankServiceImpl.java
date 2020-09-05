package com.vietis.task.service.impl;

import java.util.Iterator;
import java.util.List;

import org.apache.commons.io.FilenameUtils;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.vietis.task.converter.BankConverter;
import com.vietis.task.dto.BankDTO;
import com.vietis.task.repository.BankRepository;
import com.vietis.task.service.BankService;
import com.vietis.task.service.GenericService;

@Service
public class BankServiceImpl implements BankService, GenericService<BankDTO> {
	
	@Autowired
	private BankRepository bankRepo;
	
	@Autowired
	private BankConverter bankConverter;
	
	@Override
	public List<BankDTO> findAll() {
		return bankConverter.toDTOList(bankRepo.findAll());
	}

	@Override
	public BankDTO save(BankDTO t) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public BankDTO findOne(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String delete(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean saveData(MultipartFile file) {
		boolean isFlag = false;
		String extension = FilenameUtils.getExtension(file.getOriginalFilename());
		if (extension.equalsIgnoreCase("xls") || extension.equalsIgnoreCase("xlsx")) {
			isFlag = readDataFromExcel(file);
		}
		return isFlag;
	}

	private boolean readDataFromExcel(MultipartFile file) {
		Workbook workbook = getWorkBook(file);
		Sheet sheet = (Sheet) workbook.getSheetAt(0);
		Iterator<Row> rows = sheet.iterator();
		rows.next();
		while (rows.hasNext()) {
			Row row = rows.next();
			BankDTO bankQuest = new BankDTO();
			
			if (row.getCell(0).getCellType() == CellType.STRING) {
				bankQuest.setQuestion(row.getCell(0).getStringCellValue());
			}
			
			if (row.getCell(1).getCellType() == CellType.STRING) {
				bankQuest.setOption1(row.getCell(1).getStringCellValue());
			}
			
			if (row.getCell(2).getCellType() == CellType.STRING) {
				bankQuest.setOption2(row.getCell(2).getStringCellValue());
			}
			
			if (row.getCell(3).getCellType() == CellType.STRING) {
				bankQuest.setOption3(row.getCell(3).getStringCellValue());
			}
			
			if (row.getCell(4).getCellType() == CellType.STRING) {
				bankQuest.setOption4(row.getCell(4).getStringCellValue());
			}
			
			if (row.getCell(5).getCellType() == CellType.STRING) {
				bankQuest.setCorrect(row.getCell(5).getStringCellValue());
			}		
			
			bankRepo.save(bankConverter.toEntity(bankQuest));
		}
		return true;
	}

	private Workbook getWorkBook(MultipartFile file) {
		Workbook workbook = null;
		String extension = FilenameUtils.getExtension(file.getOriginalFilename());
		try {
			if (extension.equalsIgnoreCase("xlsx")) {
				workbook = new XSSFWorkbook(file.getInputStream());
			} else if (extension.equalsIgnoreCase("xls")) {
				workbook = new HSSFWorkbook(file.getInputStream());
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return workbook;
	}

}

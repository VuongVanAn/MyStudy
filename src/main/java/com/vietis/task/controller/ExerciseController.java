package com.vietis.task.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.vietis.task.dto.BankDTO;
import com.vietis.task.dto.ExerciseDTO;
import com.vietis.task.dto.UserDTO;
import com.vietis.task.service.BankService;
import com.vietis.task.service.ExerciseQuestService;
import com.vietis.task.util.ApiExchangeService;

@Controller
@RequestMapping("/admin/exercise")
public class ExerciseController {
	
	private String URL = "http://localhost:9090/api/exercise/";
	
	private String USER_URL = "http://localhost:9090/api/user/roles";
	
	private String BANK_URL = "http://localhost:9090/api/bank/";
	
	@Autowired
	private BankService bankService;
	
	@Autowired
	private ExerciseQuestService questService;
	
	@Autowired
	private ApiExchangeService exchangeService;
	
	@GetMapping("/list")
	public String findAll(Model model, HttpServletRequest request) {		
		String token = (String) request.getSession().getAttribute("MY_TOKEN");
		ExerciseDTO[] dtos = exchangeService.get(URL, ExerciseDTO[].class, token);
		List<ExerciseDTO> exerciseList = Arrays.asList(dtos);		
		UserDTO[] userDTOS = exchangeService.get(USER_URL, UserDTO[].class, token);
		List<UserDTO> userList = Arrays.asList(userDTOS);
		
		model.addAttribute("bankQuest", new BankDTO());
		model.addAttribute("userList", userList);
		model.addAttribute("exerciseList", exerciseList);
		return "admin/exercise/list";
	}
	
	@PostMapping("/save")
	public String createOrUpdateExercise(@ModelAttribute ExerciseDTO exercise, HttpServletRequest request,
			                         RedirectAttributes redirectAttributes) {
		String token = (String) request.getSession().getAttribute("MY_TOKEN");
		if (exercise.getId() == null) {
			@SuppressWarnings("unused")
			ExerciseDTO dto = exchangeService.post(URL, exercise, ExerciseDTO.class, token);
			redirectAttributes.addFlashAttribute("SuccessMessage", "Create Exercise Successfully!");
		} else {
			exchangeService.put(URL, exercise, token);
			redirectAttributes.addFlashAttribute("SuccessMessage", "Update Exercise Successfully!");		
		}		
		return "redirect:/admin/exercise/list";
	}
	
	@GetMapping("/findOne/{id}")
	public @ResponseBody ExerciseDTO findOne(@PathVariable Integer id, HttpServletRequest request) {
		String token = (String) request.getSession().getAttribute("MY_TOKEN");
		ExerciseDTO dto = exchangeService.get(URL + id, ExerciseDTO.class, token);
		return dto;
	}
	
	@RequestMapping(value = "/delete/{id}", method = {RequestMethod.DELETE, RequestMethod.GET})
	public String deleteExercise(@PathVariable Integer id, HttpServletRequest request, 
			                      RedirectAttributes redirectAttributes) {
		String token = (String) request.getSession().getAttribute("MY_TOKEN");
		exchangeService.delete(URL + id, token);
		redirectAttributes.addFlashAttribute("SuccessMessage", "Exercise is deleted Successfully!");
		return "redirect:/admin/exercise/list";
	}
	
	@SuppressWarnings("finally")
	@PostMapping("/upload")
	public String uploadBankQuest(@ModelAttribute BankDTO bankQuest, RedirectAttributes redirectAttributes) {
		try {
			boolean isFlag = bankService.saveData(bankQuest.getFile());
			if (!isFlag) {
				redirectAttributes.addFlashAttribute("ErrorMessage", "File Upload Exercise Questions Fail, Please Try Again!");
			} 
		} catch (NullPointerException e) {
			e.printStackTrace();
		} finally {
			redirectAttributes.addFlashAttribute("SuccessMessage", "File Upload Exercise Questions Successfully!");
			return "redirect:/admin/exercise/list";
		}		
	}
	
	@GetMapping("/generate/{id}")
	public String generateExerciseQuest(@PathVariable Integer id, HttpServletRequest request,
			RedirectAttributes redirectAttributes) {
        String token = (String) request.getSession().getAttribute("MY_TOKEN");
        ExerciseDTO dto = exchangeService.get(URL + id, ExerciseDTO.class, token);
        
		BankDTO[] dtos = exchangeService.get(BANK_URL, BankDTO[].class, token);
		List<BankDTO> bankDto = Arrays.asList(dtos);
		List<BankDTO> bankList = new ArrayList<>(bankDto);
		
		boolean isFlag = questService.generateData(bankList, id, dto.getAmount());
		if (isFlag) {
			redirectAttributes.addFlashAttribute("SuccessMessage", "Generate Exercise Questions Successfully!");
		} else {
			redirectAttributes.addFlashAttribute("ErrorMessage", "Generate Exercise Questions Fail, Please Try Again!");
		}
		return "redirect:/admin/exercise/list";
	}

}

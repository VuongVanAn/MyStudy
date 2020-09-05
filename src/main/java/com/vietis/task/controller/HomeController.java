package com.vietis.task.controller;

import java.util.Arrays;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.vietis.task.model.Exercise;
import com.vietis.task.util.ApiExchangeService;

@Controller
public class HomeController {
    
    private String URL = "http://localhost:9090/api/client/user/";
	
	@Autowired
	private ApiExchangeService exchangeService;
	
	@GetMapping("/home")
	public String home(Model model, HttpServletRequest request) {
		String token = (String) request.getSession().getAttribute("MY_TOKEN");
		Integer userId = (Integer) request.getSession().getAttribute("MY_LOGIN");
		if (token == null || userId == null) return "authorize";
		Exercise[] dtos = exchangeService.get(URL + userId, Exercise[].class, token);
		List<Exercise> exerciseList = Arrays.asList(dtos);
		model.addAttribute("userId", userId);
		model.addAttribute("exerciseList", exerciseList);
		return "client/home";
	}
	
}

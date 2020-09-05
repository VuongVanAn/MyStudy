package com.vietis.task.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.vietis.task.dto.JwtRequest;
import com.vietis.task.model.User;
import com.vietis.task.repository.UserRepository;
import com.vietis.task.util.ApiExchangeService;

@Controller
public class LoginController {
	
	private String URL = "http://localhost:9090/api/login";
	
	@Autowired
	private ApiExchangeService exchangeService;
	
	@Autowired
	private UserRepository userRepository;
	
	@RequestMapping("/login")
	public String login(Model model) {
		return "login";
	}
	
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public void login(@ModelAttribute JwtRequest loginForm, HttpServletResponse response, 
			          HttpServletRequest request) throws IOException { 
		String token = exchangeService.postNonToken(URL, loginForm, String.class);
		request.getSession().setAttribute("MY_TOKEN", token);
		
		User user = userRepository.findByEmail(loginForm.getEmail());
		request.getSession().setAttribute("MY_LOGIN", user.getId());
		request.getSession().setAttribute("AUTHORITY", user.getRoleid());
		
		if (user.getRoleid() == 2) {
			response.sendRedirect("/home");
		} else if (user.getRoleid() == 3) {
			response.sendRedirect("/admin/exercise/list");
		} else {
			response.sendRedirect("/admin/user/list");
		}
	}
	
}

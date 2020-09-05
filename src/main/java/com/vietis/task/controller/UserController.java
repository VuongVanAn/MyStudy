package com.vietis.task.controller;

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

import com.vietis.task.dto.RoleDTO;
import com.vietis.task.dto.UserDTO;
import com.vietis.task.service.GenericService;
import com.vietis.task.util.ApiExchangeService;

@Controller
@RequestMapping("/admin/user")
public class UserController {
	
	private String URL = "http://localhost:9090/api/user/";
	
	/*@Autowired
	private UserService userService;*/
	
	@Autowired
	private GenericService<RoleDTO> roleService;
	
	@Autowired
	private ApiExchangeService exchangeService;
	
	/*@GetMapping("/list")
	public String findAll(Model model, HttpServletRequest request, 
			      @RequestParam(defaultValue = "0") int page) {
		Page<UserDTO> userList = userService.findAll(PageRequest.of(page, 2));
		model.addAttribute("userList", userList);
		model.addAttribute("currentPage", page);
		model.addAttribute("roles", roleService.findAll());
		return "admin/user/list";
	}*/
	
	@GetMapping("/list")
	public String findAll(Model model, HttpServletRequest request) {
		String token = (String) request.getSession().getAttribute("MY_TOKEN");
		UserDTO[] dtos = exchangeService.get(URL, UserDTO[].class, token);
		List<UserDTO> userList = Arrays.asList(dtos);
		model.addAttribute("userList", userList);
		model.addAttribute("roles", roleService.findAll());
		return "admin/user/list";
	}
	
	@PostMapping("/save")
	public String createOrUpdateUser(@ModelAttribute UserDTO user, HttpServletRequest request,
			                         RedirectAttributes redirectAttributes) {
		String token = (String) request.getSession().getAttribute("MY_TOKEN");
		if (user.getId() == null) {
			@SuppressWarnings("unused")
			UserDTO dto = exchangeService.post(URL, user, UserDTO.class, token);
			redirectAttributes.addFlashAttribute("SuccessMessage", "Create User Successfully!");
		} else {
			exchangeService.put(URL, user, token);
			redirectAttributes.addFlashAttribute("SuccessMessage", "Update User Successfully!");
		}		
		return "redirect:/admin/user/list";
	}
	
	@GetMapping("/findOne/{id}")
	public @ResponseBody UserDTO findOne(@PathVariable Integer id, HttpServletRequest request) {
		String token = (String) request.getSession().getAttribute("MY_TOKEN");
		UserDTO dto = exchangeService.get(URL + id, UserDTO.class, token);
		return dto;
	}
	
	@RequestMapping(value = "/delete/{id}", method = {RequestMethod.DELETE, RequestMethod.GET})
	public String deleteUser(@PathVariable Integer id, HttpServletRequest request,
			                 RedirectAttributes redirectAttributes) {
		String token = (String) request.getSession().getAttribute("MY_TOKEN");
		exchangeService.delete(URL + id, token);
		redirectAttributes.addFlashAttribute("SuccessMessage", "Delete User Successfully!");
		return "redirect:/admin/user/list";
	}

}

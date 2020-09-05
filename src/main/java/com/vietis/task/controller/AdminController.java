package com.vietis.task.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class AdminController {
	
	@RequestMapping("/admin/index")
	public String show() {
		return "admin/index";
	}
	
	@RequestMapping(value = "/logout")
    public String logout(HttpServletRequest request, HttpServletResponse response) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null) {
            new SecurityContextLogoutHandler().logout(request, response, auth);
        }
        request.getSession().removeAttribute("MY_TOKEN");
        request.getSession().removeAttribute("MY_LOGIN");
        request.getSession().removeAttribute("AUTHORITY");
        return "redirect:/login/account";
    }
	
	@RequestMapping("/authorize")
	public String authorizePage() {
		return "authorize";
	}

}

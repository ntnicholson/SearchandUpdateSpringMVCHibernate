package com.hcl.controller;

import org.springframework.stereotype.Controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.View;

@Controller
@RequestMapping(value = "/admin")
public class UserController {

	@RequestMapping(value = "/dashboard", method = RequestMethod.GET)
	public String adminDashboard() {
		return "adminMenu";
	}
	@RequestMapping(value = "/editusers", method = RequestMethod.GET)
	public String selectUser() {
		return "allUsers";
	}
	@RequestMapping(value = "/edituse", method = RequestMethod.GET)
	public String editUsers() {
		return "editUser";
	}
}

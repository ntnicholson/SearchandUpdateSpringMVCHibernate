package com.hcl.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.websocket.server.PathParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.View;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.mvc.support.RedirectAttributesModelMap;

import com.hcl.entity.User;
import com.hcl.service.UserService;

@Controller
@RequestMapping(value = "/admin")
public class UserController {

	@Autowired
	UserService uService;
	
	@RequestMapping(value = "/dashboard", method = RequestMethod.GET)
	public String adminDashboard() {
		return "adminMenu";
	}
	//User Selection
	@RequestMapping(value = "/selectuser", method = RequestMethod.GET)
	public String selectUser() {
		return "selectUser";
	}
	@RequestMapping(value = "/selectuser", method = RequestMethod.POST)
	public String findUser(@RequestParam Long id, RedirectAttributes foward) 
	{
		///ModelAndView mav = new ModelAndView();
		
		if (uService.validateByID(id) == true) 
		{
			foward.addAttribute("id", id);
			return "redirect:edituser/{id}";
		}
		else {
			return "redirect:usernotfound";
		}
	}
	@GetMapping("/edituser/{id}")
	public ModelAndView editUser(@PathParam("id") String id) //@RequestParam Long id
	{
		System.out.println(id);
		ModelAndView mav = new ModelAndView();
		mav.setViewName("editUser");
		return mav;
	}
	@RequestMapping(value = "/edituser/{id}", method = RequestMethod.POST)
	public String updateUser(@RequestParam("name") String name, @RequestParam("email") String email, @PathVariable("id") String id) 
	{
		User u = new User();	
		u.setId((Long.parseLong(id)));
		u.setName(name);
		u.setEmail(email);
		uService.save(u);
		return "redirect:/admin/usersaved";
	}
//	@PostMapping(value = "/listusers")
//	public List<User> listUsers() 
//	{	
//		return uService.getAllUsers();
//	}
	@GetMapping(value = "/usersaved")
	public String userSaved() 
	{
		return "userSaved";
	}
	@GetMapping(value = "/usernotfound")
	public String userNotFound() 
	{
		return "userNotFound";
	}
}

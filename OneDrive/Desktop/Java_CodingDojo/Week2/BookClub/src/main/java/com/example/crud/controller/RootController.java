package com.example.crud.controller;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.crud.models.TempUser;
import com.example.crud.models.User;
import com.example.crud.services.UserService;

@Controller
@RequestMapping("/")
public class RootController 
{
	@Autowired
	private UserService userService;
	
	@GetMapping("")
	public String login_register(Model model)
	{
		model.addAttribute("userRegister", new User());
		model.addAttribute("userLogin", new TempUser());
		return "login_register";
	}
	
	@PostMapping("/register")
	public String register
		(
			@Valid @ModelAttribute("userRegister") User form_user,
			BindingResult res,
			Model model,
			HttpSession session
		)
	{
		User db_user = userService.register(form_user, res);
		
		if(res.hasErrors())
		{
			model.addAttribute("userLogin", new TempUser());
			return "login_register";
		}
		
		if(db_user==null) 
		{
			model.addAttribute("userLogin", new TempUser());
			return "login_register";
		}
		session.setAttribute("user", form_user);
		return "redirect:/books";
	}
	
	@PostMapping("/login")
	public String login
		(
			@Valid @ModelAttribute("userLogin") TempUser tempUser, 
			BindingResult res, 
			Model model,
			HttpSession session
		)
	{
		User user = userService.login(tempUser, res);

		if(res.hasErrors())
		{
			model.addAttribute("userRegister", new User());
			return "login_register";
		}
		
		
		if(user==null)
		{
			model.addAttribute("userRegister", new User());
			return "login_register";
		}
		session.setAttribute("user", user);
		return "redirect:/books";
	}
	
	@GetMapping("/logout")
	public String logout(HttpSession session)
	{
		userService.logout(session);
		return "redirect:/";
	}
}
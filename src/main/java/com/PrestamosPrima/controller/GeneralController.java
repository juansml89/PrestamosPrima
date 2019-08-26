package com.PrestamosPrima.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.PrestamosPrima.entity.User;
import com.PrestamosPrima.service.UserService;

@Controller
@RequestMapping("/")
public class GeneralController {
	UserService userService;
	AccountController accountController;
	UserController userController;
	@Autowired
	public GeneralController(UserService theUserService, AccountController accountController, UserController userController) {
		userService = theUserService;
		this.accountController = accountController;
		this.userController = userController;
	}
	
	@GetMapping("/")
	public String getUser(Model theModel) {
		User theUser = new User();
		
		// add to the spring model
		theModel.addAttribute("user", theUser);
		theModel.addAttribute("logInError", false);
		return "welcome-page";
		
	}
	
	
	// add mapping for "/list"
	@PostMapping("/login")
	public String getUser(Model theModel, @ModelAttribute("user") User theUser) {
		String email = theUser.getEmail();
		String password = theUser.getPassword();
		
		User obtainedUser = userService.findByEmailAndPassword(email, password);
		
		// add to the spring model
		if(obtainedUser == null) {
			theModel.addAttribute("logInError", true);
			return "welcome-page";
		}else {
			theModel.addAttribute("user", obtainedUser);
			return accountController.listAccountsByUserId(theModel, obtainedUser);
		}

		
	}
	
	// add mapping for "/list"
	@PostMapping("/signup")
	public String signUp(Model theModel) {
		
		return userController.showFormForAdd(theModel);
		
	}
	
}

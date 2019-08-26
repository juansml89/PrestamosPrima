package com.PrestamosPrima.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.PrestamosPrima.entity.User;
import com.PrestamosPrima.service.UserService;

@Controller
@RequestMapping("/users")
public class UserController {
	
	private UserService userService;
	
	@Autowired
	public UserController(UserService theUserService) {
		userService = theUserService;
	}
	
	// add mapping for "/list"
	@GetMapping("/list")
	public String listUsers(Model theModel) {
		
		List<User> theUsers = userService.findAll();
		
		// add to the spring model
		theModel.addAttribute("users", theUsers);
		
		return "users/list-users";
		
	}
	
	
	
	// add mapping for "/list"
	@GetMapping("/showFormForAdd")
	public String showFormForAdd(Model theModel) {
		
		// create model attribute to bind form data
		User theUser = new User();
		
		// add to the spring model
		theModel.addAttribute("user", theUser);
		
		return "users/user-form";	
	}
	
	
	// add mapping for "/list"
	@PostMapping("/save")
	public String saveUser(@ModelAttribute("user") User theUser) {
		
		// save the  
		userService.save(theUser);
		
		// use a redirect to prevent duplicate submissions		
		return "redirect:/";
		
	}
	
	
	
	// add mapping for "/showFormForUpdate
	@GetMapping("/showFormForUpdate")
	public String showFormForUpdate(@RequestParam("userId") int theId, Model theModel) {
		
		// get the  from the service
		User theUser = userService.findById(theId);
		
		
		// set  as a model attribute to pre populate the form
		theModel.addAttribute("user", theUser);
		
		
		//send over to out form
		return "users/user-form";

	}
	
	@GetMapping("/delete")
	public String delete(@RequestParam("userId") int theId) {
		// delete the 
		userService.deleteUser(theId);
		
		// redirect to /s/list
		return "redirect:/users/list";
		
	}
	
	
	
}





































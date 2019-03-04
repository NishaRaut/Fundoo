package com.bridgelabz.fundoo.user.controllers;
import org.slf4j.Logger;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bridgelabz.fundoo.user.dto.LoginDTO;
import com.bridgelabz.fundoo.user.dto.UserDTO;
import com.bridgelabz.fundoo.user.model.User;
import com.bridgelabz.fundoo.user.services.UserServices;


@RestController 

public class UserController {
	
	static final Logger logger=LoggerFactory.getLogger(UserController.class);
	
	@Autowired
	UserServices userServices;
	
	@RequestMapping(value = "/api/user/register")
	@PostMapping
	public String userRegister(@RequestBody UserDTO userDTO) throws Exception {
		
		logger.info("userDTO:"+userDTO);
		logger.trace("User Registration");
		System.out.println("Hello"+userDTO);
		try {
		User user = userServices.register(userDTO);
		if(user != null)
	    	return "Registration successful";
		else 
			
			return "Registration un-successful";
		}
		catch(Exception e) {
			return e.getMessage();
		}
	}
	
	@RequestMapping(value = "/api/user/login")
	@PostMapping
	public String login(@RequestBody LoginDTO loginDTO) throws Exception
	{
		String st="abc";
		logger.info("loginDTO:"+loginDTO);
		logger.trace("Login");
		boolean flag = false;
//		User user = userServices.login(loginDTO);
		return st;

	
	}

}

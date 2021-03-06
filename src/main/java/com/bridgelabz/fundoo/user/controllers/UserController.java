package com.bridgelabz.fundoo.user.controllers;
import javax.validation.Valid;


import org.slf4j.Logger;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bridgelabz.fundoo.response.Response;
import com.bridgelabz.fundoo.user.dto.LoginDTO;
import com.bridgelabz.fundoo.user.dto.UserDTO;
import com.bridgelabz.fundoo.user.services.UserServices;


@RestController 

public class UserController {
	
	static final Logger logger=LoggerFactory.getLogger(UserController.class);
	
	@Autowired
	UserServices userServices;
	
	@RequestMapping(value = "/api/user/register")
	@PostMapping
	public ResponseEntity<Response> userRegister(@Valid @RequestBody UserDTO userDTO) throws Exception {
		
		logger.info("userDTO:"+userDTO);
		logger.trace("User Registration");
		System.out.println("Hello"+userDTO);
		Response response = userServices.register(userDTO);
		return new ResponseEntity<Response>(response , HttpStatus.OK);
	}
	
	@RequestMapping(value = "/api/user/login")
	@PostMapping
	public ResponseEntity<Response> login(@RequestBody LoginDTO loginDTO) throws Exception
	{
	
		logger.info("loginDTO:"+loginDTO);
		logger.trace("Login");
		boolean flag = false;
		Response response = userServices.login(loginDTO);
		System.out.println(response.getStatusCode());
		return new ResponseEntity<Response>(response, HttpStatus.OK);

	
	}

	@GetMapping("/api/user/{token}")
	public String emailValidation(@PathVariable String token) throws Exception
	{
		logger.info("Token:"+token);
		String result = userServices.validateEmailId(token);
		return result;
	}


}

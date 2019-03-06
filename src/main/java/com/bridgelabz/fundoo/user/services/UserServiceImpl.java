package com.bridgelabz.fundoo.user.services;

import java.time.LocalDate;


import java.util.Optional;

import org.modelmapper.ModelMapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.bridgelabz.fundoo.response.Response;
import com.bridgelabz.fundoo.user.dto.LoginDTO;
import com.bridgelabz.fundoo.user.dto.UserDTO;
import com.bridgelabz.fundoo.user.model.User;
import com.bridgelabz.fundoo.user.repository.UserRepository;
import com.bridgelabz.fundoo.utility.UserToken;

@Service
public class UserServiceImpl implements UserServices {
	@Autowired
	private UserRepository userRepository;

	@Autowired
	public Environment environment;
	
	@Autowired
	private PasswordEncoder passwordEncoder;

	@Autowired
	private ModelMapper modelMapper;
	
	@Autowired
	Response response;
	
   @Autowired
	MailService mailService;
   @Autowired
   UserToken userToken;
	
	@Override
	public Response register(UserDTO userDTO) throws Exception {
		System.out.println("hello");
		Optional<User> useravailable = userRepository.findByEmail(userDTO.getEmail());
		//To check user is available or not if found it will show duplicate user found.
		if (useravailable.isPresent()) {
			throw new Exception("Dublicate user found");
		}
		//Copy user data userDTO to user class
		User user = modelMapper.map(userDTO, User.class);
		//set password in encrypted form
		user.setPassword(passwordEncoder.encode(user.getPassword()));
		
		LocalDate today = LocalDate.now();
		user.setRegisteredDate(today);
		user.setModifiedDate(today);
		
		user = userRepository.save(user);
		mailService.sendEmail(user);//, "mail for Registration" , getUrl(user.getId()));
		response.setStatusCode(100);
		response.setStatusMessage(environment.getProperty("1"));
	    return response;
		
	}

	@Override
	public Response login(LoginDTO loginuser) throws Exception {
		User useravailable = userRepository.findByEmail(loginuser.getEmail()).orElseThrow(()->new Exception("User not found..."));
		System.out.println("login user password"+passwordEncoder.encode(loginuser.getPassword()));
		System.out.println("Verification : "+useravailable.isVerification());
		if(passwordEncoder.matches(loginuser.getPassword(),useravailable.getPassword())&& useravailable.isVerification()) 
		{
			String generateToken =userToken.generateToken(useravailable.getId());
			response.setStatusCode(3);
			response.setStatusMessage(environment.getProperty("2"));
			response.setToken(generateToken);
		}
		else
		{ 
			response.setStatusCode(4);
			response.setStatusMessage("Email not valid......");
		}
	return response; 
}

	@Override
	public String validateEmailId(String token) throws Exception {
		// TODO Auto-generated method stub
		Long id = userToken.tokenVerify(token);
		User user = userRepository.findById(id).orElseThrow(() -> new Exception("User not found")); 
		user.setVerification(true);		
		userRepository.save(user);
        return "Successfully verified";
	}

	
}

package com.bridgelabz.fundoo.user.services;

import java.time.LocalDate;

import java.util.Optional;

import org.modelmapper.ModelMapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.bridgelabz.fundoo.user.dto.LoginDTO;
import com.bridgelabz.fundoo.user.dto.UserDTO;
import com.bridgelabz.fundoo.user.model.User;
import com.bridgelabz.fundoo.user.repository.UserRepository;

@Service
public class UserServiceImpl implements UserServices {
	@Autowired
	private UserRepository userRepository;

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Autowired
	private ModelMapper modelMapper;

	@Override
	public User register(UserDTO userDTO) throws Exception {
		System.out.println("hello");
		Optional<User> useravailable = userRepository.findByEmail(userDTO.getEmail());
		if (useravailable.isPresent()) {
			throw new Exception("Dublicate user found");
		}

		User user = modelMapper.map(userDTO, User.class);
		user.setPassword(passwordEncoder.encode(userDTO.getPassword()));
		LocalDate today = LocalDate.now();
		user.setRegisteredDate(today);
		user.setModifiedDate(today);
		return userRepository.save(user);
		
	}

	@Override
	public String login(LoginDTO loginuser) throws Exception {
		Optional<User> useravailable = userRepository.findByEmail(loginuser.getEmail());
		return null;
	}

}

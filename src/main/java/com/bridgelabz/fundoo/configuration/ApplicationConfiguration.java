package com.bridgelabz.fundoo.configuration;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
@Configuration
public class ApplicationConfiguration {
	@Bean
	public ModelMapper getMapper()
	{
		return new ModelMapper();
	}
	
	
	@Bean
	public PasswordEncoder getPasswordEncoder()
	{ 
		return new BCryptPasswordEncoder(); 
}
}

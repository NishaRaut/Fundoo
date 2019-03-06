package com.bridgelabz.fundoo.user.services;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.core.env.Environment;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import com.bridgelabz.fundoo.user.model.User;
import com.bridgelabz.fundoo.utility.UserToken;


@Service
public class MailService {
	@Autowired
	JavaMailSender javaMailSender;
	
	@Autowired
	UserToken tokenGenerator;
	
	@Autowired
	Environment environment;
	
	public void sendEmail(User user) throws Exception
	{  
		SimpleMailMessage mail = new SimpleMailMessage();
		mail.setTo(user.getEmail());
		mail.setFrom(this.environment.getProperty("spring.mail.username"));
		mail.setSubject("usr resistration verification");
		String userActivationLink="114.79.180.62:8080/user/useractivation/";
		userActivationLink = userActivationLink  + tokenGenerator.generateToken(user.getId());
		mail.setText(userActivationLink);
		try {
		javaMailSender.send(mail);
		}
		catch(Exception e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
		}
	}
	

}

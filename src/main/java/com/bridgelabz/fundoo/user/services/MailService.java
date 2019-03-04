package com.bridgelabz.fundoo.user.services;

import org.hibernate.cfg.Environment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import com.bridgelabz.fundoo.user.model.User;
import com.firebase.security.token.TokenGenerator;

@Service
public class MailService {
	@Autowired
	JavaMailSender javaMailSender;
	@Autowired
	TokenGenerator tockenGenerator;
	Environment environment;
	public void sendEmail(User user)
	{  
		SimpleMailMesssage mail = new SimpleMailMesssage();
		mail.setTo(user.getEmail());
		mail.setFrom(this.environment.getProperty("spring.mail.username"));
		mail.setSubject("");
	}
	

}

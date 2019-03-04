package com.bridgelabz.fundoo.user.services;
import com.bridgelabz.fundoo.user.dto.LoginDTO;
import com.bridgelabz.fundoo.user.dto.UserDTO;
import com.bridgelabz.fundoo.user.model.User;

public interface UserServices {
	public User register(UserDTO userDTO) throws Exception;
	public String login(LoginDTO loginuser) throws Exception;
//	public String validateEmailId(String token) throws Exception; 
//	public String forgotPassword(String email) throws Exception;
//  public String resetPassword(String token, String password) throws Exception;
}

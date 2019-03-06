package com.bridgelabz.fundoo.user.services;
import com.bridgelabz.fundoo.response.Response;
import com.bridgelabz.fundoo.user.dto.LoginDTO;
import com.bridgelabz.fundoo.user.dto.UserDTO;
import com.bridgelabz.fundoo.user.model.User;

public interface UserServices {
	public Response register(UserDTO userDTO) throws Exception;
	public Response login(LoginDTO loginuser) throws Exception;
	public String validateEmailId(String token) throws Exception; 
//	public Response forgotPassword(String email) throws Exception;
//  public String resetPassword(String token, String password) throws Exception;
	
}

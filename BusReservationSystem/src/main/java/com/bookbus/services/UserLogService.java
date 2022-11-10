package com.bookbus.services;

import com.bookbus.exceptions.LogException;
import com.bookbus.models.LoginDTO;

public interface UserLogService {
	
	public String userLogIn(LoginDTO dto)throws LogException;

	public String userLogOut(Integer userId)throws LogException;

}

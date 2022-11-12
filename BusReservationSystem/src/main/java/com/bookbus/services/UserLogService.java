package com.bookbus.services;

import com.bookbus.exceptions.LogException;
import com.bookbus.models.UserLoginDTO;

public interface UserLogService {
	
	public String userLogIn(UserLoginDTO dto)throws LogException;

	public String userLogOut(Integer userId)throws LogException;

}

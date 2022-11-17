package com.bookbus.services;

import com.bookbus.dto.UserLoginDTO;
import com.bookbus.exceptions.LogException;

public interface UserLogService {
	
	public String userLogIn(UserLoginDTO dto)throws LogException;

	public String userLogOut(Integer userId)throws LogException;

}

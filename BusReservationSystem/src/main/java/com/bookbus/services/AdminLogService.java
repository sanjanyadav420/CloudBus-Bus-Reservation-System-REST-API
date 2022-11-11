package com.bookbus.services;

import com.bookbus.exceptions.LogException;
import com.bookbus.models.AdminLoginDTO;

public interface AdminLogService {
	
	public String adminLogIn(AdminLoginDTO dto)throws LogException;

	public String adminLogOut(Integer adminId)throws LogException;
}

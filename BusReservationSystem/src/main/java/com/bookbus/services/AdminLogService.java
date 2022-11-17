package com.bookbus.services;

import com.bookbus.dto.AdminLoginDTO;
import com.bookbus.exceptions.LogException;

public interface AdminLogService {
	
	public String adminLogIn(AdminLoginDTO dto)throws LogException;

	public String adminLogOut(Integer adminId)throws LogException;
}

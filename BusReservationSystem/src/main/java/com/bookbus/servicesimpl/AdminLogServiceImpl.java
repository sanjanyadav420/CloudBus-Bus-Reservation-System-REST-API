package com.bookbus.servicesimpl;

import java.time.LocalDateTime;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.bookbus.exceptions.LogException;
import com.bookbus.models.Admin;
import com.bookbus.models.AdminLoginDTO;
import com.bookbus.models.CurrentAdminSession;
import com.bookbus.repositories.AdminLogRepo;
import com.bookbus.repositories.AdminRepo;
import com.bookbus.services.AdminLogService;

@Service
public class AdminLogServiceImpl implements AdminLogService {

	@Autowired
	private AdminRepo aRepo;
	
	@Autowired
	private AdminLogRepo adminLogRepo;
	
	@Override
	public String adminLogIn(AdminLoginDTO dto) throws LogException {
		
		Admin admin = aRepo.findByAdminUserName(dto.getAdminUserName());
		
		if(admin == null) throw new LogException("Please Enter a valid admin UserName.");
		
		if(admin.getAdminPassword().equals(dto.getPassword()))
		{
			Optional<CurrentAdminSession> cAdminSession = adminLogRepo.findById(admin.getAdminId());
			
			if(cAdminSession.isPresent()) throw new LogException("You are already LoggedIn with this UserName "+dto.getAdminUserName());
			
			adminLogRepo.save(new CurrentAdminSession(admin.getAdminId(), LocalDateTime.now()));
			
			return "You are loggedIn successfully.";
		}
		else
			throw new LogException("Please Enter a valid Password.");
	}

	@Override
	public String adminLogOut(Integer adminId) throws LogException {
		
		Optional<CurrentAdminSession> cAdminSession = adminLogRepo.findById(adminId);
		
		if(cAdminSession.isPresent())
		{
			adminLogRepo.deleteById(adminId);
			
			return "You are successfully logged Out.";
		}
		else
			throw new LogException("Please provide a valid Id.");
	}

	

}

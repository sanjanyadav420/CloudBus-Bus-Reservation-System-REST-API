package com.bookbus.servicesimpl;

import java.time.LocalDateTime;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.bookbus.exceptions.LogException;
import com.bookbus.models.CurrentUserSession;
import com.bookbus.models.UserLoginDTO;
import com.bookbus.models.User;
import com.bookbus.repositories.UserLogRepo;
import com.bookbus.repositories.UserRepo;
import com.bookbus.services.UserLogService;

@Service
public class UserLogServiceImpl implements UserLogService {

	@Autowired
	private UserRepo uRepo;
	
	@Autowired
	private UserLogRepo userLogRepo;
	
	@Override
	public String userLogIn(UserLoginDTO dto) throws LogException {
		
		User user = uRepo.findByEmailId(dto.getEmailId());
		
		if(user == null) throw new LogException("Please Enter a valid Email Id.");
		
		if(user.getPassword().equals(dto.getPassword()))
		{
			Optional<CurrentUserSession> cUserSession = userLogRepo.findById(user.getUserId());
			
			if(cUserSession.isPresent()) throw new LogException("You are already LoggedIn with this EmailId "+dto.getEmailId());
			
			userLogRepo.save(new CurrentUserSession(user.getUserId(), "User", LocalDateTime.now()));
			
			return "Hi "+user.getFirstName()+" you are loggedIn successfully.";
		}
		else
			throw new LogException("Please Enter a valid Password.");
	}

	@Override
	public String userLogOut(Integer userId) throws LogException {
		
		Optional<CurrentUserSession> cUserSession = userLogRepo.findById(userId);
		
		if(cUserSession.isPresent())
		{
			userLogRepo.deleteById(userId);
			
			return "You are successfully logged Out.";
		}
		else
			throw new LogException("Please provide a valid userId or you are not logged In.");
		
	}

	

}

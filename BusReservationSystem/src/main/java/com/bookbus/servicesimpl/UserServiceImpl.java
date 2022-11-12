package com.bookbus.servicesimpl;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.bookbus.exceptions.LogException;
import com.bookbus.exceptions.UserException;
import com.bookbus.models.CurrentAdminSession;
import com.bookbus.models.CurrentUserSession;
import com.bookbus.models.User;
import com.bookbus.repositories.AdminLogRepo;
import com.bookbus.repositories.UserLogRepo;
import com.bookbus.repositories.UserRepo;
import com.bookbus.services.UserService;

@Service
public class UserServiceImpl implements UserService{

	@Autowired
	private UserRepo uRepo;
	
	@Autowired
	private UserLogRepo ulRepo;
	
	@Autowired
	private AdminLogRepo alRepo;

	@Override
	public User addUser(User user) throws UserException, LogException {
		
		User user1 = uRepo.findByEmailId(user.getEmailId());
		
		if(user1 == null)
			return uRepo.save(user);
		else
			throw new UserException("User already exist with email Id "+user.getEmailId());
	}

	@Override
	public User updateUser(User user) throws UserException, LogException {
		
		Optional<CurrentUserSession> cusess = ulRepo.findById(user.getUserId());
		
		if(cusess.isPresent())
		{
			Optional<User> user1 = uRepo.findById(user.getUserId());
			
			if(user1.isPresent())
				return uRepo.save(user);
			else
				throw new UserException("User does not exist with user Id "+user.getUserId());
		}
		else
			throw new LogException("Your userId is incorrect or you are not logged In.");
		
		
	}

	@Override
	public User deleteUser(Integer userId) throws UserException, LogException {
		
		Optional<CurrentUserSession> cusess = ulRepo.findById(userId);
		
		if(cusess.isPresent())
		{
			Optional<User> user1 = uRepo.findById(userId);
			
			if(user1.isPresent()) 
			{
				uRepo.deleteById(userId);
				return user1.get();
			}
			else
				throw new UserException("User does not exist with user Id "+user1.get().getUserId());
		}
		else
			throw new LogException("Your userId is incorrect or you are not logged In.");
		
		
	}

	@Override
	public User viewUser(Integer userId) throws UserException, LogException {
		
		Optional<User> user1 = uRepo.findById(userId);
		
		if(user1.isPresent()) 
			return user1.get();
		else
			throw new UserException("User does not exist with user Id "+user1.get().getUserId());
	}

	@Override
	public List<User> viewAllUser(Integer adminId) throws UserException, LogException {
		
		Optional<CurrentAdminSession> casess = alRepo.findById(adminId);
		
		if(casess.isPresent())
		{
			List<User> users = uRepo.findAll();
			
			if(users.size() == 0)
				throw new UserException("No users found...");
			else
				return users;
		}
		else
			throw new LogException("Admins can only see list of users If you are please logIn first.");
		
		
	}
	
	

}

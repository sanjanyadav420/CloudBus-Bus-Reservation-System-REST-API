package com.bookbus.services;

import java.util.List;

import com.bookbus.exceptions.LogException;
import com.bookbus.exceptions.UserException;
import com.bookbus.models.User;

public interface UserService {
	
	public User addUser(User user) throws UserException, LogException;
	public User updateUser(User user) throws UserException, LogException;
	public User deleteUser(Integer userId) throws UserException, LogException;
	public User viewUser(Integer userId) throws UserException, LogException;
	public List<User> viewAllUser(Integer adminId) throws UserException, LogException;
}

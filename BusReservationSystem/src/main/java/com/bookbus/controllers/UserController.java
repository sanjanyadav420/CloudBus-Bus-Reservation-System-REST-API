package com.bookbus.controllers;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.bookbus.exceptions.LogException;
import com.bookbus.exceptions.UserException;
import com.bookbus.models.User;
import com.bookbus.services.UserService;

@RestController
@RequestMapping("/cloudbus")
public class UserController {
	
	@Autowired
	private UserService uService;
	
	@PostMapping("/users")
	public ResponseEntity<User> addUser(@RequestBody User user) throws UserException, LogException
	{
		User user1 = uService.addUser(user);
		
		return new ResponseEntity<User>(user1, HttpStatus.CREATED);
	}
	
	@PutMapping("/users")
	public ResponseEntity<User> updateUser(@RequestBody User user) throws UserException, LogException
	{
		User user1 = uService.updateUser(user);
		
		return new ResponseEntity<User>(user1, HttpStatus.OK);
	}
	
	@DeleteMapping("/users/{userId}")
	public ResponseEntity<User> deleteUser(@PathVariable("userId") Integer userId) throws UserException, LogException
	{
		User user1 = uService.deleteUser(userId);
		
		return new ResponseEntity<User>(user1, HttpStatus.OK);
	}
	
	@GetMapping("/users/{userId}")
	public ResponseEntity<User> viewUser(@PathVariable("userId") Integer userId) throws UserException, LogException
	{
		User user1 = uService.deleteUser(userId);
		
		return new ResponseEntity<User>(user1, HttpStatus.FOUND);
	}
	
	@GetMapping("/admins/{adminId}")
	public ResponseEntity<List<User>> viewAllUser(@PathVariable("adminId") Integer adminId) throws UserException, LogException
	{
		List<User> users = uService.viewAllUser(adminId);
		
		return new ResponseEntity<List<User>>(users, HttpStatus.FOUND);
	}
}

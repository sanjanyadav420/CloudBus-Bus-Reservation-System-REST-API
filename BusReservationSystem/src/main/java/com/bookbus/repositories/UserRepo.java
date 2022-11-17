package com.bookbus.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bookbus.models.User;

public interface UserRepo extends JpaRepository<User, Integer>{
	
	public User findByEmailId(String emailId);
	
}

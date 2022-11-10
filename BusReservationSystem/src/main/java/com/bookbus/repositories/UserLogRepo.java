package com.bookbus.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import com.bookbus.models.CurrentUserSession;

public interface UserLogRepo extends JpaRepository<CurrentUserSession, Integer>{
	
	
}

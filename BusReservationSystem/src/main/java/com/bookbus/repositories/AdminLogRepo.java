package com.bookbus.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import com.bookbus.models.CurrentAdminSession;

public interface AdminLogRepo extends JpaRepository<CurrentAdminSession, Integer>{
	
	
}

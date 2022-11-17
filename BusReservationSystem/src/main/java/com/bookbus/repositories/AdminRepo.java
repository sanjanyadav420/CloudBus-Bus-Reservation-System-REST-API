package com.bookbus.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import com.bookbus.models.Admin;

public interface AdminRepo extends JpaRepository<Admin, Integer>{
	
	public Admin findByAdminUserName(String adminUserName);
}

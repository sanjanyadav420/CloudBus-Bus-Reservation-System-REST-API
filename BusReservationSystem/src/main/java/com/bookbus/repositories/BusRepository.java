package com.bookbus.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.bookbus.models.Bus;

@Repository
public interface BusRepository extends JpaRepository<Bus,Integer>{
	
	public List<Bus> findByBusType(String busType);
	
	@Query("from Bus")
	public List<Bus> viewAllBus();
}

package com.bookbus.repositories;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.bookbus.models.Reservation;

@Repository
public interface ReservationRepository extends JpaRepository<Reservation,Integer>{
	
	public List<Reservation> findByReservationDate(LocalDate date);
	
	@Query("from Reservation")
	public List<Reservation> getAllReservationDetails();
	
}

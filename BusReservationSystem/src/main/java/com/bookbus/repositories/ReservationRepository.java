package com.bookbus.repositories;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.bookbus.models.Reservation;

@Repository
public interface ReservationRepository extends JpaRepository<Reservation,Integer>{
	
//	@Query("select from Reservation r JOIN User u ON r.reservationId=u.reservationId where userId=?1 AND reservationDate?2")
//	public List<Reservation> getReservationByDate(Integer UserId, LocalDate date);
	
	@Query("from Reservation")
	public List<Reservation> getAllReservationDetails();
	
}

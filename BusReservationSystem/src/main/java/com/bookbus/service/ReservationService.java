package com.bookbus.service;

import java.time.LocalDate;
import java.util.List;

import com.bookbus.exceptions.ReservationNotFoundException;
import com.bookbus.models.Reservation;

public interface ReservationService {
	
	public Reservation addReservation(Reservation reservation);
	
	public Reservation updateReservation(Reservation reservation) throws ReservationNotFoundException;
	
	public Reservation deleteReservation(int reservationId) throws ReservationNotFoundException;
	
	public Reservation viewReservation(int reservationId) throws ReservationNotFoundException;
	
	public List<Reservation> viewAllReservation() throws ReservationNotFoundException;
	
	public List<Reservation> getAllReservation(LocalDate date) throws ReservationNotFoundException;
	
}

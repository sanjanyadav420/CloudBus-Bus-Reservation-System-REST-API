package com.bookbus.services;

import java.time.LocalDate;
import java.util.List;

import com.bookbus.dto.ReservationDto;
import com.bookbus.exceptions.BusNotFoundException;
import com.bookbus.exceptions.ReservationNotFoundException;
import com.bookbus.models.Reservation;

public interface ReservationService {
	
	public Reservation addReservation(Integer busId,ReservationDto reservation) throws BusNotFoundException;
	
	public Reservation updateReservation(Integer busId,ReservationDto reservation) throws ReservationNotFoundException, BusNotFoundException;
	
	public Reservation deleteReservation(Integer reservationId) throws ReservationNotFoundException;
	
	public Reservation viewReservation(Integer reservationId) throws ReservationNotFoundException;
	
	public List<Reservation> viewAllReservation() throws ReservationNotFoundException;
	
	public List<Reservation> getAllReservation(LocalDate date) throws ReservationNotFoundException;

}

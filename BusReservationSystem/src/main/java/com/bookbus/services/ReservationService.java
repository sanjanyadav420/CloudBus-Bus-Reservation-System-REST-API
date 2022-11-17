package com.bookbus.services;

import java.time.LocalDate;
import java.util.List;

import com.bookbus.dto.ReservationDto;
import com.bookbus.exceptions.BusNotFoundException;
import com.bookbus.exceptions.LogException;
import com.bookbus.exceptions.ReservationNotFoundException;
import com.bookbus.exceptions.UserException;
import com.bookbus.models.Reservation;

public interface ReservationService {
	
	public Reservation addReservation(Integer userId,Integer busId,ReservationDto reservation) throws BusNotFoundException, LogException, Exception;
	
	public Reservation updateReservation(Integer userId,Integer busId,ReservationDto reservation) throws ReservationNotFoundException, BusNotFoundException, LogException, Exception;
	
	public Reservation deleteReservation(Integer userId,Integer reservationId) throws ReservationNotFoundException, LogException;
	
	public Reservation viewReservation(Integer reservationId) throws ReservationNotFoundException, UserException;
	
	public List<Reservation> viewAllReservation(Integer adminId) throws ReservationNotFoundException, LogException;
	
	public List<Reservation> getAllReservation(LocalDate date) throws ReservationNotFoundException;

}

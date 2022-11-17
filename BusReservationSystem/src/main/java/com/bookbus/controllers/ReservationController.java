package com.bookbus.controllers;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bookbus.dto.ReservationDto;
import com.bookbus.exceptions.BusNotFoundException;
import com.bookbus.exceptions.LogException;
import com.bookbus.exceptions.ReservationNotFoundException;
import com.bookbus.exceptions.UserException;
import com.bookbus.models.Reservation;
import com.bookbus.services.ReservationService;

@RestController
@RequestMapping("/cloudbus")
public class ReservationController {
	
	@Autowired
	private ReservationService resService;
	
	
	@PostMapping("/reservations/{userId}/{busId}")
	public ResponseEntity<Reservation> addReservation(@PathVariable("userId") Integer userId,@PathVariable("busId") Integer busId,@Valid @RequestBody ReservationDto reservation) throws BusNotFoundException, LogException, Exception{
		Reservation savedReservation=resService.addReservation(userId,busId,reservation);
		return new ResponseEntity<Reservation>(savedReservation,HttpStatus.CREATED);
	}
	
	
	@PutMapping("/reservations/{userId}/{busId}")
	public ResponseEntity<Reservation> updateReservation(@PathVariable("userId") Integer userId,@PathVariable("busId") Integer busId,@Valid @RequestBody ReservationDto reservation) throws ReservationNotFoundException, BusNotFoundException, LogException,Exception {
		Reservation updatedReservation=resService.updateReservation(userId,busId,reservation);
		return new ResponseEntity<Reservation>(updatedReservation,HttpStatus.ACCEPTED);
	}
	
	
	@DeleteMapping("/reservations/{userId}/{reservationId}")
	public ResponseEntity<Reservation> deleteReservation(@PathVariable("userId") Integer userId, @PathVariable("reservationId") Integer reservationId) throws ReservationNotFoundException, LogException {
		Reservation deletedReservation=resService.deleteReservation(userId, reservationId);
		return new ResponseEntity<Reservation>(deletedReservation,HttpStatus.OK);
	}
	
	
	@GetMapping("/reservations/{reservationId}")
	public  ResponseEntity<Reservation> viewReservation(@PathVariable("reservationId") Integer reservationId) throws ReservationNotFoundException, UserException {
		Reservation viewReservation=resService.viewReservation(reservationId);
		return new ResponseEntity<Reservation>(viewReservation,HttpStatus.CREATED);
	}
	
	
	@GetMapping("/reservationsadmin/{adminId}")
	public ResponseEntity<List<Reservation>> viewAllReservation(@PathVariable("adminId") Integer adminId) throws ReservationNotFoundException, LogException{
		List<Reservation> allReservations=resService.viewAllReservation(adminId);
		return new ResponseEntity<List<Reservation>>(allReservations,HttpStatus.CREATED);
	}
	
	
	@GetMapping("/allreservations/{date}")
	public ResponseEntity<List<Reservation>> getAllReservation(@PathVariable("date") String reservationDate) throws Exception{
		LocalDate ld=null;
		
		DateTimeFormatter dtf=DateTimeFormatter.ofPattern("yyyy-MM-dd");
		try {
			ld=LocalDate.parse(reservationDate,dtf);
		} catch (Exception e) {
			throw new Exception("Date must be yyyy-MM-dd formate");
		}
		List<Reservation> allReservationsByDate=resService.getAllReservation(ld);
		return new ResponseEntity<List<Reservation>>(allReservationsByDate,HttpStatus.CREATED);
	}
}

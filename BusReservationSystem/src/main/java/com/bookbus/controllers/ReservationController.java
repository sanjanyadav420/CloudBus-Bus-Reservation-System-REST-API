package com.bookbus.controllers;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.bookbus.exceptions.ReservationNotFoundException;
import com.bookbus.models.Reservation;
import com.bookbus.services.ReservationService;

@RestController
public class ReservationController {
	
	@Autowired
	private ReservationService resService;
	
	
	@PostMapping("/reservatons")
	public ResponseEntity<Reservation> addReservation(@RequestBody Reservation reservation) {
		Reservation savedReservation=resService.addReservation(reservation);
		return new ResponseEntity<Reservation>(savedReservation,HttpStatus.CREATED);
	}
	
	
	@PutMapping("/reservatons")
	public ResponseEntity<Reservation> updateReservation(@RequestBody Reservation reservation) throws ReservationNotFoundException {
		Reservation updatedReservation=resService.updateReservation(reservation);
		return new ResponseEntity<Reservation>(updatedReservation,HttpStatus.ACCEPTED);
	}
	
	
	@DeleteMapping("/reservatons/{id}")
	public ResponseEntity<Reservation> deleteReservation(@PathVariable("id") int reservationId) throws ReservationNotFoundException {
		Reservation deletedReservation=resService.deleteReservation(reservationId);
		return new ResponseEntity<Reservation>(deletedReservation,HttpStatus.OK);
	}
	
	
	@GetMapping("/reservatons/{id}")
	public  ResponseEntity<Reservation> viewReservation(@PathVariable("id") int reservationId) throws ReservationNotFoundException {
		Reservation viewReservation=resService.viewReservation(reservationId);
		return new ResponseEntity<Reservation>(viewReservation,HttpStatus.CREATED);
	}
	
	
	@GetMapping("/reservatons")
	public ResponseEntity<List<Reservation>> viewAllReservation() throws ReservationNotFoundException{
		List<Reservation> allReservations=resService.viewAllReservation();
		return new ResponseEntity<List<Reservation>>(allReservations,HttpStatus.CREATED);
	}
	
	
	@GetMapping("/allreservatons/{date}")
	public ResponseEntity<List<Reservation>> getAllReservation(@PathVariable("date") LocalDate date) throws ReservationNotFoundException{
		List<Reservation> allReservationsByDate=resService.getAllReservation(date);
		return new ResponseEntity<List<Reservation>>(allReservationsByDate,HttpStatus.CREATED);
	}
}

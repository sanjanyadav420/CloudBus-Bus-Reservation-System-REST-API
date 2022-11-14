package com.bookbus.controllers;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
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

import com.bookbus.dto.ReservationDto;
import com.bookbus.exceptions.BusNotFoundException;
import com.bookbus.exceptions.ReservationNotFoundException;
import com.bookbus.models.Reservation;
import com.bookbus.services.ReservationService;

@RestController
public class ReservationController {
	
	@Autowired
	private ReservationService resService;
	
	
	@PostMapping("/reservatons/{busId}")
	public ResponseEntity<Reservation> addReservation(@PathVariable("busId") Integer busId,@RequestBody ReservationDto reservation) throws BusNotFoundException {
		Reservation savedReservation=resService.addReservation(busId,reservation);
		return new ResponseEntity<Reservation>(savedReservation,HttpStatus.CREATED);
	}
	
	
	@PutMapping("/reservatons/{busId}")
	public ResponseEntity<Reservation> updateReservation(@PathVariable("busId") Integer busId, @RequestBody ReservationDto reservation) throws ReservationNotFoundException, BusNotFoundException {
		Reservation updatedReservation=resService.updateReservation(busId,reservation);
		return new ResponseEntity<Reservation>(updatedReservation,HttpStatus.ACCEPTED);
	}
	
	
	@DeleteMapping("/reservatons/{id}")
	public ResponseEntity<Reservation> deleteReservation(@PathVariable("id") Integer reservationId) throws ReservationNotFoundException {
		Reservation deletedReservation=resService.deleteReservation(reservationId);
		return new ResponseEntity<Reservation>(deletedReservation,HttpStatus.OK);
	}
	
	
	@GetMapping("/reservatons/{id}")
	public  ResponseEntity<Reservation> viewReservation(@PathVariable("id") Integer reservationId) throws ReservationNotFoundException {
		Reservation viewReservation=resService.viewReservation(reservationId);
		return new ResponseEntity<Reservation>(viewReservation,HttpStatus.CREATED);
	}
	
	
	@GetMapping("/reservatons")
	public ResponseEntity<List<Reservation>> viewAllReservation() throws ReservationNotFoundException{
		List<Reservation> allReservations=resService.viewAllReservation();
		return new ResponseEntity<List<Reservation>>(allReservations,HttpStatus.CREATED);
	}
	
	
	@GetMapping("/allreservatons/{date}")
	public ResponseEntity<List<Reservation>> getAllReservation(@PathVariable("date") String reservationDate) throws ReservationNotFoundException{
		DateTimeFormatter dtf=DateTimeFormatter.ofPattern("yyyy-MM-dd");
		LocalDate ld=LocalDate.parse(reservationDate,dtf);
		List<Reservation> allReservationsByDate=resService.getAllReservation(ld);
		return new ResponseEntity<List<Reservation>>(allReservationsByDate,HttpStatus.CREATED);
	}
}

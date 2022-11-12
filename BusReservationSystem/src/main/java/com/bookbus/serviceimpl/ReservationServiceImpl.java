package com.bookbus.serviceimpl;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bookbus.exceptions.ReservationNotFoundException;
import com.bookbus.models.Reservation;
import com.bookbus.repositories.ReservationRepository;
import com.bookbus.services.ReservationService;

@Service
public class ReservationServiceImpl implements ReservationService{

	
	@Autowired
	private ReservationRepository reservationRepo;

	
	@Override
	public Reservation addReservation(Reservation reservation) {
		Reservation savedReservation = reservationRepo.save(reservation);
		return savedReservation;
	}

	
	@Override
	public Reservation updateReservation(Reservation reservation) throws ReservationNotFoundException {
		Optional<Reservation> opt= reservationRepo.findById(reservation.getReservationId());
		if(opt.isPresent()) {
			return reservationRepo.save(reservation);
		}
		else
			throw new ReservationNotFoundException("No reservation found with reservation id "+reservation.getReservationId());
	}

	
	@Override
	public Reservation deleteReservation(int reservationId) throws ReservationNotFoundException {
		Optional<Reservation> opt= reservationRepo.findById(reservationId);
		if(opt.isPresent()) {
			reservationRepo.delete(opt.get());
			return opt.get();
		}
		else
			throw new ReservationNotFoundException("No reservation found with reservation id "+reservationId);
	}

	
	@Override
	public Reservation viewReservation(int reservationId) throws ReservationNotFoundException {
		Optional<Reservation> opt= reservationRepo.findById(reservationId);
		if(opt.isPresent()) {
			return opt.get();
		}
		else
			throw new ReservationNotFoundException("No reservation found with reservation id "+reservationId);
	}

	
	@Override
	public List<Reservation> viewAllReservation() throws ReservationNotFoundException {
		List<Reservation> allReservtion=reservationRepo.getAllReservationDetails();
		if(allReservtion.size()==0) {
			throw new ReservationNotFoundException("No reservation found");
		}
		else
			return allReservtion;
	}

	
	@Override
	public List<Reservation> getAllReservation(LocalDate date) throws ReservationNotFoundException {
		List<Reservation> allReservtion=reservationRepo.findByReservationDate(date);
		if(allReservtion.size()==0) {
			throw new ReservationNotFoundException("No reservation found on "+date);
		}
		else
			return allReservtion;
	}
	
}

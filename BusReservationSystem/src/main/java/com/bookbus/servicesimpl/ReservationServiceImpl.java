package com.bookbus.servicesimpl;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bookbus.dto.ReservationDto;
import com.bookbus.exceptions.BusNotFoundException;
import com.bookbus.exceptions.ReservationNotFoundException;
import com.bookbus.models.Bus;
import com.bookbus.models.Reservation;
import com.bookbus.repositories.BusRepository;
import com.bookbus.repositories.ReservationRepository;
import com.bookbus.services.ReservationService;

@Service
public class ReservationServiceImpl implements ReservationService{

	
	@Autowired
	private ReservationRepository reservationRepo;
	
	@Autowired
	private BusRepository busRepo;
	

	
	@Override
	public Reservation addReservation(Integer busId,ReservationDto reservation) throws BusNotFoundException {
		Reservation finalReservation=new Reservation();
		
		finalReservation.setReservationStatus(reservation.getReservationStatus());
		finalReservation.setReservationType(reservation.getReservationType());
		
		DateTimeFormatter dtf=DateTimeFormatter.ofPattern("dd/MM/yyyy");
		
		LocalDate ld=LocalDate.parse(reservation.getReservationDate(),dtf);
		
		finalReservation.setReservationDate(ld);
		
		finalReservation.setReservationTime(LocalTime.parse(reservation.getReservationTime()));
		finalReservation.setSource(reservation.getSource());
		finalReservation.setDestination(reservation.getDestination());
		
		Optional<Bus> bs= busRepo.findById(busId);
		
		Reservation savedReservation=null;
		if(bs.isPresent()) {
			Bus getBus=bs.get();

			if(getBus.getAvaiableSeats()>0) {
				getBus.setAvaiableSeats(getBus.getAvaiableSeats()-1);
				finalReservation.setBus(getBus);
				savedReservation = reservationRepo.save(finalReservation);
				
			}
			else {
				throw new BusNotFoundException("No seat avaliable in this bus");
			}
			return savedReservation;
		}
		else {
			
			throw new BusNotFoundException("No bus data found with bus id "+reservation.getBus().getBusId());
			
		}
		
		
	}

	
	@Override
	public Reservation updateReservation(Integer busId,ReservationDto reservation) throws ReservationNotFoundException, BusNotFoundException {
		Optional<Reservation> opt= reservationRepo.findById(reservation.getReservationId());
		if(opt.isPresent()) {
			Reservation getreservation=new Reservation();
			
			getreservation.setReservationId(reservation.getReservationId());
			getreservation.setReservationStatus(reservation.getReservationStatus());
			getreservation.setReservationType(reservation.getReservationType());
			
			DateTimeFormatter dtf=DateTimeFormatter.ofPattern("dd/MM/yyyy");
			
			LocalDate ld=LocalDate.parse(reservation.getReservationDate(),dtf);
			
			getreservation.setReservationDate(ld);
			
			getreservation.setReservationTime(LocalTime.parse(reservation.getReservationTime()));
			getreservation.setSource(reservation.getSource());
			getreservation.setDestination(reservation.getDestination());
			
			Optional<Bus> bs= busRepo.findById(busId);
			if(bs.isPresent()) {
				
				Bus getBus=bs.get();
				getreservation.setBus(getBus);
				
			}
			else {
				
				throw new BusNotFoundException("No bus data found with bus id "+reservation.getBus().getBusId());
				
			}
			System.out.println("reservation object is "+getreservation.toString());
			return reservationRepo.save(getreservation);
		}
		else
			throw new ReservationNotFoundException("No reservation found with reservation id "+reservation.getReservationId());
	}

	
	@Override
	public Reservation deleteReservation(Integer reservationId) throws ReservationNotFoundException {
		Optional<Reservation> opt= reservationRepo.findById(reservationId);
		if(opt.isPresent()) {
			reservationRepo.delete(opt.get());
			return opt.get();
		}
		else
			throw new ReservationNotFoundException("No reservation found with reservation id "+reservationId);
	}

	
	@Override
	public Reservation viewReservation(Integer reservationId) throws ReservationNotFoundException {
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
		List<Reservation> allReservtion=reservationRepo.getAllReservationDetails(date);
		if(allReservtion.size()==0) {
			throw new ReservationNotFoundException("No reservation found on "+date);
		}
		else
			return allReservtion;
	}
	
}

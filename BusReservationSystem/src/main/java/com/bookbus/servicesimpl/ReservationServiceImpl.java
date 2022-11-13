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
import com.bookbus.exceptions.LogException;
import com.bookbus.exceptions.ReservationNotFoundException;
import com.bookbus.exceptions.UserException;
import com.bookbus.models.Bus;
import com.bookbus.models.CurrentAdminSession;
import com.bookbus.models.CurrentUserSession;
import com.bookbus.models.Reservation;
import com.bookbus.models.User;
import com.bookbus.repositories.AdminLogRepo;
import com.bookbus.repositories.BusRepository;
import com.bookbus.repositories.ReservationRepository;
import com.bookbus.repositories.UserLogRepo;
import com.bookbus.repositories.UserRepo;
import com.bookbus.services.ReservationService;

@Service
public class ReservationServiceImpl implements ReservationService{

	
	@Autowired
	private ReservationRepository reservationRepo;
	
	@Autowired
	private BusRepository busRepo;
	
	@Autowired
	private UserRepo uRepo;
	
	@Autowired
	private UserLogRepo ulRepo;
	
	@Autowired
	private AdminLogRepo alRepo;
	
	@Override
	public Reservation addReservation(Integer userId, Integer busId,ReservationDto reservation) throws BusNotFoundException, LogException {
		
		Optional<CurrentUserSession> cusess = ulRepo.findById(userId);
		
		if(cusess.isPresent())
		{
			Reservation finalReservation=new Reservation();
			
			finalReservation.setReservationStatus(reservation.getReservationStatus());
			finalReservation.setReservationType(reservation.getReservationType());
			
			DateTimeFormatter dtf=DateTimeFormatter.ofPattern("dd/MM/yyyy");
			
			LocalDate ld=LocalDate.parse(reservation.getReservationDate(),dtf);
			
			finalReservation.setReservationDate(ld);
			
			finalReservation.setReservationTime(LocalTime.parse(reservation.getReservationTime()));
			
			
			Optional<Bus> bs= busRepo.findById(busId);
			
			Reservation savedReservation=null;
			if(bs.isPresent()) {
				Bus getBus=bs.get();
				
				if((getBus.getRouteFrom()).equals(reservation.getSource()) && (getBus.getRouteTo()).equals(reservation.getDestination())) {
					finalReservation.setSource(reservation.getSource());
					finalReservation.setDestination(reservation.getDestination());
				}
				else {
					throw new BusNotFoundException("Bus and user source or destination is not match");
				}
				
				if(getBus.getAvaiableSeats()>0) {
					getBus.setAvaiableSeats(getBus.getAvaiableSeats()-1);
					finalReservation.setBus(getBus);
					savedReservation = reservationRepo.save(finalReservation);
					
				}
				else {
					throw new BusNotFoundException("No seat avaliable in this bus");
				}
				
				Optional<User> useropt = uRepo.findById(userId);
				
				User user = useropt.get();
				
				user.setReservation(savedReservation);
				
				uRepo.save(user);
				
				return savedReservation;
			}
			else
				throw new BusNotFoundException("No bus data found with bus id "+reservation.getBus().getBusId());
				
			
		}
		else
			throw new LogException("Your userId is incorrect or you are not logged In.");
		
		
	}

	
	@Override
	public Reservation updateReservation(Integer userId,Integer busId,ReservationDto reservation) throws ReservationNotFoundException, BusNotFoundException, LogException {
		
		Optional<CurrentUserSession> cusess = ulRepo.findById(userId);
		
		if(cusess.isPresent())
		{
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
		else
			throw new LogException("Your userId is incorrect or you are not logged In.");
	}

	
	@Override
	public Reservation deleteReservation(Integer userId,Integer reservationId) throws ReservationNotFoundException, LogException {
		
		Optional<CurrentUserSession> cusess = ulRepo.findById(userId);
		
		if(cusess.isPresent())
		{
			Optional<Reservation> opt= reservationRepo.findById(reservationId);
			if(opt.isPresent()) {
				Reservation resData=opt.get();
				reservationRepo.delete(resData);
				Bus bs=resData.getBus();
				bs.setAvaiableSeats(bs.getAvaiableSeats()+1);
				return opt.get();
			}
			else
				throw new ReservationNotFoundException("No reservation found with reservation id "+reservationId);
		}
		else
			throw new LogException("Your userId is incorrect or you are not logged In.");
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
	public List<Reservation> viewAllReservation(Integer adminId) throws ReservationNotFoundException, LogException {
		
		Optional<CurrentAdminSession> casess = alRepo.findById(adminId);
		
		if(casess.isPresent())
		{
			List<Reservation> allReservtion=reservationRepo.getAllReservationDetails();
			if(allReservtion.size()==0) {
				throw new ReservationNotFoundException("No reservation found");
			}
			else
				return allReservtion;
		}
		else
			throw new LogException("Admins can only see list of users If you are please logIn first.");
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

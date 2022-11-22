package com.bookbus.servicesimpl;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bookbus.dto.ReservationDto;
import com.bookbus.exceptions.BusNotFoundException;
import com.bookbus.exceptions.LogException;
import com.bookbus.exceptions.ReservationNotFoundException;
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
	public Reservation addReservation(Integer userId, Integer busId,ReservationDto reservation) throws BusNotFoundException, LogException,Exception{
		
		Optional<CurrentUserSession> cusess = ulRepo.findById(userId);
		
		if(cusess.isPresent())
		{
			
			if(uRepo.findById(userId).get().getReservation()!=null) {
				throw new ReservationNotFoundException("One reservation is already done");
			}
			
			Reservation finalReservation=new Reservation();
			
			finalReservation.setReservationStatus(reservation.getReservationStatus());
			finalReservation.setReservationType(reservation.getReservationType());
			
			DateTimeFormatter dtf=DateTimeFormatter.ofPattern("dd/MM/yyyy");
			
			LocalDate ld=LocalDate.parse(reservation.getReservationDate(),dtf);
			
			if(ld.isAfter(LocalDate.now())){
				finalReservation.setReservationDate(ld);
			}
			else {
				throw new Exception("Date must be in future");
			}
			
			
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
					throw new BusNotFoundException("Bus source or destination is not match");
				}
				if(!(getBus.getBusType().equals(reservation.getReservationType()))) {
					throw new BusNotFoundException("Bus type is not match");
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
				throw new BusNotFoundException("No bus data found with bus id "+busId);
		}
		else
			throw new LogException("No user found login required");
		
		
	}

	
	@Override
	public Reservation updateReservation(Integer userId,Integer busId,ReservationDto reservation) throws ReservationNotFoundException, BusNotFoundException, LogException,Exception {
		
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
				
				
				if(ld.isAfter(LocalDate.now())){
					getreservation.setReservationDate(ld);
				}
				else {
					throw new Exception("Date must be in future");
				}
				
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
				return reservationRepo.save(getreservation);
			}
			else
				throw new ReservationNotFoundException("No reservation found with reservation id "+reservation.getReservationId());
		}
		else
			throw new LogException("No user found login required");
	}

	
	@Override
	public Reservation deleteReservation(Integer userId,Integer reservationId) throws ReservationNotFoundException, LogException {
		
		Optional<CurrentUserSession> cusess = ulRepo.findById(userId);
		
		if(cusess.isPresent())
		{
			Optional<Reservation> opt= reservationRepo.findById(reservationId);
			if(opt.isPresent()) {
				Optional<User> loginUser= uRepo.findById(userId);
				User currentUser= loginUser.get();
				currentUser.setReservation(null);
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
			throw new LogException("No user found login required");
	}

	
	@Override
	public Reservation viewReservation(Integer reservationId) throws ReservationNotFoundException {

		Optional<Reservation> res= reservationRepo.findById(reservationId);
	
		if(res.isPresent()) {
			return res.get();
		}
		else {
			throw new ReservationNotFoundException("No reservation found with reservation id "+reservationId);
		}
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
			throw new LogException("Admin can only see list of users reservation");
	}

	
	@Override
	public List<Reservation> getAllReservation(Integer UserId,LocalDate date) throws ReservationNotFoundException{
//		List<Reservation> allReservtion=reservationRepo.getReservationByDate(UserId, date);
//		if(allReservtion.size()==0) {
//			throw new ReservationNotFoundException("No reservation found on "+date);
//		}
//		else
//			return allReservtion;
		
		Optional<User> opt= uRepo.findById(UserId);
		if(opt.isPresent()) {
			User u1= opt.get();
			Reservation rse= u1.getReservation();
			if(rse==null) {
				throw new ReservationNotFoundException("No reservation found");
			}
			else {
				List<Reservation> resvationList=new ArrayList<>();
				if(rse.getReservationDate().compareTo(date)==0) {
					resvationList.add(rse);
					return resvationList;
				}
				else {
					throw new ReservationNotFoundException("No reservation found with date "+date);
				}
			}
			
		}
		else
			throw new ReservationNotFoundException("No user name and date mismatch");
		
	}
	
}

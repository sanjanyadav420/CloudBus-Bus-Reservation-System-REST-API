
package com.bookbus.servicesimpl;

import java.time.LocalTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bookbus.dto.BusDto;
import com.bookbus.exceptions.BusNotFoundException;
import com.bookbus.exceptions.LogException;
import com.bookbus.models.Bus;
import com.bookbus.models.CurrentAdminSession;
import com.bookbus.repositories.AdminLogRepo;
import com.bookbus.repositories.BusRepository;
import com.bookbus.services.BusService;

@Service
public class BusServiceImpl implements BusService{
	
	
	@Autowired
	private BusRepository busRepo;
	
	@Autowired
	private AdminLogRepo alRepo;

	
	@Override
	public Bus addBus(Integer adminId, BusDto bus) throws LogException, BusNotFoundException{
		
		if(bus.getAvaiableSeats()>bus.getSeats()) {
			throw new BusNotFoundException("Avaiable seat is not greater then fix set of the bus");
		}
		
		Optional<CurrentAdminSession> casess = alRepo.findById(adminId);
		
		if(casess.isPresent())
		{
			Bus bs=new Bus();
			
			bs.setBusName(bus.getBusName());
			bs.setDriverName(bus.getDriverName());
			bs.setBusType(bus.getBusType());
			bs.setRouteFrom(bus.getRouteFrom());
			bs.setRouteTo(bus.getRouteTo());
			bs.setArrivalTime(LocalTime.parse(bus.getArrivalTime()));
			bs.setDepartureTime(LocalTime.parse(bus.getDepartureTime()));
			bs.setSeats(bus.getSeats());
			bs.setAvaiableSeats(bus.getAvaiableSeats());
			Bus savedBus=busRepo.save(bs);
			
			return savedBus;
		}
		else
			throw new LogException("Admin login required to add bus");
		
		
	}

	
	@Override
	public Bus updateBus(Integer adminId, BusDto bus) throws BusNotFoundException, LogException {
		
		if(bus.getAvaiableSeats()>bus.getSeats()) {
			throw new BusNotFoundException("Avaiable seat is not greater then fix set of the bus");
		}
		
		Optional<CurrentAdminSession> casess = alRepo.findById(adminId);
		
		if(casess.isPresent())
		{
			Optional<Bus> opt= busRepo.findById(bus.getBusId());
			
			if(opt.isPresent()) {
				Bus bs=new Bus();
				bs.setBusId(bus.getBusId());
				bs.setBusName(bus.getBusName());
				bs.setDriverName(bus.getDriverName());
				bs.setBusType(bus.getBusType());
				bs.setRouteFrom(bus.getRouteFrom());
				bs.setRouteTo(bus.getRouteTo());
				bs.setArrivalTime(LocalTime.parse(bus.getArrivalTime()));
				bs.setDepartureTime(LocalTime.parse(bus.getDepartureTime()));
				bs.setSeats(bus.getSeats());
				bs.setAvaiableSeats(bus.getAvaiableSeats());
				return busRepo.save(bs);
			}
			
			else
				throw new BusNotFoundException("No bus found with bus id "+bus.getBusId());
		}
		else
			throw new LogException("Admin login required to update bus details");
		
	}

	
	@Override
	public Bus deleteBus(Integer adminId, Integer busId) throws BusNotFoundException, LogException {
		
		Optional<CurrentAdminSession> casess = alRepo.findById(adminId);
		
		if(casess.isPresent())
		{
			Optional<Bus> opt=busRepo.findById(busId);
			if(opt.isPresent()) {
				busRepo.delete(opt.get());
				return opt.get();
			}
			else
				throw new BusNotFoundException("No bus found with bus id "+busId);
		}
		else
			throw new LogException("Admin login required to delete bus from database");
		
	}

	
	@Override
	public Bus viewBus(Integer busId) throws BusNotFoundException {
		Optional<Bus> opt=busRepo.findById(busId);
		if(opt.isPresent()) {
			return opt.get();
		}
		else
			throw new BusNotFoundException("No bus found with bus id "+busId);
	}

	
	@Override
	public List<Bus> viewBusByType(String busType) throws BusNotFoundException {
		List<Bus> buses=busRepo.findByBusType(busType);
		if(buses.size()==0) {
			throw new BusNotFoundException("No bus found with type "+busType);
		}
		else
			return buses;
	}

	
	@Override
	public List<Bus> viewAllBus() throws BusNotFoundException {
		List<Bus> buses=busRepo.viewAllBus();
		if(buses.size()==0) {
			throw new BusNotFoundException("No bus found");
		}
		else
			return buses;
	}

}

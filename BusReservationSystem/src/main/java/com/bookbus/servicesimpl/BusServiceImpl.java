package com.bookbus.servicesimpl;

import java.time.LocalTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bookbus.dto.BusDto;
import com.bookbus.exceptions.BusNotFoundException;
import com.bookbus.models.Bus;
import com.bookbus.repositories.BusRepository;
import com.bookbus.services.BusService;

@Service
public class BusServiceImpl implements BusService{
	
	
	@Autowired
	private BusRepository busRepo;

	
	@Override
	public Bus addBus(BusDto bus){
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

	
	@Override
	public Bus updateBus(BusDto bus) throws BusNotFoundException {
		Optional<Bus> opt= busRepo.findById(bus.getBusId());
		
		if(opt.isPresent()) {
//			opt.get().setBusName(bus.getBusName());
//			opt.get().setDriverName(bus.getDriverName());
//			opt.get().setBusType(bus.getBusType());
//			opt.get().setRouteFrom(bus.getRouteFrom());
//			opt.get().setRouteTo(bus.getRouteTo());
//			opt.get().setArrivalTime(LocalTime.parse(bus.getArrivalTime()));
//			opt.get().setDepartureTime(LocalTime.parse(bus.getDepartureTime()));
//			opt.get().setSeats(bus.getSeats());
//			opt.get().setAvaiableSeats(bus.getAvaiableSeats());
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

	
	@Override
	public Bus deleteBus(Integer busId) throws BusNotFoundException {
		Optional<Bus> opt=busRepo.findById(busId);
		if(opt.isPresent()) {
			busRepo.delete(opt.get());
			return opt.get();
		}
		else
			throw new BusNotFoundException("No bus found with bus id "+busId);
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

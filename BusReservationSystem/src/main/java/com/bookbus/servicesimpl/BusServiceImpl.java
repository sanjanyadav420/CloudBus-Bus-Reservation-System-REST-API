package com.bookbus.servicesimpl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bookbus.exceptions.BusNotFoundException;
import com.bookbus.models.Bus;
import com.bookbus.repositories.BusRepository;
import com.bookbus.services.BusService;

@Service
public class BusServiceImpl implements BusService{
	
	
	@Autowired
	private BusRepository busRepo;

	
	@Override
	public Bus addBus(Bus bus) {
		
		return busRepo.save(bus);
	}

	
	@Override
	public Bus updateBus(Bus bus) throws BusNotFoundException {
		Optional<Bus> opt= busRepo.findById(bus.getBusId());
		if(opt.isPresent()) {
			return busRepo.save(bus);
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
			throw new BusNotFoundException("No bus found");
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

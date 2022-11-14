package com.bookbus.services;

import java.util.List;

import com.bookbus.dto.BusDto;
import com.bookbus.exceptions.BusNotFoundException;
import com.bookbus.models.Bus;

public interface BusService {
	
	public Bus addBus(BusDto bus);
	
	public Bus updateBus(BusDto bus) throws BusNotFoundException;
	
	public Bus deleteBus(Integer busId) throws BusNotFoundException;
	
	public Bus viewBus(Integer busId) throws BusNotFoundException;
	
	public List<Bus> viewBusByType(String busType) throws BusNotFoundException;
	
	public List<Bus> viewAllBus() throws BusNotFoundException;
}

package com.bookbus.services;

import java.util.List;

import com.bookbus.exceptions.BusNotFoundException;
import com.bookbus.models.Bus;

public interface BusService {
	
	public Bus addBus(Bus bus);
	
	public Bus updateBus(Bus bus) throws BusNotFoundException;
	
	public Bus deleteBus(int busId) throws BusNotFoundException;
	
	public Bus viewBus(int busId) throws BusNotFoundException;
	
	public List<Bus> viewBusByType(String busType) throws BusNotFoundException;
	
	public List<Bus> viewAllBus() throws BusNotFoundException;
}

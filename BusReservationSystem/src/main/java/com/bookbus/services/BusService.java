package com.bookbus.services;

import java.util.List;

import com.bookbus.dto.BusDto;
import com.bookbus.exceptions.BusNotFoundException;
import com.bookbus.exceptions.LogException;
import com.bookbus.models.Bus;

public interface BusService {
	
	public Bus addBus(Integer adminId, BusDto bus) throws LogException, BusNotFoundException;
	
	public Bus updateBus(Integer adminId, BusDto bus) throws BusNotFoundException, LogException;
	
	public Bus deleteBus(Integer adminId, Integer busId) throws BusNotFoundException, LogException;
	
	public Bus viewBus(Integer busId) throws BusNotFoundException;
	
	public List<Bus> viewBusByType(String busType) throws BusNotFoundException;
	
	public List<Bus> viewAllBus() throws BusNotFoundException;
}

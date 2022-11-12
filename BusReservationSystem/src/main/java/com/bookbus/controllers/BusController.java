package com.bookbus.controllers;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.bookbus.dto.BusDto;
import com.bookbus.exceptions.BusNotFoundException;
import com.bookbus.models.Bus;
import com.bookbus.services.BusService;

@RestController
public class BusController {
	
	@Autowired
	private BusService busService;

	
	@PostMapping("/buses")
	public ResponseEntity<Bus> addBus(@RequestBody BusDto bus){
		Bus savedBus=busService.addBus(bus);
		return new ResponseEntity<Bus>(savedBus,HttpStatus.CREATED);
	}
	
	
	@PutMapping("/buses")
	public ResponseEntity<Bus> updateBus(@RequestBody BusDto bus) throws BusNotFoundException {
		Bus updatedBus=busService.updateBus(bus);
		return new ResponseEntity<Bus>(updatedBus,HttpStatus.ACCEPTED);
	}
	
	
	@DeleteMapping("/buses/{id}")
	public ResponseEntity<Bus> deleteBus(@PathVariable("id") int busId) throws BusNotFoundException{
		Bus deletedBus=busService.deleteBus(busId);
		return new ResponseEntity<Bus>(deletedBus,HttpStatus.ACCEPTED);
	}
	
	
	@GetMapping("/buses/{id}")
	public ResponseEntity<Bus> viewBus(@PathVariable("id") int busId) throws BusNotFoundException{
		Bus viewBus=busService.viewBus(busId);
		return new ResponseEntity<Bus>(viewBus,HttpStatus.CREATED);
	}
	
	
	@GetMapping("/busesbytype/{type}")
	public ResponseEntity<List<Bus>> viewBusByType(@PathVariable("type") String busType) throws BusNotFoundException{
		List<Bus> viewAllBusByType=busService.viewBusByType(busType);
		return new ResponseEntity<List<Bus>>(viewAllBusByType,HttpStatus.CREATED);
	}
	
	
	@GetMapping("/buses")
	public ResponseEntity<List<Bus>> viewAllBus() throws BusNotFoundException{
		List<Bus> viewAllBus=busService.viewAllBus();
		return new ResponseEntity<List<Bus>>(viewAllBus,HttpStatus.CREATED);
	}
}

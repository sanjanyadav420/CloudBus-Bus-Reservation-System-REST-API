package com.bookbus.controllers;

import java.util.List;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.bookbus.dto.BusDto;
import com.bookbus.exceptions.BusNotFoundException;
import com.bookbus.exceptions.LogException;
import com.bookbus.models.Bus;
import com.bookbus.services.BusService;

@RestController
@RequestMapping("/cloudbus")
public class BusController {
	
	@Autowired
	private BusService busService;

	
	@PostMapping("/buses/{adminId}")
	public ResponseEntity<Bus> addBus(@Valid @PathVariable("adminId") Integer adminId,@Valid @RequestBody BusDto bus) throws LogException, BusNotFoundException{
		Bus savedBus=busService.addBus(adminId,bus);
		return new ResponseEntity<Bus>(savedBus,HttpStatus.CREATED);
	}
	
	
	@PutMapping("/buses{adminId}")
	public ResponseEntity<Bus> updateBus(@PathVariable("adminId") Integer adminId,@Valid @RequestBody BusDto bus) throws BusNotFoundException, LogException {
		Bus updatedBus=busService.updateBus(adminId,bus);
		return new ResponseEntity<Bus>(updatedBus,HttpStatus.ACCEPTED);
	}
	
	
	@DeleteMapping("/buses/{adminId}/{busId}")
	public ResponseEntity<Bus> deleteBus(@PathVariable("adminId") Integer adminId, @PathVariable("busId") int busId) throws BusNotFoundException, LogException{
		Bus deletedBus=busService.deleteBus(adminId,busId);
		return new ResponseEntity<Bus>(deletedBus,HttpStatus.ACCEPTED);
	}
	
	
	@GetMapping("/buses/{busId}")
	public ResponseEntity<Bus> viewBus(@PathVariable("busId") Integer busId) throws BusNotFoundException{
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

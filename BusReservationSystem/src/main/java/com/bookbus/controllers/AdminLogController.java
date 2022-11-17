package com.bookbus.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bookbus.dto.AdminLoginDTO;
import com.bookbus.exceptions.LogException;
import com.bookbus.services.AdminLogService;
import io.swagger.v3.oas.annotations.parameters.RequestBody;

@RestController
@RequestMapping("/cloudbus")
public class AdminLogController {
	
	@Autowired
	private AdminLogService aLogServ;
	
	@PostMapping("/adminlogin")
	public ResponseEntity<String> adminLogIn(@RequestBody AdminLoginDTO dto) throws LogException{
		
		String result = aLogServ.adminLogIn(dto);
		return new ResponseEntity<String>(result, HttpStatus.ACCEPTED);
	}
	
	@GetMapping("/adminlogout/{adminId}")
	public ResponseEntity<String> adminLogOut(@PathVariable("adminId") Integer adminId) throws LogException {
		
		String result = aLogServ.adminLogOut(adminId);
		return new ResponseEntity<String>(result, HttpStatus.OK);
	}
}

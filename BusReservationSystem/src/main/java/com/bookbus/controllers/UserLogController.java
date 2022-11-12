package com.bookbus.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.bookbus.exceptions.LogException;
import com.bookbus.models.UserLoginDTO;
import com.bookbus.services.UserLogService;
import io.swagger.v3.oas.annotations.parameters.RequestBody;

@RestController
@RequestMapping("/cloudbus")
public class UserLogController {
	
	@Autowired
	private UserLogService uLogServ;
	
	@PostMapping("/userlogin")
	public ResponseEntity<String> userLogIn(@RequestBody UserLoginDTO dto) throws LogException{
		
		String result = uLogServ.userLogIn(dto);
		return new ResponseEntity<String>(result, HttpStatus.ACCEPTED);
	}
	
	@GetMapping("/userlogout/{userId}")
	public ResponseEntity<String> adminLogOut(@PathVariable("userId") Integer userId) throws LogException {
		
		String result = uLogServ.userLogOut(userId);
		return new ResponseEntity<String>(result, HttpStatus.OK);
	}
	
}

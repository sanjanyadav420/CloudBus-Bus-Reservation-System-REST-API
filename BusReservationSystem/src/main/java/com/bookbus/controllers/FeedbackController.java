package com.bookbus.controllers;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import com.bookbus.exceptions.FeedbackException;
import com.bookbus.exceptions.LogException;
import com.bookbus.models.Feedback;
import com.bookbus.services.FeedbackService;

@RestController("/cloudbus")
public class FeedbackController {
	
	@Autowired
	private FeedbackService feedbackservice;
	
	@PostMapping("/feedback/{userid}")
	public ResponseEntity<Feedback> addFeedBackHandler( @PathVariable("userid") Integer userId ,@Valid @RequestBody Feedback feedback) throws LogException {
		
		
		Feedback addFeedback= feedbackservice.addFeedBack(feedback, userId);
		
		return new ResponseEntity<Feedback>(addFeedback, HttpStatus.CREATED);
		
	}
	
	@PutMapping("/feedback/{userid}")
	public ResponseEntity<Feedback> updateFeedBackHandler(@PathVariable("userid") Integer userId , @Valid @RequestBody Feedback feedback) throws FeedbackException, LogException {
		
		Feedback updateFeedback= feedbackservice.updateFeedBack(feedback, userId);
		
		return new ResponseEntity<Feedback>(updateFeedback, HttpStatus.OK);
	}
	
	@PutMapping("/feedback/{id}")
	public ResponseEntity<Feedback> viewFeedBackHandler(@PathVariable("id") Integer feedbackid) throws FeedbackException{
		
		Feedback viewFeedback= feedbackservice.viewFeedBack(feedbackid);
		
		return new ResponseEntity<Feedback>(viewFeedback, HttpStatus.OK);
		
	}
	
	@GetMapping("/feedback")
	public ResponseEntity<List<Feedback>> viewAllFeedBackHandler() throws FeedbackException{
		
		List<Feedback> listOfFeedback= feedbackservice.viewAllFeedBack();
		
		return new ResponseEntity<List<Feedback>>(listOfFeedback, HttpStatus.OK);
	}

}

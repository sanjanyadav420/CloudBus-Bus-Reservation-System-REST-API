package com.bookbus.services;

import java.util.List;

import com.bookbus.exceptions.FeedbackException;
import com.bookbus.models.Feedback;


public interface FeedbackService {

	public Feedback addFeedBack(Feedback feedback);
	
	public Feedback updateFeedBack (Feedback feedback) throws FeedbackException;
	
	public Feedback viewFeedBack(int feedbackid) throws FeedbackException;
	
	public List<Feedback> viewAllFeedBack() throws FeedbackException;
}

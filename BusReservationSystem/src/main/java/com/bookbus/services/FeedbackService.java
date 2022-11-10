package com.bookbus.services;

import java.util.List;

import com.bookbus.models.Feedback;


public interface FeedbackService {

	public Feedback addFeedBack(Feedback feedback);
	
	public Feedback updateFeedBack (Feedback feedback);
	
	public Feedback viewFeedBack(int feedbackid);
	
	public List<Feedback> viewAllFeedBack();
}

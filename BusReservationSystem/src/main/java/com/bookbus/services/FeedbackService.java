package com.bookbus.services;

import java.util.List;

import com.bookbus.exceptions.FeedbackException;
import com.bookbus.exceptions.LogException;
import com.bookbus.models.Feedback;


public interface FeedbackService {


//	public Feedback addFeedBack(Feedback feedback, Integer userId) throws LogException;

	public Feedback addFeedBack(Feedback feedback, Integer Userid) throws LogException;

	
	public Feedback updateFeedBack (Feedback feedback, Integer Userid) throws FeedbackException, LogException;
	
	public Feedback viewFeedBack(Integer feedbackid) throws FeedbackException;
	
	public List<Feedback> viewAllFeedBack() throws FeedbackException;
}

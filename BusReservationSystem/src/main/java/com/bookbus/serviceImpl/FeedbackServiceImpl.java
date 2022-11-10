package com.bookbus.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bookbus.models.Feedback;
import com.bookbus.repositories.FeedbackRepo;
import com.bookbus.services.FeedbackService;

@Service
public class FeedbackServiceImpl implements FeedbackService {
	
	@Autowired
	private FeedbackRepo feedbackReop;

	@Override
	public Feedback addFeedBack(Feedback feedback) {
		
		return feedbackReop.save(feedback);
	}

	@Override
	public Feedback updateFeedBack(Feedback feedback) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Feedback viewFeedBack(int feedbackid) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Feedback> viewAllFeedBack() {
		// TODO Auto-generated method stub
		return null;
	}

}

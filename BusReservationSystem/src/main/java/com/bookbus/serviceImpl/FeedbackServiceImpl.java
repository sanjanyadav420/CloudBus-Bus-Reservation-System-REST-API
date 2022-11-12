package com.bookbus.serviceImpl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bookbus.exceptions.FeedbackException;
import com.bookbus.exceptions.LogException;
import com.bookbus.models.CurrentUserSession;
import com.bookbus.models.Feedback;
import com.bookbus.repositories.FeedbackRepo;
import com.bookbus.repositories.UserLogRepo;
import com.bookbus.repositories.UserRepo;
import com.bookbus.services.FeedbackService;

@Service
public class FeedbackServiceImpl implements FeedbackService {

	@Autowired
	private FeedbackRepo feedbackRepo;
	
	@Autowired
	private UserLogRepo userlogrepo;
	
	@Autowired
	private UserRepo userRepo;

	@Override
	public Feedback addFeedBack(Feedback feedback, Integer UserId) throws LogException {

		Optional<CurrentUserSession> cus= userlogrepo.findById(UserId);
		
		if(cus.isPresent()) {
			return feedbackRepo.save(feedback);
		}else {
			throw new LogException("Please Login First/ Inavlid User Id "+UserId);
		}
		
		
	}

	@Override
	public Feedback updateFeedBack(Feedback feedback, Integer UserId) throws FeedbackException, LogException {
		
		Optional<CurrentUserSession> cus= userlogrepo.findById(UserId);
		
		if(cus.isPresent()) {
			Optional<Feedback> updateFeedback = feedbackRepo.findById(feedback.getFeedBackId());
			
			
			if (updateFeedback.isEmpty()) {

				throw new FeedbackException("Feedback not found");
			}
			else{
				Feedback updatedFeedBack = feedbackRepo.save(updateFeedback.get());
				
				return updatedFeedBack;
			}

			
		}else {
			throw new LogException("Please Login First/ Inavlid User Id "+UserId);
		}

		

	}

	@Override
	public Feedback viewFeedBack(Integer feedbackid) throws FeedbackException {
		return feedbackRepo.findById(feedbackid)
				.orElseThrow(() -> new FeedbackException("FeedBack with Id " + feedbackid + " not found"));
	}

	@Override
	public List<Feedback> viewAllFeedBack() throws FeedbackException {
		List<Feedback> feedbackList = feedbackRepo.findAll();

		if (feedbackList.size() == 0) {

			throw new FeedbackException("The List is Empty");

		}
		return feedbackList;

	}

}

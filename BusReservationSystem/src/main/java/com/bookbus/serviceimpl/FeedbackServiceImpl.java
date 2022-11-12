package com.bookbus.serviceimpl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bookbus.exceptions.FeedbackException;
import com.bookbus.exceptions.LogException;
import com.bookbus.models.CurrentUserSession;
import com.bookbus.models.Feedback;
import com.bookbus.models.User;
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
	private UserRepo userrepo;
	

	@Override
	public Feedback addFeedBack(Feedback feedback, Integer userId ) throws LogException {
		
		

		Optional<CurrentUserSession> cus= userlogrepo.findById(userId);
		
		if(cus.isPresent()) {
			Optional<User> userobj= userrepo.findById(userId);
			feedback.setUsers(userobj.get());
			return feedbackRepo.save(feedback);
			
		}else {
			throw new LogException("User is not Login, Please Login First.. Thankyou! : "+userId);
		}
		
		
	}

	@Override
	public Feedback updateFeedBack(Feedback feedback) throws FeedbackException {
		Optional<Feedback> updateFeedback = feedbackRepo.findById(feedback.getFeedBackId());

		if (updateFeedback.isEmpty()) {

			throw new FeedbackException("Feedback not found");
		}
		Feedback updatedFeedBack = feedbackRepo.save(updateFeedback.get());

		return updatedFeedBack;

	}

	@Override
	public Feedback viewFeedBack(int feedbackid) throws FeedbackException {
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

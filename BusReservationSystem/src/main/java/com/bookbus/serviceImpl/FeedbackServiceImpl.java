package com.bookbus.serviceImpl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bookbus.exceptions.FeedbackException;
import com.bookbus.models.Feedback;
import com.bookbus.repositories.FeedbackRepo;
import com.bookbus.services.FeedbackService;

@Service
public class FeedbackServiceImpl implements FeedbackService {

	@Autowired
	private FeedbackRepo feedbackRepo;

	@Override
	public Feedback addFeedBack(Feedback feedback) {

		return feedbackRepo.save(feedback);
	}

	@Override
	public Feedback updateFeedBack(Feedback feedback) throws FeedbackException {
		Optional<Feedback> updateFeedback = feedbackRepo.findById(feedback.getFeedBackId());

		if (updateFeedback.isEmpty()) {

			throw new FeedbackException("Feedback not found");
		}
		Feedback updatedFeedBack = feedbackRepo.save(updateFeedback);

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

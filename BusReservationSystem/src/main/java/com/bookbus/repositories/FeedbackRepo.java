package com.bookbus.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bookbus.models.Feedback;

@Repository
public interface FeedbackRepo extends JpaRepository<Feedback, Integer> {

	Feedback save(Optional<Feedback> updateFeedback);

	

}

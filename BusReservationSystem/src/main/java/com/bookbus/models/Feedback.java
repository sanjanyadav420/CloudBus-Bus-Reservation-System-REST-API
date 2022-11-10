package com.bookbus.models;

import java.time.LocalDate;

import javax.annotation.Generated;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Feedback {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer feedBackId;
	private Integer driverRating;
	private Integer serviceRating;
	private Integer overallRating;
	private String comments;
	private LocalDate feedbackdate;
	
	private User users;
	private Bus bus;

}

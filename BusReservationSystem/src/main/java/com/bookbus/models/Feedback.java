package com.bookbus.models;

import java.time.LocalDate;

import javax.annotation.Generated;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnore;

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
	
//	@Min(0)
//	@Max(value= 5, message = "Rate Between 0 to 5")
	private Integer driverRating;
	
//	@Min(0)
//	@Max(value= 5, message = "Rate Between 0 to 5")
	private Integer serviceRating;
	
//	@Min(0)
//	@Max(value= 5, message = "Rate Between 0 to 5")
	private Integer overallRating;
	
//	@Size(min = 5, max = 25, message = "Minimun 5 letters")
	private String comments;
	
	private LocalDate feedbackdate;
	
	@JsonIgnore
	@OneToOne(cascade =CascadeType.ALL)
	private User users;
	
	@JsonIgnore
	@OneToOne(cascade =CascadeType.ALL)
	private Bus bus;

}

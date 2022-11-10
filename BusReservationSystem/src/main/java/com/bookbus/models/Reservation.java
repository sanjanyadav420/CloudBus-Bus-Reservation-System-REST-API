package com.bookbus.models;

import java.time.LocalDate;
import java.time.LocalTime;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.validation.constraints.Future;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Reservation {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int reservationId;
	
	@NotNull
	@NotBlank
	@NotEmpty
	private String reservationStatus;
	
	@NotNull
	@NotBlank
	@NotEmpty
	private String reservationType;
	
	@Future
	private LocalDate reservationDate;
	
	@Future
	private LocalTime reservationTime;
	
	@NotNull
	@NotBlank
	@NotEmpty
	private String source;
	
	@NotNull
	@NotBlank
	@NotEmpty
	private String destination;
	
	@NotNull
	@OneToOne(cascade = CascadeType.ALL)
	Bus bus;
}

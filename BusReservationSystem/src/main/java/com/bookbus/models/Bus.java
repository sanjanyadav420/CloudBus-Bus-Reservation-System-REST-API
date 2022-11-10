package com.bookbus.models;

import java.time.LocalTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Future;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Bus {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int busId;
	
	@NotNull
	@NotBlank
	@NotEmpty
	@Size(min=3)
	private String busName;
	
	@NotNull
	@NotBlank
	@NotEmpty
	private String driverName;
	
	@NotNull
	@NotBlank
	@NotEmpty
	private String busType;
	
	@NotNull
	@NotBlank
	@NotEmpty
	private String routeFrom;
	
	@NotNull
	@NotBlank
	@NotEmpty
	private String routeTo;
	
	@Future
	private LocalTime arrivalTime;
	
	@Future
	private LocalTime departureTime;
	
	@Min(0)
	private int seats;
	
	@Max(50)
	private int avaiableSeats;

}

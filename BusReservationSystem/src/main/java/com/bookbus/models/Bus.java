package com.bookbus.models;

import java.time.LocalTime;

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
public class Bus {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int busId;
	private String busName;
	private String driverName;
	private String busType;
	private String routeFrom;
	private String routeTo;
	private LocalTime arrivalTime;
	private LocalTime departureTime;
	private int seats;
	private int avaiableSeats;

}

package com.bookbus.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ReservationDto {
	
	private Integer reservationId;
	private String reservationStatus;
	private String reservationType;
	private String reservationDate;
	private String reservationTime;
	private String source;
	private String destination;
	@JsonIgnore
	private BusDto bus;
	
}

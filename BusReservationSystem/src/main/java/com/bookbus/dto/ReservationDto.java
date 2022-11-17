package com.bookbus.dto;


import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ReservationDto {
	
	
	private Integer reservationId;
	
	
	@NotNull(message = "Reservation status not found")
	@NotBlank(message = "Reservation status should not blank")
	@Size(min=3,max = 12,message = "Reservation status should be minimum 3 and maximum 12 character")
	private String reservationStatus;
	
	
	@NotNull(message = "Reservation type not found")
	@NotBlank(message = "Reservation type should not blank")
	@Size(min=5,max = 12,message = "Reservation type should be minimum 5 and maximum 12 character")
	private String reservationType;
	
	
	private String reservationDate;
	
	
	private String reservationTime;
	
	
	@NotNull(message = "Source place name not found")
	@NotBlank(message = "Source place name should not blank")
	@Size(min=3,max = 15,message = "Source place name should be minimum 3 and maximum 15 character")
	private String source;
	
	
	@NotNull(message = "Destination place name not found")
	@NotBlank(message = "Destination place name should not blank")
	@Size(min=3,max=15,message = "Destination place name should be minimum 3 and maximum 15 character")
	private String destination;
	
	
	@JsonIgnore
	private BusDto bus;
	
}

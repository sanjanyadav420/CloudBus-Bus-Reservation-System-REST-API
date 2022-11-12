package com.bookbus.models;

import java.time.LocalDate;
import java.time.LocalTime;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.validation.constraints.Future;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonFormat.Shape;
import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Reservation {
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE,generator="reservationSequence")
	@SequenceGenerator(name = "reservationSequence",sequenceName = "reservation_Id",allocationSize = 1,initialValue = 1001)
	private Integer reservationId;
	
//	@NotNull(message = "Reservation status not found")
//	@NotBlank(message = "Reservation status should not blank")
//	@NotEmpty(message = "Reservation status should not empty")
//	@Size(min=3,max = 12,message = "Reservation status should be minimum 3 and maximum 12 character")
	private String reservationStatus;
	
//	@NotNull(message = "Reservation type not found")
//	@NotBlank(message = "Reservation type should not blank")
//	@NotEmpty(message = "Reservation type should not empty")
//	@Size(min=5,max = 12,message = "Reservation type should be minimum 5 and maximum 12 character")
	private String reservationType;
	
//	@Future(message = "Reservation date should be in future")
	private LocalDate reservationDate;
	
//	@Future(message = "Reservation time should be in future")
	private LocalTime reservationTime;
	
//	@NotNull(message = "Source place name not found")
//	@NotBlank(message = "Source place name should not blank")
//	@NotEmpty(message = "Source place name should not empty")
//	@Size(min=3,max = 15,message = "Source place name should be minimum 3 and maximum 15 character")
	private String source;
	
//	@NotNull(message = "Destination place name not found")
//	@NotBlank(message = "Destination place name should not blank")
//	@NotEmpty(message = "Destination place name should not empty")
//	@Size(min=3,max=15,message = "Destination place name should be minimum 3 and maximum 15 character")
	private String destination;
	
//	@NotNull(message = "Bus details not found")
	@OneToOne(cascade = CascadeType.DETACH)
	@JsonIgnore
	private Bus bus;
}

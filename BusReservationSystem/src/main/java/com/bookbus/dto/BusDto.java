package com.bookbus.dto;


import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BusDto {
	
	
	private Integer busId;
	
	
	@NotNull(message = "Bus name not found")
	@NotBlank(message = "Bus name should not blank")
	@Size(min=3,max = 15,message = "Bus name should be minimum 3 and maximum 12 character")
	private String busName;
	
	
	@NotNull(message = "Bus driver name not found")
	@NotBlank(message = "Bus driver name should not blank")
	@Size(min=3,max = 16,message = "Bus driver name should be minimum 3 and maximum 16 character")
	private String driverName;
	
	
	@NotNull(message = "Bus type not found")
	@NotBlank(message = "Bus type should not blank")
	@Size(min=5,max = 12,message = "Bus type should be minimum 5 and maximum 12 character")
	private String busType;
	
	
	@NotNull(message = "Source station name not found")
	@NotBlank(message = "Source station name should not blank")
	@Size(min=3,max = 15,message = "Source station name should be minimum 3 and maximum 15 character")
	private String routeFrom;
	
	
	@NotNull(message = "Destination station name not found")
	@NotBlank(message = "Destination station name should not blank")
	@Size(min=3,max=15,message = "Destination station name should be minimum 3 and maximum 15 character")
	private String routeTo;
	
	
	private String arrivalTime;
	
	
	private String departureTime;
	
	
	@Min(value = 10,message = "Minimum seat is 10")
	private Integer seats;
	
	
	@Min(value = 1,message = "Minimum available seat is 1")
	private Integer avaiableSeats;
	
	
	
}

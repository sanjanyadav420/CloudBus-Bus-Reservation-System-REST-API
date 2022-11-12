package com.bookbus.models;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.springframework.stereotype.Repository;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Repository
@Entity
public class Route {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer routeId;
	
	@NotNull(message = "Starting point of Route cannot be null")
	@NotBlank(message = "Starting point of Route cannot be blank")
	private String routeFrom;
	
	@NotNull(message = "Destination point of Route cannot be null")
	@NotBlank(message = "Destination point of Route cannot be blank")
	private String routeTo;
	private Integer distance;
	
	@OneToMany(cascade = CascadeType.ALL)
	private List<Bus> busList = new ArrayList<>();
}

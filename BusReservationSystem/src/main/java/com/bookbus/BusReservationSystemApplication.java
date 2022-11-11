package com.bookbus;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.bookbus.models.Bus;

import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EnableSwagger2
public class BusReservationSystemApplication {

	public static void main(String[] args) {
		SpringApplication.run(BusReservationSystemApplication.class, args);
		
	}

}

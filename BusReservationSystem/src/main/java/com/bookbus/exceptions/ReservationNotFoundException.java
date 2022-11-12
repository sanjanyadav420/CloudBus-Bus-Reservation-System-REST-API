
package com.bookbus.exceptions;

public class ReservationNotFoundException extends Exception{

	public ReservationNotFoundException() {
		
	}
	
	public ReservationNotFoundException(String message) {
		super(message);
	}
}

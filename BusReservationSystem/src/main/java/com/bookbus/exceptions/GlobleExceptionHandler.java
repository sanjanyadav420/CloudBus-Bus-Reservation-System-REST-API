
package com.bookbus.exceptions;

import java.time.LocalDateTime;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

@ControllerAdvice
public class GlobleExceptionHandler {
	
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<MyErrorDetails> otherExceptionHandler(MethodArgumentNotValidException manve) {

		MyErrorDetails error = new MyErrorDetails();
		error.setTimestamp(LocalDateTime.now());
		error.setMessage("Validation failed...");
		error.setDetails(manve.getBindingResult().getFieldError().getDefaultMessage());

		return new ResponseEntity<MyErrorDetails>(error, HttpStatus.NOT_ACCEPTABLE);

	}
	
	@ExceptionHandler(UserException.class)
	public ResponseEntity<MyErrorDetails> otherExceptionHandler(UserException ue, WebRequest wReq) {

		MyErrorDetails error = new MyErrorDetails();
		error.setTimestamp(LocalDateTime.now());
		error.setMessage(ue.getMessage());
		error.setDetails(wReq.getDescription(false));

		return new ResponseEntity<MyErrorDetails>(error, HttpStatus.BAD_REQUEST);

	}
	
	@ExceptionHandler(LogException.class)
	public ResponseEntity<MyErrorDetails> otherExceptionHandler(LogException le, WebRequest wReq) {

		MyErrorDetails error = new MyErrorDetails();
		error.setTimestamp(LocalDateTime.now());
		error.setMessage(le.getMessage());
		error.setDetails(wReq.getDescription(false));

		return new ResponseEntity<MyErrorDetails>(error, HttpStatus.NOT_FOUND);

	}
	
	@ExceptionHandler(FeedbackException.class)
	public ResponseEntity<MyErrorDetails> studentExceptionEhandler(FeedbackException se, WebRequest req) {
		
		MyErrorDetails err= new MyErrorDetails();
		err.setTimestamp(LocalDateTime.now());
		err.setMessage(se.getMessage());
		err.setDetails(req.getDescription(false));
		
		return new ResponseEntity<MyErrorDetails>(err, HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(ReservationNotFoundException.class)
	public ResponseEntity<MyErrorDetails> reservatonNotFoundExceptionHandller(ReservationNotFoundException re,WebRequest req){
		MyErrorDetails err=new MyErrorDetails(LocalDateTime.now(),re.getMessage(),req.getDescription(false));
		return new ResponseEntity<MyErrorDetails>(err,HttpStatus.BAD_REQUEST);
	}
	
	
	@ExceptionHandler(BusNotFoundException.class)
	public ResponseEntity<MyErrorDetails> busNotFoundExceptionHandller(BusNotFoundException br,WebRequest req){
		MyErrorDetails err=new MyErrorDetails(LocalDateTime.now(),br.getMessage(),req.getDescription(false));
		return new ResponseEntity<MyErrorDetails>(err,HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(Exception.class)
	public ResponseEntity<MyErrorDetails> otherExceptionHandler(Exception e, WebRequest wReq) {

		MyErrorDetails error = new MyErrorDetails();
		error.setTimestamp(LocalDateTime.now());
		error.setMessage(e.getMessage());
		error.setDetails(wReq.getDescription(false));

		return new ResponseEntity<MyErrorDetails>(error, HttpStatus.BAD_REQUEST);

	}
	
	
	
}

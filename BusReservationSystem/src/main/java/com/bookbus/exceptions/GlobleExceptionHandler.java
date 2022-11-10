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
		error.setMessage(manve.getMessage());
		error.setDetails(manve.getBindingResult().getFieldError().getDefaultMessage());

		return new ResponseEntity<MyErrorDetails>(error, HttpStatus.NOT_ACCEPTABLE);

	}

	@ExceptionHandler(Exception.class)
	public ResponseEntity<MyErrorDetails> otherExceptionHandler(Exception e, WebRequest wReq) {

		MyErrorDetails error = new MyErrorDetails();
		error.setTimestamp(LocalDateTime.now());
		error.setMessage(e.getMessage());
		error.setDetails(wReq.getDescription(false));

		return new ResponseEntity<MyErrorDetails>(error, HttpStatus.INTERNAL_SERVER_ERROR);

	}
}

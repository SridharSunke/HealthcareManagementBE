package com.trion.healthcare.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.trion.healthcare.exception.UserNotFoundException;

@ControllerAdvice
public class RestErrorHandler {
	
	@ExceptionHandler(UserNotFoundException.class)
	public ResponseEntity<String> handleUserNotFoundException() {
		return new ResponseEntity("User Not Found For This Request",HttpStatus.NOT_FOUND);
		
	}

}

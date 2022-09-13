package com.trion.healthcare.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.trion.healthcare.exception.DepartmentNotFoundException;
import com.trion.healthcare.exception.UserNotFoundException;
import com.trion.healthcare.exception.WradNotFoundException;
import com.trion.healthcare.model.RestError;

@ControllerAdvice
public class RestErrorHandler {

	@ExceptionHandler(UserNotFoundException.class)
	public ResponseEntity<String> handleUserNotFoundException(Exception e) {
		return new ResponseEntity(e.getMessage(), HttpStatus.NOT_FOUND);

	}

	@ExceptionHandler(DepartmentNotFoundException.class)
	public ResponseEntity<RestError> handleDepartNotFoundException(Exception e) {
		return new ResponseEntity(new RestError(e), HttpStatus.BAD_REQUEST);

	}

	@ExceptionHandler(WradNotFoundException.class)
	public ResponseEntity<RestError> handlerWardNotFoundException(Exception e) {
		return new ResponseEntity(new RestError(e), HttpStatus.NOT_FOUND);
	}
}

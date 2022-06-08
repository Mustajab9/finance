package com.lawencon.auth.controller;

import org.springframework.core.NestedExceptionUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.lawencon.auth.exception.ValidateException;

@ControllerAdvice
public class ErrorHandlerController {

	@ExceptionHandler(ValidateException.class)
	public ResponseEntity<?> handleValidate(ValidateException e) {
		String message = NestedExceptionUtils.getMostSpecificCause(e).getMessage();
		return new ResponseEntity<>(message, HttpStatus.BAD_REQUEST);
	}
}

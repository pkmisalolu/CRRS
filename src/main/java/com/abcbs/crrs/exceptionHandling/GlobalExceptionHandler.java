package com.abcbs.crrs.exceptionHandling;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

	//if invalid query
	@ExceptionHandler(InValidQueryException.class)
	public ResponseEntity<String> handleResourceNotFound(InValidQueryException ex) {
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
	}

	//if status is closed
	@ExceptionHandler(StatusClosedException.class)
	public ResponseEntity<String> handleResourceNotFound(StatusClosedException ex) {
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
	}

	//if invalid activity  enters other than 'RAA'
	@ExceptionHandler(InValidActivityException.class)
	public ResponseEntity<String> handleResourceNotFound(InValidActivityException ex) {
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
	}

	//if invalid location  enters other than 'RAA'
	@ExceptionHandler(InValidLocationException.class)
	public ResponseEntity<String> handleResourceNotFound(InValidLocationException ex) {
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
	}

	//if zero records fetched from the query
	@ExceptionHandler(NoDataSelectedException.class)
	public ResponseEntity<String> handleResourceNotFound(NoDataSelectedException ex) {
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
	}

	//if invalid StartAfter value enters
	@ExceptionHandler(InvalidStartAfterException.class)
	public ResponseEntity<String> handleResourceNotFound(InvalidStartAfterException ex) {
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
	}


}

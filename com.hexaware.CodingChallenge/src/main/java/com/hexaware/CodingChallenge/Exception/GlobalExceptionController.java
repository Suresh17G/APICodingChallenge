package com.hexaware.CodingChallenge.Exception;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import jakarta.servlet.http.HttpServletRequest;

@ControllerAdvice
public class GlobalExceptionController {
	
		
	@ExceptionHandler(TaskNotFoundException.class)
	public ResponseEntity<errorDetails> TaskNotFound(TaskNotFoundException e,HttpServletRequest hr){
		String path = hr.getRequestURI();
		errorDetails error = new errorDetails(LocalDateTime.now(),e.getMessage(),path,HttpStatus.NOT_FOUND.toString());
		return new ResponseEntity<>(error,HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(UserExistsException.class)
	public ResponseEntity<errorDetails> UserExists(UserExistsException e,HttpServletRequest hr){
		String path = hr.getRequestURI();
		errorDetails error = new errorDetails(LocalDateTime.now(),e.getMessage(),path,HttpStatus.NOT_FOUND.toString());
		return new ResponseEntity<>(error,HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(Exception.class)
	public ResponseEntity<errorDetails> handelallexceptions(Exception e,HttpServletRequest hr){
		String path = hr.getRequestURI();
		errorDetails error = new errorDetails(LocalDateTime.now(),e.getClass().getName()+":"+e.getMessage(),path,HttpStatus.INTERNAL_SERVER_ERROR.toString());
		return new ResponseEntity<>(error,HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
}

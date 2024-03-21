package com.Student.Exception;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;

@RestController
@ControllerAdvice
public class ExceptionHandler {
	

	@org.springframework.web.bind.annotation.ExceptionHandler
		public ResponseEntity<Object> GlobalexceptionHandler(StudentException ex,WebRequest request){
		return new ResponseEntity<Object>(ex.getMessage(),HttpStatus.BAD_REQUEST);
			
		}
		

	}




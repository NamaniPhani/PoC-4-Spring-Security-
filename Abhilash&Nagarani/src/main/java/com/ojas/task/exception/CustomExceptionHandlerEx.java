
package com.ojas.task.exception;

import javax.validation.ConstraintViolationException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.ojas.task.model.Response;

@ControllerAdvice
public class CustomExceptionHandlerEx {

	@ExceptionHandler(MethodArgumentNotValidException.class)

	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public ResponseEntity<?> methodArgumentexceptionHandlerMethod(MethodArgumentNotValidException e) {
		Response response = new Response();
		response.setObject(null);
		response.setStatus(HttpStatus.BAD_REQUEST);
		response.setMessage(e.getBindingResult().getFieldError().getDefaultMessage());
		return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(ConstraintViolationException.class)

	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public ResponseEntity<?> constraintViolationExceptionHandlerMethod(ConstraintViolationException e) {
		Response response = new Response();
		response.setObject(null);
		response.setStatus(HttpStatus.BAD_REQUEST);
		response.setMessage(e.getMessage().toString());
		return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
	}
}

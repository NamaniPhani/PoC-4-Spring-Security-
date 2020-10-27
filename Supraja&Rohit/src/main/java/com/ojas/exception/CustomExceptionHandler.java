package com.ojas.exception;

import java.util.Date;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class CustomExceptionHandler extends ResponseEntityExceptionHandler {

	@ExceptionHandler(value = CustomException.class)
	public ResponseEntity<Response> customException(CustomException cusException, Exception ex) {
		String localizedMessage = cusException.getLocalizedMessage();
		Response response = new Response();
		response.setStatuscode("409");
		response.setStatus("FAIL");
		response.setMessage(localizedMessage);
		response.setTimestamp(new Date());
		return new ResponseEntity<>(response, HttpStatus.UNPROCESSABLE_ENTITY);
	}

}

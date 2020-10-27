package com.ojas.SpringSecurity.exception;



import static com.ojas.SpringSecurity.util.Constants.FAIL;
import static com.ojas.SpringSecurity.util.Constants.FAIL_STATUS;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.ojas.SpringSecurity.response.Response;

@RestControllerAdvice
public class CustomExceptionHandler extends ResponseEntityExceptionHandler {

	@ExceptionHandler(value = CustomException.class)
	public ResponseEntity<Response> customException(CustomException cusException, Exception ex) {
		String localizedMessage = cusException.getLocalizedMessage();
		Response response = new Response();
		response.setStatucCode(FAIL_STATUS);
		response.setStatusMessage(FAIL);
		response.setStatusMessage(localizedMessage);
		return new ResponseEntity<>(response, HttpStatus.UNPROCESSABLE_ENTITY);
	}

}

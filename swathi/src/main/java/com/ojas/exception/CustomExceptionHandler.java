package com.ojas.exception;



import java.util.Date;
import static com.ojas.util.Constants.FAIL_STATUS;
import static com.ojas.util.Constants.FAIL;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.ojas.response.Response;

@ControllerAdvice
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

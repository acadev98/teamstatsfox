package com.acadev.teamstatsfox.handler;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;

import com.acadev.teamstatsfox.handler.exception.ApiException;
import com.acadev.teamstatsfox.model.dto.ResponseDTO;
import com.acadev.teamstatsfox.utils.enums.ApiMessage;

@ControllerAdvice
@RestController
public class GlobalExceptionHandler {
	
	private static final Logger LOG = LoggerFactory.getLogger(GlobalExceptionHandler.class);
	
	@ExceptionHandler(Exception.class)
	public final ResponseEntity<ResponseDTO> allExceptionHandler(Exception ex, WebRequest request) {
		
		LOG.info("GlobalExceptionHandler > allExceptionHandler > ex: {}", ex);
		
		ResponseDTO error = new ResponseDTO();
			error.setCode(ApiMessage.E5XX_GENERIC_ERROR_MESSAGE.getHttpStatus().value());
			error.setMessage(ApiMessage.E5XX_GENERIC_ERROR_MESSAGE.getMessage());
			
			return new ResponseEntity<ResponseDTO>(error, ApiMessage.E5XX_GENERIC_ERROR_MESSAGE.getHttpStatus());
	}
	
	@ExceptionHandler(ApiException.class)
	public final ResponseEntity<ResponseDTO> apiExceptionHandler(ApiException ex, WebRequest request) {
		
		LOG.info("GlobalExceptionHandler > apiExceptionHandler > ex: {}", ex);
		
		ApiMessage apiMessage = ex.getApiMessage();
		
		ResponseDTO error = new ResponseDTO();
			error.setCode(apiMessage.getHttpStatus().value());
			error.setMessage(apiMessage.getMessage());
			
			return new ResponseEntity<ResponseDTO>(error, ApiMessage.E5XX_GENERIC_ERROR_MESSAGE.getHttpStatus());
	}

}
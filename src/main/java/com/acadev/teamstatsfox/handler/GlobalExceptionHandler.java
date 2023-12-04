package com.acadev.teamstatsfox.handler;

import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
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

	@ExceptionHandler(AccessDeniedException.class)
	public final ResponseEntity<ResponseDTO> accessDeniedExceptionHandler(ApiException ex, WebRequest request) {

		LOG.info("GlobalExceptionHandler > accessDeniedExceptionHandler > ex: {}", ex);

		ResponseDTO error = new ResponseDTO();
			error.setCode(ApiMessage.ACCESS_DENIED.getHttpStatus().value());
			error.setMessage(ApiMessage.ACCESS_DENIED.getMessage());

		return new ResponseEntity<ResponseDTO>(error, ApiMessage.ACCESS_DENIED.getHttpStatus());
	}

	@ExceptionHandler(ApiException.class)
	public final ResponseEntity<ResponseDTO> apiExceptionHandler(ApiException ex, WebRequest request) {

		LOG.info("GlobalExceptionHandler > apiExceptionHandler > ex: {}", ex);

		ApiMessage apiMessage = ex.getApiMessage();

		ResponseDTO error = new ResponseDTO();
			error.setCode(apiMessage.getHttpStatus().value());
			error.setMessage(apiMessage.getMessage());

		return new ResponseEntity<ResponseDTO>(error, ex.getApiMessage().getHttpStatus());
	}

	@ExceptionHandler(MethodArgumentNotValidException.class)
	public final ResponseEntity<ResponseDTO> methodArgumentNotValidExceptionHandler(MethodArgumentNotValidException ex, WebRequest request) {

		LOG.info("GlobalExceptionHandler > methodArgumentNotValidExceptionHandler > ex: {}", ex);

		List<String> errors = ex.getBindingResult().getFieldErrors().stream()
				.map(DefaultMessageSourceResolvable::getDefaultMessage).collect(Collectors.toList());

		ResponseDTO error = new ResponseDTO();
			error.setCode(HttpStatus.BAD_REQUEST.value());
			error.setMessage(errors.get(0));

		return new ResponseEntity<ResponseDTO>(error, HttpStatus.BAD_REQUEST);
	}

}

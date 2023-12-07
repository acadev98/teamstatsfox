package com.acadev.teamstatsfox.handler;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.MultiValueMap;

import com.acadev.teamstatsfox.model.dto.ResponseDTO;

public class ResponseHandler {

	public static ResponseEntity<Object> generateResponse(String message, HttpStatus status) {
		return generateResponse(message, status, null, null);
	}

	public static ResponseEntity<Object> generateResponse(String message, HttpStatus status,
			MultiValueMap<String, String> headers) {
		return generateResponse(message, status, null, headers);
	}

	public static ResponseEntity<Object> generateResponse(String message, HttpStatus status, Object responseObj) {
		return generateResponse(message, status, responseObj, null);
	}

	public static ResponseEntity<Object> generateResponse(String message, HttpStatus status, Object responseObj,
			MultiValueMap<String, String> headers) {
		ResponseDTO response = new ResponseDTO();

		response.setCode(status.value());
		response.setMessage(message);

		if (responseObj != null) {
			response.setData(responseObj);
		}

		if (headers != null) {
			return new ResponseEntity<Object>(response, headers, status);
		} else {
			return new ResponseEntity<Object>(response, status);
		}

	}

}
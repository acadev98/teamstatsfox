package com.acadev.teamstatsfox.handler;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.MultiValueMap;

public class ResponseHandler {

	public static ResponseEntity<Object> generateResponse(Object responseObj) {
		return generateResponse(responseObj, HttpStatus.OK, HttpStatus.OK.name());
	}

	public static ResponseEntity<Object> generateResponse(Object responseObj, String message) {
		return generateResponse(responseObj, HttpStatus.OK, message);
	}

	public static ResponseEntity<Object> generateResponse(Object responseObj, HttpStatus status) {
		return generateResponse(responseObj, status, status.name());
	}

	public static ResponseEntity<Object> generateResponse(Object responseObj, HttpStatus status, String message) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("message", message);
		map.put("status", status.value());

		if (responseObj != null) {
			map.put("data", responseObj);
		}

		return new ResponseEntity<Object>(map, status);
	}

	public static ResponseEntity<Object> generateResponse(Object responseObj, MultiValueMap<String, String> headers,
			HttpStatus status) {
		return new ResponseEntity<Object>(responseObj, headers, status);
	}

	public static ResponseEntity<Object> generateResponse(Object responseObj, MultiValueMap<String, String> headers) {
		return new ResponseEntity<Object>(responseObj, headers, HttpStatus.OK);
	}
}
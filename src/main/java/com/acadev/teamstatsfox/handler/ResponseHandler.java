package com.acadev.teamstatsfox.handler;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class ResponseHandler {
    public static ResponseEntity<Object> generateResponse(Object responseObj, HttpStatus status) {
    	return generateResponse(responseObj, status, status.name());
    }
    public static ResponseEntity<Object> generateResponse(Object responseObj, HttpStatus status, String message) {
        Map<String, Object> map = new HashMap<String, Object>();
            map.put("message", message);
            map.put("status", status.value());
            map.put("data", responseObj);

            return new ResponseEntity<Object>(map,status);
    }
}
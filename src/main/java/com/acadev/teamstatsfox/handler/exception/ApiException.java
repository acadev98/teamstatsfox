package com.acadev.teamstatsfox.handler.exception;

import com.acadev.teamstatsfox.utils.enums.ApiMessage;

import lombok.Data;

@Data
public class ApiException extends RuntimeException {
	
	private ApiMessage apiMessage;
	
	public ApiException(ApiMessage apiMessage) {
		super(apiMessage.getMessage());
		this.apiMessage = apiMessage;
	}
	
	public ApiException(String message, ApiMessage apiMessage) {
		super(message);
		this.apiMessage = apiMessage;
	}
	
}

package com.acadev.teamstatsfox.utils.enums;

import org.springframework.http.HttpStatus;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ApiMessage {
	OK(HttpStatus.OK, "Operacion Exitosa."),
	E5XX_GENERIC_ERROR_MESSAGE(HttpStatus.INTERNAL_SERVER_ERROR, "Error interno de sistema."),
	EMAIL_USER_ALREADY_EXISTS(HttpStatus.BAD_REQUEST, "Usuario ya existe."), 
	LOGIN_NO_ACTIVE(HttpStatus.BAD_REQUEST, "Esperar activacion de admin."), 
	CREDENTIALS_INCORRECT(HttpStatus.BAD_REQUEST, "Credenciales incorrectas."),
	;
	
	private final HttpStatus httpStatus;
	private final String message;
	
}
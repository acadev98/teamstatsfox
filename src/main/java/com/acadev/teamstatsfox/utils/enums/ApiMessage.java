package com.acadev.teamstatsfox.utils.enums;

import org.springframework.http.HttpStatus;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ApiMessage {
	OK(HttpStatus.OK, "Operacion Exitosa."),
	E5XX_GENERIC_ERROR_MESSAGE(HttpStatus.INTERNAL_SERVER_ERROR, "Error interno de sistema."),
	EMAIL_USER_ALREADY_EXISTS(HttpStatus.BAD_REQUEST, "Email ya existe registrado."),
	USERNAME_ALREADY_EXISTS(HttpStatus.BAD_REQUEST, "Nombre de usuario ya existe."),
	LOGIN_NO_ACTIVE(HttpStatus.BAD_REQUEST, "Esperar activacion de admin."),
	CREDENTIALS_INCORRECT(HttpStatus.BAD_REQUEST, "Credenciales incorrectas."),
	USER_NOT_FOUND(HttpStatus.BAD_REQUEST, "Usuario inexistente."),
	ROLE_USER_NOT_FOUND(HttpStatus.BAD_REQUEST, "Usuario no tiene rol asignado."),
	ACCESS_DENIED(HttpStatus.UNAUTHORIZED, "Acceso denegado."),
	CONTENT_NOT_FOUND(HttpStatus.NOT_FOUND, "No se encontraron resultados."),
	OPPONENT_ALREADY_EXISTS(HttpStatus.BAD_REQUEST, "El equipo rival ya existe."),
	FILE_EMPTY(HttpStatus.BAD_REQUEST, "El archivo enviado es vacío."),
	;

	private final HttpStatus httpStatus;
	private final String message;

}

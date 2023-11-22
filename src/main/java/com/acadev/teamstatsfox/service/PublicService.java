package com.acadev.teamstatsfox.service;

import org.springframework.http.ResponseEntity;

public interface PublicService {

	ResponseEntity<Object> echo();

	ResponseEntity<Object> getDataStatic();

	ResponseEntity<Object> getDataCvs();

	ResponseEntity<Object> getPlayers();

}

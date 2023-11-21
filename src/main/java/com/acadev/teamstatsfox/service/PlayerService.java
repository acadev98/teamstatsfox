package com.acadev.teamstatsfox.service;

import org.springframework.http.ResponseEntity;

public interface PlayerService {

	ResponseEntity<Object> echo();

	ResponseEntity<Object> get();

	ResponseEntity<Object> get(Long id);

}

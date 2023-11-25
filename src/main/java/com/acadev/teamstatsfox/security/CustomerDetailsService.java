package com.acadev.teamstatsfox.security;

import java.util.ArrayList;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.acadev.teamstatsfox.database.entity.User;
import com.acadev.teamstatsfox.database.repository.UserRepository;
import com.acadev.teamstatsfox.handler.exception.ApiException;
import com.acadev.teamstatsfox.utils.enums.ApiMessage;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class CustomerDetailsService implements UserDetailsService {
	
	@Autowired
	private UserRepository repository;
	
	private Optional<User> userDetail;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		log.info("loadUserByUsername > username: {}", username);
		userDetail = repository.findByEmail(username);
		
		if (userDetail.isPresent()) {
			return new org.springframework.security.core.userdetails.User(userDetail.get().getEmail(), userDetail.get().getPassword(), new ArrayList<>());
		} else {
			throw new ApiException(ApiMessage.E5XX_GENERIC_ERROR_MESSAGE);
		}
	}
	
	public User getUserDetaiUser() {
		return userDetail.get();
	}

}

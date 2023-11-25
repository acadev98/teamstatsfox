package com.acadev.teamstatsfox.service.impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.acadev.teamstatsfox.database.entity.User;
import com.acadev.teamstatsfox.database.repository.UserRepository;
import com.acadev.teamstatsfox.handler.exception.ApiException;
import com.acadev.teamstatsfox.service.UserService;
import com.acadev.teamstatsfox.utils.enums.ApiMessage;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository repository;
	
	public String echo() {
		return "user echo message";
	}

	public User create(User user) {
		Optional<User> userEmailExist = repository.findByEmail(user.getEmail());
		if (userEmailExist.isPresent()) {
			throw new ApiException(ApiMessage.EMAIL_USER_ALREADY_EXISTS);
		}
		return repository.save(user);
	}

}

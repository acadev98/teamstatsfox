package com.acadev.teamstatsfox.security;

import java.util.ArrayList;
import java.util.List;
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

@Service
public class CustomUserDetailsService implements UserDetailsService {

	@Autowired
	private UserRepository repository;

	public CustomUserDetailsService(UserRepository userRepository) {
		this.repository = userRepository;
	}

	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		Optional<User> user = repository.findByEmail(email);

		if (user.isPresent()) {
			List<String> roles = new ArrayList<>();
			roles.add("USER");
			UserDetails userDetails = org.springframework.security.core.userdetails.User.builder()
					.username(user.get().getEmail()).password(user.get().getPassword())
					.roles(roles.toArray(new String[0])).build();
			return userDetails;
		} else {
			throw new ApiException(ApiMessage.E5XX_GENERIC_ERROR_MESSAGE);
		}
	}

}

package com.acadev.teamstatsfox.service.impl;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import com.acadev.teamstatsfox.database.entity.Roles;
import com.acadev.teamstatsfox.database.entity.User;
import com.acadev.teamstatsfox.database.repository.RoleRepository;
import com.acadev.teamstatsfox.database.repository.UserRepository;
import com.acadev.teamstatsfox.handler.exception.ApiException;
import com.acadev.teamstatsfox.model.request.LoginRequest;
import com.acadev.teamstatsfox.model.request.RegisterRequest;
import com.acadev.teamstatsfox.model.response.LoginResponse;
import com.acadev.teamstatsfox.utils.ConstantsUtils;
import com.acadev.teamstatsfox.utils.JwtUtil;
import com.acadev.teamstatsfox.utils.enums.ApiMessage;
import com.acadev.teamstatsfox.utils.enums.ERole;

@Service
public class AuthServiceImpl {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private RoleRepository roleRepository;

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private JwtUtil jwtUtil;
	
	public Long getNextId() {
		Optional<User> entityMaxId = userRepository.findTopByOrderByIdDesc();
		if (entityMaxId.isPresent())
				return (entityMaxId.get().getId()+1);
        return 1L;
    }

	public LoginResponse login(LoginRequest loginRequest) {

		Authentication authentication;

		try {
			authentication = authenticationManager.authenticate(
					new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));
		} catch (BadCredentialsException e) {
			throw new ApiException(ApiMessage.CREDENTIALS_INCORRECT);
		}

		SecurityContextHolder.getContext().setAuthentication(authentication);
		String jwt = jwtUtil.generateJwtToken(authentication);
		UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();

		List<String> roles = userDetails.getAuthorities().stream().map(item -> item.getAuthority())
				.collect(Collectors.toList());

		LoginResponse res = LoginResponse.builder().id(userDetails.getId()).username(userDetails.getUsername())
				.email(userDetails.getEmail()).token(jwt).type(ConstantsUtils.JWT_TYPE).roles(roles).build();

		return res;
	}

	public User register(@RequestBody RegisterRequest registerRequest) {

		if (userRepository.existsByUsername(registerRequest.getUsername())) {
			throw new ApiException(ApiMessage.USERNAME_ALREADY_EXISTS);
		}

		if (userRepository.existsByEmail(registerRequest.getEmail())) {
			throw new ApiException(ApiMessage.EMAIL_USER_ALREADY_EXISTS);
		}

		String hashedPassword = passwordEncoder.encode(registerRequest.getPassword());

		Optional<Roles> userRole = roleRepository.findByName(ERole.ROLE_USER);
		if (userRole.isEmpty()) {
			throw new ApiException(ApiMessage.ROLE_USER_NOT_FOUND);
		}

		Set<Roles> roles = new HashSet<>();
		roles.add(userRole.get());

		User user = User.builder().id(getNextId()).username(registerRequest.getUsername()).email(registerRequest.getEmail())
				.password(hashedPassword).roles(roles).build();

		return userRepository.save(user);
	}

}

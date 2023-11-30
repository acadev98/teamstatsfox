package com.acadev.teamstatsfox.service.impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import com.acadev.teamstatsfox.database.entity.User;
import com.acadev.teamstatsfox.database.repository.UserRepository;
import com.acadev.teamstatsfox.handler.exception.ApiException;
import com.acadev.teamstatsfox.model.request.LoginRequest;
import com.acadev.teamstatsfox.model.response.LoginResponse;
import com.acadev.teamstatsfox.security.CustomUserDetailsService;
import com.acadev.teamstatsfox.security.jwt.JwtUtil;
import com.acadev.teamstatsfox.service.UserService;
import com.acadev.teamstatsfox.utils.enums.ApiMessage;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository repository;
	
	@Autowired
	AuthenticationManager authenticationManager;
	
	@Autowired
	CustomUserDetailsService customerDetailsService;
	
	@Autowired
	JwtUtil jwtUtil;
	
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
	
	public LoginResponse login(LoginRequest request) {
		try {
            Authentication authentication =
                    authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword()));
            String email = authentication.getName();
            User user = User.builder().email(email).build();
            String token = jwtUtil.createToken(user);
            LoginResponse response = LoginResponse.builder().email(email).token(token).build();
            
            return response;

        }catch (BadCredentialsException e){
			throw new ApiException(ApiMessage.CREDENTIALS_INCORRECT);
        }catch (Exception e){
			throw new ApiException(ApiMessage.E5XX_GENERIC_ERROR_MESSAGE);
        }
	}
	
	/**
	public LoginResponse login(LoginRequest request) {
//		log.info("loadUserByUsername > login: {}", request.toString());
		try {
			Authentication authentication = authenticationManager.authenticate(
					new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword())
			);
		
			if (authentication.isAuthenticated()) {
				if (customerDetailsService.getUserDetaiUser().getStatus().equalsIgnoreCase("true")) {
					String token = jwtUtil.generateToken(customerDetailsService.getUserDetaiUser().getEmail(), customerDetailsService.getUserDetaiUser().getRole());
					LoginResponse response = new LoginResponse();
						response.setEmail(customerDetailsService.getUserDetaiUser().getEmail());
						response.setToken(token);
					return response;
				} else {
					throw new ApiException(ApiMessage.LOGIN_NO_ACTIVE);
				}
			}
			throw new ApiException(ApiMessage.CREDENTIALS_INCORRECT);
			
		} catch (Exception e) {
			throw new ApiException(ApiMessage.CREDENTIALS_INCORRECT);
		}
		
	}*/

}

package com.acadev.teamstatsfox.service;

import com.acadev.teamstatsfox.database.entity.User;
import com.acadev.teamstatsfox.model.request.LoginRequest;
import com.acadev.teamstatsfox.model.response.LoginResponse;

public interface UserService {

	String echo();

	User create(User user);

	LoginResponse login(LoginRequest user);

}

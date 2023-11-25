package com.acadev.teamstatsfox.service;

import com.acadev.teamstatsfox.database.entity.User;

public interface UserService {

	String echo();

	User create(User user);

}

package com.alien.services;

import java.util.List;

import com.alien.entities.User;

public interface UserService {

	User saveUser(User user);

	List<User> getAllUser();

	User getUser(String userId);
	
}

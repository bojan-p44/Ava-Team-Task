package com.ava.service;

import com.ava.model.User;


public interface UserService {

	void save(User user);

	User findUserByEmail(String email);
}

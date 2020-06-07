package com.ava.service;

import com.ava.entity.User;


public interface UserService {

	void save(User user);

	User findUserByEmail(String email);
}

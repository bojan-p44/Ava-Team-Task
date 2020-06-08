package com.ava.service;

import com.ava.model.User;
import com.ava.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository userRepository;

	@Override
	public void save(User user) {

	}

	@Override
	public User findUserByEmail(String email) {
		return null;
	}
}

package com.ava.service;

import com.ava.entity.User;
import com.ava.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository userRepository;

	@Override
	public void save(User user) {
		userRepository.save(user);
	}

	@Override
	public User findUserByEmail(String email) {
		Optional<User> user = userRepository.findUserByEmail(email);
		return user.orElse(null);
	}
}

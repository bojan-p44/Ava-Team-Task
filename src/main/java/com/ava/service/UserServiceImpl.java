package com.ava.service;

import com.ava.exception.UserAlreadyExists;
import com.ava.exception.UserNotFoundException;
import com.ava.model.Role;
import com.ava.model.User;
import com.ava.model.UserDTO;
import com.ava.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private PasswordEncoder bcryptEncoder;

	@Override
	public User save(UserDTO userDTO, Role role) {
		Optional<User> user = userRepository.findUserByEmail(userDTO.getEmail());
		if (user.isPresent()) {
			throw new UserAlreadyExists("User with email: " + userDTO.getEmail() + " already exists");
		}
		User newUser = new User();
		newUser.setFirstName(userDTO.getFirstName());
		newUser.setLastName(userDTO.getLastName());
		newUser.setEmail(userDTO.getEmail());
		newUser.setAddress(userDTO.getAddress());
		newUser.setCountry(userDTO.getCountry());
		newUser.setPassword(bcryptEncoder.encode(userDTO.getPassword()));
		newUser.setRole(role);
		return userRepository.save(newUser);
	}

	@Override
	public User findUserByEmail(String email) {
		Optional<User> user = userRepository.findUserByEmail(email);
		if (!user.isPresent()){
			throw new UserNotFoundException("User with email: " + email + " doesn't exist");
		}
		return user.get();
	}

	@Override
	public User findUserById(Long id) {
		Optional<User> user = userRepository.findUserById(id);
		if (!user.isPresent()){
			throw new UserNotFoundException("User with id: " + id + " doesn't exists");
		}
		return user.get();
	}
}

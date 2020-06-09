package com.ava.service;

import com.ava.exception.BadRequestException;
import com.ava.exception.UserAlreadyExists;
import com.ava.exception.UserNotFoundException;
import com.ava.entity.*;
import com.ava.entity.enumeration.Role;
import com.ava.entity.request.CreateUserRequest;
import com.ava.entity.request.UpdateCurrentUserRequest;
import com.ava.entity.request.UpdateUserRequest;
import com.ava.repository.UserRepository;
import org.apache.commons.lang3.EnumUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService, UserDetailsService {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private PasswordEncoder bcryptEncoder;

	@Override
	public User findUserByEmail(String email) {
		Optional<User> user = userRepository.findUserByEmail(email);
		if (!user.isPresent()) {
			throw new UserNotFoundException("User with email: " + email + " doesn't exist");
		}
		return user.get();
	}

	@Override
	public User findUserById(Long id) {
		Optional<User> user = userRepository.findUserById(id);
		if (!user.isPresent()) {
			throw new UserNotFoundException("User with id: " + id + " doesn't exists");
		}
		return user.get();
	}

	@Override
	public List<User> findUsers( String role,SearchRecord searchRecord) {
		return userRepository.findBy(role,searchRecord);
	}

	@Override
	@Transactional
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		Optional<User> user = userRepository.findUserByEmail(email);
		if (!user.isPresent()) {
			throw new UsernameNotFoundException("User not found with email: " + email);
		}

		List<String> role = new ArrayList<>();
		role.add(user.get().getRole().name());

		List<GrantedAuthority> authorities = role.stream()
				.map(SimpleGrantedAuthority::new)
				.collect(Collectors.toList());

		return new org.springframework.security.core.userdetails.User(user.get().getEmail(), user.get().getPassword(),
				authorities);
	}

	@Override
	@Transactional
	public User saveUser(CreateUserRequest request, Role role) {
		Optional<User> user = userRepository.findUserByEmail(request.getEmail());
		if (user.isPresent()) {
			throw new UserAlreadyExists("User with email: " + request.getEmail() + " already exists");
		}
		User newUser = new User();
		newUser.setFirstName(request.getFirstName());
		newUser.setLastName(request.getLastName());
		newUser.setEmail(request.getEmail());
		newUser.setAddress(request.getAddress());
		newUser.setCountry(request.getCountry());
		newUser.setPassword(bcryptEncoder.encode(request.getPassword()));
		newUser.setRole(role);
		return userRepository.save(newUser);
	}

	@Override
	@Transactional
	public User updateCurrentUser(String email, UpdateCurrentUserRequest request) {
		User user = findUserByEmail(email);
		if (userRepository.existsUsersByEmailAndIdNotLike(request.getEmail(), user.getId())) {
			throw new UserAlreadyExists("User with email: " + request.getEmail() + " already exists");
		}
		if (!request.getPassword().equals(request.getPasswordConfirm())) {
			throw new BadRequestException("Password and Confirm password don't have the same value");
		}
		user.setFirstName(request.getFirstName());
		user.setLastName(request.getLastName());
		user.setEmail(user.getEmail());
		user.setAddress(user.getAddress());
		user.setCountry(user.getCountry());
		user.setPassword(request.getPassword());
		return userRepository.save(user);
	}

	@Override
	@Transactional
	public User updateUser(Long id, UpdateUserRequest request) {
		User user = findUserById(id);
		if (userRepository.existsUsersByEmailAndIdNotLike(request.getEmail(), id)) {
			throw new UserAlreadyExists("User with email: " + request.getEmail() + " already exists");
		}
		if (!EnumUtils.isValidEnum(Role.class, request.getRole().name())) {
			throw new BadRequestException("Role with name: " + request.getRole().name() + " doesn't exist");
		}
		user.setRole(request.getRole());
		user.setCountry(request.getCountry());
		user.setAddress(request.getAddress());
		user.setEmail(request.getEmail());
		user.setFirstName(request.getFirstName());
		user.setLastName(request.getLastName());
		return userRepository.save(user);
	}
}

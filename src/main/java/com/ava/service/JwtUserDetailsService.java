package com.ava.service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import com.ava.model.Role;
import com.ava.model.User;
import com.ava.model.UserDTO;
import com.ava.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


@Service
public class JwtUserDetailsService implements UserDetailsService {
	
	@Autowired
	private UserRepository userRepository;

	@Autowired
	private PasswordEncoder bcryptEncoder;

	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		Optional<User> user = userRepository.findUserByEmail(email);
		if (!user.isPresent()) {
			throw new UsernameNotFoundException("User not found with email: " + email);
		}

		List<String> role = new ArrayList<>();
		role.add(user.get().getRole().name());

		List<GrantedAuthority> authorities = role.stream()
				.map(r -> new SimpleGrantedAuthority(r))
				.collect(Collectors.toList());

		return new org.springframework.security.core.userdetails.User(user.get().getEmail(), user.get().getPassword(),
				authorities);
	}
	
	public User save(UserDTO user) {
		User newUser = new User();
		newUser.setFirstName(user.getFirstName());
		newUser.setLastName(user.getLastName());
		newUser.setEmail(user.getEmail());
		newUser.setAddress(user.getAddress());
		newUser.setCountry(user.getCountry());
		newUser.setPassword(bcryptEncoder.encode(user.getPassword()));
		newUser.setRole(Role.ROLE_STANDARD);
		return userRepository.save(newUser);
	}
}

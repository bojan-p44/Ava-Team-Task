package com.ava.service;

import com.ava.model.Role;
import com.ava.model.User;
import com.ava.model.UserDTO;


public interface UserService {

	User save(UserDTO user, Role role);

	User findUserByEmail(String email);

	User findUserById(Long id);
}

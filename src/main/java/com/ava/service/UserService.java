package com.ava.service;

import com.ava.model.*;
import com.ava.model.enumeration.Role;

import java.util.List;


public interface UserService {

	User updateCurrentUser(String email, UpdateCurrentUserRequest request);

	User updateUser(Long id, UpdateUserRequest user);

	User saveUser(CreateUserRequest user, Role role);

	User findUserByEmail(String email);

	User findUserById(Long id);

	List<User> findUsers(String role, SearchRecord searchRecord);
}

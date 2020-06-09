package com.ava.service;

import com.ava.entity.*;
import com.ava.entity.enumeration.Role;
import com.ava.entity.request.CreateUserRequest;
import com.ava.entity.request.UpdateCurrentUserRequest;
import com.ava.entity.request.UpdateUserRequest;

import java.util.List;


public interface UserService {

	User updateCurrentUser(String email, UpdateCurrentUserRequest request);

	User updateUser(Long id, UpdateUserRequest user);

	User saveUser(CreateUserRequest user, Role role);

	User findUserByEmail(String email);

	User findUserById(Long id);

	List<User> findUsers(String role, SearchRecord searchRecord);
}

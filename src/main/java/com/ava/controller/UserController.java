package com.ava.controller;

import com.ava.dto.SearchRecord;
import com.ava.model.*;
import com.ava.model.enumeration.Role;
import com.ava.dto.CreateUserRequest;
import com.ava.dto.UpdateCurrentUserRequest;
import com.ava.dto.UpdateUserRequest;
import com.ava.service.UserService;
import io.swagger.annotations.*;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;


@RestController
@RequestMapping("/user")
@Api(tags = "user-controller")
@Log4j2
public class UserController {

	@Autowired
	private UserService userService;

	// GET

	@ApiOperation(value = "Get multiple Users details by params", response = List.class)
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Successfully retrieved Users details"),
			@ApiResponse(code = 401, message = "You are not authorized"),
			@ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden")
	})
	@GetMapping("/search")
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	public List<User> getMultipleUsers(@RequestParam(required = false) String firstName,
									   @RequestParam(required = false) String lastName,
									   @RequestParam(required = false) String email,
									   @RequestParam(required = false) String address,
									   @RequestParam(required = false) String country,
									   @RequestParam(required = false) Role role) {

		SearchRecord searchRecord = new SearchRecord(firstName, lastName, email, address, country);
		if (role != null) {
			log.info("Admin User with email: " + userService.getCurrentUserEmail() + " searching for users by params: " + searchRecord.toString() + " and role: " + role.name());
			return userService.findUsers(role.name(), searchRecord);
		} else {
			log.info("Admin User with email: " + userService.getCurrentUserEmail() + " searching for users by params: " + searchRecord.toString());
			return userService.findUsers(null, searchRecord);
		}
	}

	@ApiOperation(value = "Get User details by Id", response = ResponseEntity.class)
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Successfully retrieved User details"),
			@ApiResponse(code = 401, message = "You are not authorized"),
			@ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
	})
	@GetMapping("/{id}")
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	public User getUserDetails(@PathVariable Long id) {
		log.info("Admin User with email: " + userService.getCurrentUserEmail() + " searching for User details with id: " + id);
		return userService.findUserById(id);
	}

	@ApiOperation(value = "Get Logged in User details", response = ResponseEntity.class)
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Successfully retrieved User details"),
			@ApiResponse(code = 401, message = "You are not authorized"),
			@ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
			@ApiResponse(code = 404, message = "The resource you were trying to reach is not found")
	})
	@GetMapping("/myDetails")
	public User getMyDetails() {
		return userService.findCurrentUser();
	}

	// POST

	@ApiOperation(value = "Create new Admin User", response = ResponseEntity.class)
	@ApiResponses(value = {
			@ApiResponse(code = 201, message = "Successfully created Admin user"),
			@ApiResponse(code = 401, message = "You are not authorized to create Admin user"),
			@ApiResponse(code = 400, message = "You are sending a wrong request to server"),
			@ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
			@ApiResponse(code = 409, message = "User already registered")
	})
	@PostMapping(path = "/admin/create", produces = "application/json")
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	public ResponseEntity<User> createAdminUser(@RequestBody CreateUserRequest request) {
		User user = userService.saveUser(request, Role.ROLE_ADMIN);
		log.info("Admin User with email: " + userService.getCurrentUserEmail() + " created Admin user, data: " + request.toString());
		return new ResponseEntity<>(user, HttpStatus.CREATED);
	}

	@ApiOperation(value = "Create new Standard User", response = ResponseEntity.class)
	@ApiResponses(value = {
			@ApiResponse(code = 201, message = "Successfully created Standard user"),
			@ApiResponse(code = 401, message = "You are not authorized to create Standard user"),
			@ApiResponse(code = 400, message = "You are sending a wrong request to server"),
			@ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
			@ApiResponse(code = 409, message = "User already registered")
	})
	@PostMapping(path = "/standard/create", produces = "application/json")
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	public ResponseEntity<User> createStandardUser(@Valid @RequestBody CreateUserRequest request) {
		User user = userService.saveUser(request, Role.ROLE_STANDARD);
		log.info("Admin User with email: " + userService.getCurrentUserEmail() + " created Standard user, data: " + request.toString());
		return new ResponseEntity<>(user, HttpStatus.CREATED);
	}

	// PUT

	@ApiOperation(value = "Update existing User", response = ResponseEntity.class)
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Successfully updated User details"),
			@ApiResponse(code = 401, message = "You are not authorized to update User details"),
			@ApiResponse(code = 400, message = "You are sending a wrong request to server"),
			@ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
			@ApiResponse(code = 409, message = "User with email already registered")
	})
	@PutMapping(path = "/edit/{id}", produces = "application/json")
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	public ResponseEntity<User> editUserDetails(@PathVariable Long id,
												@Valid @RequestBody UpdateUserRequest request) {
		User user = userService.updateUser(id, request);
		log.info("Admin User with email: " + userService.getCurrentUserEmail() + " updated user details for user with id: " + id +", data: " + request.toString());
		return new ResponseEntity<>(user, HttpStatus.OK);
	}

	@ApiOperation(value = "Update Logged in User details", response = ResponseEntity.class)
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Successfully updated User details"),
			@ApiResponse(code = 401, message = "You are not authorized to update User details"),
			@ApiResponse(code = 400, message = "You are sending a wrong request to server"),
			@ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
			@ApiResponse(code = 409, message = "User with email already registered")
	})
	@PutMapping(path = "/edit/myDetails", produces = "application/json")
	public ResponseEntity<User> editMyDetails(@Valid @RequestBody UpdateCurrentUserRequest request) {
		User user = userService.updateCurrentUser(request);
		log.info("User with email: " + userService.getCurrentUserEmail() + " updated own details, data: " + request.toString());
		return new ResponseEntity<>(user, HttpStatus.OK);
	}

}

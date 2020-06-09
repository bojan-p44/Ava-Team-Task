package com.ava.controller;

import com.ava.model.Role;
import com.ava.model.User;
import com.ava.model.UserDTO;
import com.ava.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
@Api(tags = "user-controller")
public class UserController {

	@Autowired
	private UserService userService;

	@GetMapping("/{id}")
	public User getUserDetails(@PathVariable Long id) {
		return userService.findUserById(id);
	}

	@ApiOperation(value = "Create Admin User", response = ResponseEntity.class)
	@ApiResponses(value = {
			@ApiResponse(code = 201, message = "Successfully created Admin user"),
			@ApiResponse(code = 401, message = "You are not authorized to view the resource"),
			@ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden or you don't have proper Role"),
			@ApiResponse(code = 404, message = "The resource you were trying to reach is not found"),
			@ApiResponse(code = 409, message = "User already registered")
	})
	@PostMapping(path = "/admin/create", produces = "application/json")
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	public ResponseEntity<User> createAdminUser(@RequestBody UserDTO userDTO) {
		User user = userService.save(userDTO, Role.ROLE_ADMIN);
		return new ResponseEntity<>(user, HttpStatus.CREATED);
	}

}

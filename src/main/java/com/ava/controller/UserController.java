package com.ava.controller;

import com.ava.config.SwaggerConfig;
import com.ava.entity.User;
import com.ava.exception.ResourceAlreadyExists;
import com.ava.request.CreateAdminUserRequest;
import com.ava.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Base64;

@RestController
@RequestMapping("/user")
@Api(tags = {SwaggerConfig.TAG})
public class UserController {

	@Autowired
	private UserService userService;

	@ApiOperation(value = "Create Admin User", response = ResponseEntity.class)
	@ApiResponses(value = {
			@ApiResponse(code = 201, message = "Successfully created Admin user"),
			@ApiResponse(code = 401, message = "You are not authorized to view the resource"),
			@ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
			@ApiResponse(code = 404, message = "The resource you were trying to reach is not found"),
			@ApiResponse(code = 409, message = "User already registered")
	})
	@PostMapping(path = "/admin/create", produces = "application/json")
	public ResponseEntity createAdminUser(@RequestBody CreateAdminUserRequest request) {

		if (userService.findUserByEmail(request.getEmail()) != null) {
			throw new ResourceAlreadyExists("User with this email address is already registered!");
		}

		User user = new User(request.getFirstName(),
				request.getLastName(),
				request.getEmail(),
				new String(Base64.getEncoder().encode(request.getPassword().getBytes())),
				request.getCountry(),
				request.getAddress(),
				Boolean.TRUE);

		userService.save(user);

		return new ResponseEntity(HttpStatus.CREATED);
	}

}

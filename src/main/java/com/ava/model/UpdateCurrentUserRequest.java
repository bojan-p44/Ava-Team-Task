package com.ava.model;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.*;

@Getter
@Setter
public class UpdateCurrentUserRequest {

	@ApiModelProperty(notes = "The first name of User")
	@NotEmpty(message = "Please provide a first name")
	private String firstName;
	@ApiModelProperty(notes = "The last name of User")
	@NotEmpty(message = "Please provide a last name")
	private String lastName;
	@ApiModelProperty(notes = "The email of User")
	@Email(message = "Please provide a valid email address")
	@NotEmpty(message = "Please provide a email")
	private String email;
	@ApiModelProperty(notes = "The country of User")
	@NotEmpty(message = "Please provide a country")
	private String country;
	@ApiModelProperty(notes = "The address of User")
	@NotEmpty(message = "Please provide a address")
	private String address;
	@ApiModelProperty(notes = "The password of User")
	@NotEmpty(message = "Please provide a password")
	@Size(min = 5, max = 10, message
			= "Password must be between 5 and 10 characters")
	private String password;
	@ApiModelProperty(notes = "Password confirmation")
	@NotEmpty(message = "Please provide a password confirmation")
	@Size(min = 5, max = 10, message
			= "Password must be between 5 and 10 characters")
	private String passwordConfirm;
}

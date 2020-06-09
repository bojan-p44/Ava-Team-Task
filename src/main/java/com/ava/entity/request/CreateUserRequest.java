package com.ava.entity.request;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

@Getter
@Setter
public class CreateUserRequest {

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
	@ApiModelProperty(notes = "The password of User")
	@NotEmpty(message = "Please provide a password")
	private String password;
	@ApiModelProperty(notes = "The country of User")
	@NotEmpty(message = "Please provide a country")
	private String country;
	@ApiModelProperty(notes = "The address of User")
	@NotEmpty(message = "Please provide a address")
	private String address;
}

package com.ava.dto;

import com.ava.model.enumeration.Role;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@ToString
public class UpdateUserRequest {

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
	@ApiModelProperty(notes = "The Role of User")
	@NotNull(message = "Please provide a Role")
	private Role role;
}

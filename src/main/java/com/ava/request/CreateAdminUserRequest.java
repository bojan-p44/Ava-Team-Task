package com.ava.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateAdminUserRequest {

	private String firstName;
	private String lastName;
	private String email;
	private String password;
	private String country;
	private String address;
}

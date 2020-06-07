package com.ava.entity;

import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Email;
import java.io.Serializable;

@Entity
@Table(name = "users")
@Getter
@Setter
@NoArgsConstructor
@ToString
public class User implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@ApiModelProperty(notes = "The database generated user ID")
	private Long id;

	@Column(name = "first_name", nullable = false)
	@ApiModelProperty(notes = "The first name of User")
	private String firstName;

	@Column(name = "last_name", nullable = false)
	@ApiModelProperty(notes = "The last name of User")
	private String lastName;

	@Column(name = "email", nullable = false)
	@Email(message = "Please provide a valid email address")
	@ApiModelProperty(notes = "The email of User")
	private String email;

	@Column(name = "password", nullable = false)
	@ApiModelProperty(notes = "The password of User")
	private String password;

	@Column(name = "country", nullable = false)
	@ApiModelProperty(notes = "The country of User")
	private String country;

	@Column(name = "address", nullable = false)
	@ApiModelProperty(notes = "The address of User")
	private String address;

	@Column(name = "is_admin", nullable = false)
	@ApiModelProperty(notes = "A flag that means whether the User is an Admin or not")
	private Boolean isAdmin;

	public User(String firstName, String lastName, String email, String decodedPassword, String country, String address, Boolean isAdmin) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.password = decodedPassword;
		this.country = country;
		this.address = address;
		this.isAdmin = isAdmin;
	}
}

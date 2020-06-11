package com.ava.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import java.io.Serializable;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@ApiModel
public class JwtRequest implements Serializable {

	@ApiModelProperty(notes = "The username (email address)")
	private String username;
	@ApiModelProperty(notes = "The password")
	private String password;
}

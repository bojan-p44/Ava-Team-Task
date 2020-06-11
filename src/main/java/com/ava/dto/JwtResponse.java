package com.ava.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;

import java.io.Serializable;

@Getter
@ApiModel
public class JwtResponse implements Serializable {

	@ApiModelProperty(notes = "Auto-generated JSON Web Token")
	private final String jwttoken;

	public JwtResponse(String jwttoken) {
		this.jwttoken = jwttoken;
	}


}

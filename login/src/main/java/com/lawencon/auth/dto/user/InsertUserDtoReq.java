package com.lawencon.auth.dto.user;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class InsertUserDtoReq {
	@NotEmpty(message = "Username Is Empty")
	@Size(max = 30, min = 5, message = "Username min 5 Character and max 30 Character")
	private String username;

	private String password;

	@NotNull(message = "Role Id Is Empty")
	private Long roleId;

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Long getRoleId() {
		return roleId;
	}

	public void setRoleId(Long roleId) {
		this.roleId = roleId;
	}
}

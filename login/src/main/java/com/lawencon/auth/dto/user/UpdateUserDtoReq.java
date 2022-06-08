package com.lawencon.auth.dto.user;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class UpdateUserDtoReq {
	@NotNull(message = "Id Is Empty")
	private Long id;

	@NotEmpty(message = "Username Is Empty")
	@Size(max = 30, min = 5, message = "Username min 5 Character and max 30 Character")
	private String username;

	@NotNull(message = "Version Is Empty")
	private Integer version;
	private Boolean isActive;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public Integer getVersion() {
		return version;
	}

	public void setVersion(Integer version) {
		this.version = version;
	}

	public Boolean getIsActive() {
		return isActive;
	}

	public void setIsActive(Boolean isActive) {
		this.isActive = isActive;
	}
}

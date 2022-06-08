package com.lawencon.auth.dto.role;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class UpdateRoleDtoReq {
	@NotNull(message = "Role Id Is Empty")
	private Long id;
	
	@NotEmpty(message = "Role Name Is Empty")
	@Size(max = 30, min = 5, message = "Name min 5 Character and max 30 Character")
	private String roleName;
	
	@NotNull(message = "Version Is Empty")
	private Integer version;
	private Boolean isActive;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
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

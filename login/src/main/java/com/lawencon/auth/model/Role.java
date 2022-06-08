package com.lawencon.auth.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "user_role")
public class Role extends BaseModel {
	@Column(name = "role_code", length = 30, nullable = false, unique = true)
	private String roleCode;
	
	@Column(name = "role_name", length = 30, nullable = false)
	private String roleName;
	
	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}
	
	public void setRoleCode(String roleCode) {
		this.roleCode = roleCode;
	}
	
	public String getRoleName() {
		return roleName;
	}
	
	public String getRoleCode() {
		return roleCode;
	}
}

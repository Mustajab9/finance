package com.lawencon.auth.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "users")
public class User extends BaseModel {
	@Column(name = "username", length = 30, nullable = false, unique = true)
	private String username;
	
	@Column(name = "password", length = 225, nullable = false)
	private String password;
	
	@ManyToOne
	@JoinColumn(name = "role_id", nullable = false)
	private Role roleId;
	
	public void setRoleId(Role roleId) {
		this.roleId = roleId;
	}
	
	public void setUsername(String username) {
		this.username = username;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}
	
	public Role getRoleId() {
		return roleId;
	}
	
	public String getUsername() {
		return username;
	}
	
	public String getPassword() {
		return password;
	}
}

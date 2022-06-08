package com.lawencon.auth.dto.user.auth;

public class AuthorizationDtoRes {
	private String token;
	private Long id;

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
}

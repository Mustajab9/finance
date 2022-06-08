package com.lawencon.auth.service.impl;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import com.lawencon.auth.dto.user.auth.AuthorizationDtoRes;

public class BaseServiceImpl {	
	protected Long loginId() throws Exception {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if (auth != null && auth.getPrincipal() != null) {
			AuthorizationDtoRes authDtoRes = (AuthorizationDtoRes) auth.getPrincipal();
			return authDtoRes.getId();
		}
		throw new RuntimeException("Invalid login");
	}
}

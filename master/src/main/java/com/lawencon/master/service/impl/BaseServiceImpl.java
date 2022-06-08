package com.lawencon.master.service.impl;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import com.lawencon.master.pojo.AuthorizationPojo;

public class BaseServiceImpl {	
	protected Long loginId() throws Exception {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if (auth != null && auth.getPrincipal() != null) {
			AuthorizationPojo authPojo = (AuthorizationPojo) auth.getPrincipal();
			return authPojo.getId();
		}
		throw new RuntimeException("Invalid login");
	}
}

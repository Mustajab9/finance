package com.lawencon.transaction.service.impl;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import com.lawencon.transaction.pojo.AuthorizationPojo;

public class BaseServiceImpl {

	protected Long loginId() throws Exception {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if (auth != null && auth.getPrincipal() != null) {
			AuthorizationPojo authPojo = (AuthorizationPojo) auth.getPrincipal();
			return authPojo.getId();
		}
		throw new RuntimeException("Invalid login");
	}

	protected LocalDate convertDateToLocalDate(Date dateToConvert) {
		return dateToConvert.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
	}
}

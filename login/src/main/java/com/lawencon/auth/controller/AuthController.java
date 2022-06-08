package com.lawencon.auth.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lawencon.auth.dto.user.auth.AuthorizationDtoRes;

@RestController
public class AuthController {
	
	@PostMapping("verify")
	public ResponseEntity<?> verifyToken() throws Exception {
		var authentication = SecurityContextHolder.getContext().getAuthentication();
		if (authentication != null) {
			AuthorizationDtoRes data = (AuthorizationDtoRes) authentication.getPrincipal();
			return new ResponseEntity<>(data, HttpStatus.OK);
		}
		return new ResponseEntity<>("Invalid token", HttpStatus.UNAUTHORIZED);
	}
}

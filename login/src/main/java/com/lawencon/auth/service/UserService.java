package com.lawencon.auth.service;

import org.springframework.security.core.userdetails.UserDetailsService;

import com.lawencon.auth.dto.InsertDtoRes;
import com.lawencon.auth.dto.UpdateDtoRes;
import com.lawencon.auth.dto.user.GetAllUserDtoRes;
import com.lawencon.auth.dto.user.GetByUserIdDtoRes;
import com.lawencon.auth.dto.user.InsertUserDtoReq;
import com.lawencon.auth.dto.user.UpdateUserDtoReq;
import com.lawencon.auth.model.User;

public interface UserService extends BaseSevice, UserDetailsService {
	GetAllUserDtoRes getAll() throws Exception;

	GetByUserIdDtoRes getById(Long id) throws Exception;

	InsertDtoRes insert(InsertUserDtoReq data) throws Exception;

	UpdateDtoRes update(UpdateUserDtoReq data) throws Exception;

	User getByUser(String data) throws Exception;

	Long getUserId() throws Exception;
}

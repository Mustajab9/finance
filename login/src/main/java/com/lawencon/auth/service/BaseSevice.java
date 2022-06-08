package com.lawencon.auth.service;

import com.lawencon.auth.dto.DeleteDtoRes;

public interface BaseSevice {
	DeleteDtoRes deleteById(Long id) throws Exception;
}

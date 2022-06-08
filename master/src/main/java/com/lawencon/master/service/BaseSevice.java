package com.lawencon.master.service;

import com.lawencon.master.dto.DeleteDtoRes;

public interface BaseSevice {
	DeleteDtoRes deleteById(Long id) throws Exception;
}

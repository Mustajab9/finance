package com.lawencon.transaction.service;

import com.lawencon.transaction.dto.InsertDtoRes;
import com.lawencon.transaction.dto.gl.GetAllGLDtoRes;
import com.lawencon.transaction.dto.gl.GetByGLIdDtoRes;
import com.lawencon.transaction.dto.gl.InsertGLDtoReq;

public interface GeneralLedgerService {
	GetAllGLDtoRes getAll() throws Exception;
	
	GetByGLIdDtoRes getById(Long id) throws Exception;
	
	InsertDtoRes insert(InsertGLDtoReq data) throws Exception;
}

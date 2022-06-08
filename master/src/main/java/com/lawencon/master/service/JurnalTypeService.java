package com.lawencon.master.service;

import com.lawencon.master.dto.InsertDtoRes;
import com.lawencon.master.dto.UpdateDtoRes;
import com.lawencon.master.dto.jurnaltype.GetAllTypeDtoRes;
import com.lawencon.master.dto.jurnaltype.GetByTypeIdDtoRes;
import com.lawencon.master.dto.jurnaltype.InsertTypeDtoReq;
import com.lawencon.master.dto.jurnaltype.UpdateTypeDtoReq;

public interface JurnalTypeService extends BaseSevice {
	GetAllTypeDtoRes getAll() throws Exception;

	GetByTypeIdDtoRes getById(Long id) throws Exception;

	InsertDtoRes insert(InsertTypeDtoReq data) throws Exception;

	UpdateDtoRes update(UpdateTypeDtoReq data) throws Exception;
}
